package net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.EdimaxSocket;
import net.kleditzsch.shcDesktopClient.Util.Validator.IpAddressValidator;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Edimax Steckdose Formular
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class EdimaxSocketFormController {

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

    @FXML // fx:id="inputComment"
    private TextField inputComment; // Value injected by FXMLLoader

    @FXML // fx:id="inputInverse"
    private CheckBox inputInverse; // Value injected by FXMLLoader

    @FXML // fx:id="inputDisabled"
    private CheckBox inputDisabled; // Value injected by FXMLLoader

    @FXML // fx:id="inputIpAddress"
    private TextField inputIpAddress; // Value injected by FXMLLoader

    @FXML // fx:id="inputUserName"
    private TextField inputUserName; // Value injected by FXMLLoader

    @FXML // fx:id="inputPassword"
    private TextField inputPassword; // Value injected by FXMLLoader

    @FXML // fx:id="inputType"
    private ChoiceBox<String> inputType; // Value injected by FXMLLoader

    /**
     * Datenelement
     */
    protected EdimaxSocket edimaxSocket;

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

            edimaxSocket.setHash(inputHash.getText());
            edimaxSocket.setName(inputName.getText());
            edimaxSocket.setComment(inputComment.getText());
            edimaxSocket.setSocketType(inputType.getValue().equals("SP 1101W") ? EdimaxSocket.TYPE_SP_1101W : EdimaxSocket.TYPE_SP_2101W);
            edimaxSocket.setIpAddress(inputIpAddress.getText());
            edimaxSocket.setUsername(inputUserName.getText());
            edimaxSocket.setPassword(inputPassword.getText());
            edimaxSocket.setInverse(inputInverse.isSelected());
            edimaxSocket.setDisabled(inputDisabled.isSelected());

            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert formGridPane != null : "fx:id=\"formGridPane\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert inputComment != null : "fx:id=\"inputComment\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert inputInverse != null : "fx:id=\"inputInverse\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert inputDisabled != null : "fx:id=\"inputDisabled\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert inputIpAddress != null : "fx:id=\"inputIpAddress\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert inputUserName != null : "fx:id=\"inputUserName\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert inputPassword != null : "fx:id=\"inputPassword\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";
        assert inputType != null : "fx:id=\"inputType\" was not injected: check your FXML file 'EdimaxSocketForm.fxml'.";

        //Formularfelder initalisieren
        inputType.getItems().addAll("SP 1101W", "SP 2101W");

        //Validieren
        validationSupport.registerValidator(inputName, Validator.createEmptyValidator("Der Name muss ausgefüllt werden"));
        validationSupport.registerValidator(inputType, Validator.createEmptyValidator("Der Typ muss ausgewählt werden"));
        validationSupport.registerValidator(inputIpAddress, new IpAddressValidator());
        validationSupport.registerValidator(inputUserName, Validator.createEmptyValidator("Der Benutzername muss ausgefüllt werden"));
        validationSupport.registerValidator(inputPassword, Validator.createEmptyValidator("Das Passwort muss ausgefüllt werden"));

        buttonSave.disableProperty().bind(validationSupport.invalidProperty());
    }

    public EdimaxSocket getElement() {
        return edimaxSocket;
    }

    public void setElement(EdimaxSocket edimaxSocket) {

        this.edimaxSocket = edimaxSocket;
        if(edimaxSocket.getHash() == null || edimaxSocket.getHash().equals("")) {
            edimaxSocket.setHash(edimaxSocket.createHash());
            edimaxSocket.setUsername("admin");
            edimaxSocket.setPassword("1234");
        }

        //Daten setzen
        inputHash.setText(edimaxSocket.getHash());
        inputName.setText(edimaxSocket.getName());
        inputComment.setText(edimaxSocket.getComment());
        inputType.setValue((edimaxSocket.getSocketType() == EdimaxSocket.TYPE_SP_1101W ? "SP 1101W" : "SP 2101W"));
        inputIpAddress.setText(edimaxSocket.getIpAddress());
        inputUserName.setText(edimaxSocket.getUsername());
        inputPassword.setText(edimaxSocket.getPassword());
        inputInverse.setSelected(edimaxSocket.isInverse());
        inputDisabled.setSelected(edimaxSocket.isDisabled());
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
