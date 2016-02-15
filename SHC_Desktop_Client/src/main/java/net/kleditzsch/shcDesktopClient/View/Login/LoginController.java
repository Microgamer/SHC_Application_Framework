package net.kleditzsch.shcDesktopClient.View.Login;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcCore.Core.BasicElement;
import net.kleditzsch.shcCore.ClientData.Login.Handshake;
import net.kleditzsch.shcCore.ClientData.Login.LoginResponse;
import net.kleditzsch.shcCore.User.ChallangeResponseUtil;
import net.kleditzsch.shcDesktopClient.HttpInterface.ConnectionManager;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.Data.Settings.Settings;
import net.kleditzsch.shcDesktopClient.Util.UiNotificationHelper;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Login Formular
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LoginController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootStackPane"
    private StackPane rootStackPane; // Value injected by FXMLLoader

    @FXML // fx:id="rootGrid"
    private GridPane rootGrid; // Value injected by FXMLLoader

    @FXML // fx:id="inputServerAddress"
    private TextField inputServerAddress; // Value injected by FXMLLoader

    @FXML // fx:id="inputUsername"
    private TextField inputUsername; // Value injected by FXMLLoader

    @FXML // fx:id="inputUserhash"
    private TextField inputUserhash; // Value injected by FXMLLoader

    @FXML // fx:id="inputServerPort"
    private Spinner<Integer> inputServerPort; // Value injected by FXMLLoader

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        //Textfelder initalisieren und validieren
        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(inputServerAddress, Validator.createEmptyValidator("die Server Adresse muss ausgefüllt werden"));
        validationSupport.registerValidator(inputUsername, Validator.createEmptyValidator("der Benutzername muss ausgefüllt werden"));
        validationSupport.registerValidator(inputUserhash, Validator.createEmptyValidator("der Benutzerhash muss ausgefüllt werden"));

        //Einstellungen laden
        Settings settings = ShcDesktopClient.getInstance().getSettings();
        String serverAddress = (String) settings.getSetting(Settings.SETTING_SERVER_ADDRESS).getValue();
        int serverPort = Double.valueOf((double) settings.getSetting(Settings.SETTING_SERVER_PORT).getValue()).intValue();
        String userName = (String) settings.getSetting(Settings.SETTING_SERVER_USER).getValue();
        String userHash = (String) settings.getSetting(Settings.SETTING_SERVER_IDENTIFIER).getValue();

        //Formularfelder Initalisieren
        inputServerAddress.setText(serverAddress);
        inputUsername.setText(userName);
        inputUserhash.setText(userHash);

        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = (SpinnerValueFactory.IntegerSpinnerValueFactory) new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, serverPort);
        inputServerPort.setValueFactory(valueFactory);
        inputServerPort.setEditable(true);
        inputServerPort.getEditor().textProperty().addListener(e -> {

            if(!inputServerPort.isEditable()) {

                return;
            }

            String text = inputServerPort.getEditor().getText();
            SpinnerValueFactory<Integer> vf = inputServerPort.getValueFactory();
            if(vf != null) {

                StringConverter<Integer> converter = vf.getConverter();
                if(converter != null && text != null) {

                    vf.setValue(converter.fromString(text));
                }
            }
        });

        loginButton.disableProperty().bind(validationSupport.invalidProperty());
    }

    @FXML
    void tryLogin(ActionEvent event) {

        //Ladeanzeige
        MaskerPane maskerPane = new MaskerPane();
        rootStackPane.getChildren().add(maskerPane);
        maskerPane.setText("Bitte warten ...");
        maskerPane.setVisible(true);

        //Eingaben holen
        String serverAddress = inputServerAddress.getText();
        int serverPort = inputServerPort.valueProperty().getValue();
        String userName = inputUsername.getText();
        String userHash = inputUserhash.getText();

        //Verbindungsversuch
        ConnectionManager cm = new ConnectionManager(serverAddress, serverPort);

        Settings settings = ShcDesktopClient.getInstance().getSettings();
        String clientHash = (String) settings.getSetting(Settings.SETTING_SERVER_CLIENT_HASH).getValue();
        if(clientHash != null && clientHash.length() < 20) {

            //Handshake senden
            clientHash = BasicElement.createHash();
            String userAgent = "SHC Desktop ClientData von " + System.getProperty("user.name") + " unter " + System.getProperty("os.name") + " " + System.getProperty("os.version");
            try {

                Handshake handshake = cm.sendHandshake(clientHash, userAgent);
                if(handshake.isSuccess()) {

                    //erfolgreich gesendet

                    //ClientHash Speichern
                    settings.getSetting(Settings.SETTING_SERVER_ADDRESS).setValue(serverAddress);
                    settings.getSetting(Settings.SETTING_SERVER_PORT).setValue(serverPort);
                    settings.getSetting(Settings.SETTING_SERVER_CLIENT_HASH).setValue(clientHash);
                    settings.dump();

                    //Meldung
                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Gerät angemeldet", "Das Gerät wurde erfolgreich angemeldet, nach der Freischaltung dur einen Andministrator kannst du das SHC benutzen");
                    //UiDialogHelper.showInfoDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Gerät angemeldet", null, "Das Gerät wurde erfolgreich angemeldet, nach der Freischaltung dur einen Andministrator kannst du das SHC benutzen");
                    maskerPane.setVisible(false);
                    return;
                } else {

                    //Fehler beim senden
                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Fehler", handshake.getMessage());
                    //UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Fehler", null, handshake.getMessage());
                    maskerPane.setVisible(false);
                    return;
                }
            } catch (IOException e) {

                //Verbindungsfehler
                maskerPane.setVisible(false);
                UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Verbindungsfehler", null, e.getLocalizedMessage());
                return;
            }
        }

        //Anmeldeversuch
        try {

            String challange = cm.getLoginChallange();
            String challengeResponse = ChallangeResponseUtil.computeChallangeResponse(challange, userName, userHash, clientHash);

            LoginResponse loginResponse = cm.sendLogin(challengeResponse);
            if(loginResponse.isSuccess() == true) {

                //Login erfolgreich
                maskerPane.setVisible(false);

                //Benutzerdaten sichern
                settings.getSetting(Settings.SETTING_SERVER_USER).setValue(userName);
                settings.getSetting(Settings.SETTING_SERVER_IDENTIFIER).setValue(userHash);
                settings.dump();

                //Status auf Verbunden ändern
                ShcDesktopClient.getInstance().getMainViewController().setState(true);

                //Connectionmanager über die Session ID informieren
                ShcDesktopClient.getInstance().getConnectionManager().setSessionId(loginResponse.getSessionId());
                ShcDesktopClient.getInstance().getConnectionManager().updateLastContact();
                UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Login erfolgreich");
                return;
            } else {

                //Login Fehlgeschlagen
                maskerPane.setVisible(false);
                UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Login Fehler", loginResponse.getMessage());
                //UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Login Fehler", null, loginResponse.getMessage());
                return;
            }

        } catch (IOException | NoSuchAlgorithmException e) {

            //Verbindungsfehler
            maskerPane.setVisible(false);
            UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Verbindungsfehler", e.getMessage());
            //UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Verbindungsfehler", null, e.getLocalizedMessage());
            return;
        }
    }
}
