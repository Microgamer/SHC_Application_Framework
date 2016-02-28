package net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import net.kleditzsch.shcCore.Automation.Devices.Readable.UserAtHome;
import net.kleditzsch.shcDesktopClient.Util.Validator.IpAddressValidator;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Benutzer zu Hause Formular
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserAtHomeFormController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="formGridPane"
    private GridPane formGridPane; // Value injected by FXMLLoader

    @FXML // fx:id="inputHash"
    private TextField inputHash; // Value injected by FXMLLoader

    @FXML // fx:id="inputName"
    private TextField inputName; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCancel"
    private Button buttonCancel; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSave"
    private Button buttonSave; // Value injected by FXMLLoader

    @FXML // fx:id="inputIpAddress"
    private TextField inputIpAddress; // Value injected by FXMLLoader

    @FXML // fx:id="inputTimeout"
    private Spinner<Integer> inputTimeout; // Value injected by FXMLLoader

    @FXML // fx:id="inputExternalData"
    private CheckBox inputExternalData; // Value injected by FXMLLoader

    @FXML // fx:id="inputComment"
    private TextField inputComment; // Value injected by FXMLLoader

    @FXML // fx:id="inputDisabled"
    private CheckBox inputDisabled; // Value injected by FXMLLoader

    /**
     * Datenelement
     */
    protected UserAtHome userAtHome;

    /**
     * Validierung
     */
    protected ValidationSupport validationSupport = new ValidationSupport();

    /**
     * abgebrochen
     */
    protected boolean canceld = true;

    @FXML
    void cancel(ActionEvent event) {

        //Fenster schliesen
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
        canceld = true;
    }

    @FXML
    void save(ActionEvent event) {

        if(!validationSupport.isInvalid()) {

            userAtHome.setHash(inputHash.getText());
            userAtHome.setName(inputName.getText());
            userAtHome.setComment(inputComment.getText());
            userAtHome.setIpAddress(inputIpAddress.getText());
            userAtHome.setTimeout(inputTimeout.getValueFactory().getValue());
            userAtHome.setUseExternalData(inputExternalData.isSelected());
            userAtHome.setDisabled(inputDisabled.isSelected());

            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert formGridPane != null : "fx:id=\"formGridPane\" was not injected: check your FXML file 'UserAtHomeForm.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'UserAtHomeForm.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'UserAtHomeForm.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'UserAtHomeForm.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'UserAtHomeForm.fxml'.";
        assert inputIpAddress != null : "fx:id=\"inputIpAddress\" was not injected: check your FXML file 'UserAtHomeForm.fxml'.";
        assert inputTimeout != null : "fx:id=\"inputTimeout\" was not injected: check your FXML file 'UserAtHomeForm.fxml'.";
        assert inputExternalData != null : "fx:id=\"inputExternalData\" was not injected: check your FXML file 'UserAtHomeForm.fxml'.";
        assert inputComment != null : "fx:id=\"inputComment\" was not injected: check your FXML file 'UserAtHomeForm.fxml'.";
        assert inputDisabled != null : "fx:id=\"inputDisabled\" was not injected: check your FXML file 'outputTpl.fxml'.";

        //Bindings herstellen
        inputIpAddress.disableProperty().bind(inputExternalData.selectedProperty());
        inputTimeout.disableProperty().bind(inputExternalData.selectedProperty());

        //Spinner initalisieren
        SpinnerValueFactory.IntegerSpinnerValueFactory timeoutValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 86_400_000, 0);
        timeoutValueFactory.setAmountToStepBy(100);
        inputTimeout.setValueFactory(timeoutValueFactory);
        inputTimeout.setEditable(true);
        inputTimeout.getEditor().textProperty().addListener(e -> {

            if(!inputTimeout.isEditable()) {

                return;
            }

            String text = inputTimeout.getEditor().getText();
            SpinnerValueFactory<Integer> vf = inputTimeout.getValueFactory();
            if(vf != null) {

                StringConverter<Integer> converter = vf.getConverter();
                if(converter != null && text != null) {

                    vf.setValue(converter.fromString(text));
                }
            }
        });

        //Validieren
        validationSupport.registerValidator(inputName, Validator.createEmptyValidator("Der Name muss ausgefüllt werden"));
        validationSupport.registerValidator(inputIpAddress, new IpAddressValidator());

        //Validator deaktivieren wenn externe Daten aktiv
        inputExternalData.selectedProperty().addListener(e -> {

            if(inputExternalData.isSelected()) {

                validationSupport.registerValidator(inputIpAddress, false, (Control c, TextField newValue) -> ValidationResult.fromErrorIf( c, "", false));
            } else {

                validationSupport.registerValidator(inputIpAddress, true, new IpAddressValidator());
            }
        });

        buttonSave.disableProperty().bind(validationSupport.invalidProperty());
    }

    /**
     * gibt den Benutzer zu Hause zurück
     *
     * @return Benutzer zu Hause
     */
    public UserAtHome getElement() {
        return userAtHome;
    }

    /**
     * setzt den Benutzer zu Hause
     *
     * @param userAtHome Benutzer zu Hause
     */
    public void setElement(UserAtHome userAtHome) {

        this.userAtHome = userAtHome;
        if(userAtHome.getHash() == null || userAtHome.getHash().equals("")) {
            userAtHome.setHash(userAtHome.createHash());
        }

        //Daten setzen
        inputHash.setText(userAtHome.getHash());
        inputName.setText(userAtHome.getName());
        inputComment.setText(userAtHome.getComment());
        inputIpAddress.setText(userAtHome.getIpAddress());
        inputTimeout.getValueFactory().setValue(userAtHome.getTimeout());
        inputExternalData.setSelected(userAtHome.isUseExternalData());
        inputDisabled.setSelected(userAtHome.isDisabled());
    }

    /**
     * gibt an ob die Bearbeitung abgebrochen wurde
     *
     * @return true wenn abgebrochen
     */
    public boolean isCanceld() {
        return canceld;
    }
}
