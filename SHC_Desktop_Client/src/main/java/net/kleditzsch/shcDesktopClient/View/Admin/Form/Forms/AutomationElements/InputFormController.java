package net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.kleditzsch.shcCore.Automation.Devices.Readable.Input;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class InputFormController {

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

    @FXML // fx:id="inputSwitchServer"
    private ChoiceBox<SwitchServer> inputSwitchServer; // Value injected by FXMLLoader

    @FXML // fx:id="inputPin"
    private ChoiceBox<Integer> inputPin; // Value injected by FXMLLoader

    @FXML // fx:id="inputInverse"
    private CheckBox inputInverse; // Value injected by FXMLLoader

    @FXML // fx:id="inputExternalData"
    private CheckBox inputExternalData; // Value injected by FXMLLoader

    @FXML // fx:id="inputDisabled"
    private CheckBox inputDisabled; // Value injected by FXMLLoader

    @FXML // fx:id="inputIdentifier"
    private TextField inputIdentifier; // Value injected by FXMLLoader

    /**
     * Datenelement
     */
    protected Input input;

    /**
     * Liste der Schaltserver
     */
    protected Map<String, SwitchServer> switchServers;

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

            input.setHash(inputHash.getText());
            input.setName(inputName.getText());
            input.setComment(inputComment.getText());
            input.setIdentifier(inputIdentifier.getText());
            input.setSwitchServer(inputSwitchServer.getValue().getHash());
            input.setPin(inputPin.getValue());
            input.setInverse(inputInverse.isSelected());
            input.setUseExternalData(inputExternalData.isSelected());
            input.setDisabled(inputDisabled.isSelected());

            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert formGridPane != null : "fx:id=\"formGridPane\" was not injected: check your FXML file 'InputForm.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'InputForm.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'InputForm.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'InputForm.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'InputForm.fxml'.";
        assert inputComment != null : "fx:id=\"inputComment\" was not injected: check your FXML file 'InputForm.fxml'.";
        assert inputSwitchServer != null : "fx:id=\"inputSwitchServer\" was not injected: check your FXML file 'InputForm.fxml'.";
        assert inputPin != null : "fx:id=\"inputPin\" was not injected: check your FXML file 'InputForm.fxml'.";
        assert inputInverse != null : "fx:id=\"inputInverse\" was not injected: check your FXML file 'InputForm.fxml'.";
        assert inputExternalData != null : "fx:id=\"inputExternalData\" was not injected: check your FXML file 'InputForm.fxml'.";
        assert inputDisabled != null : "fx:id=\"inputDisabled\" was not injected: check your FXML file 'outputTpl.fxml'.";

        //Bindings herstellen
        inputSwitchServer.disableProperty().bind(inputExternalData.selectedProperty());
        inputPin.disableProperty().bind(inputExternalData.selectedProperty());
        inputInverse.disableProperty().bind(inputExternalData.selectedProperty());
        inputIdentifier.disableProperty().bind(inputExternalData.selectedProperty().not());

        //Auswahlfelder initalisieren
        for(int i = 0; i <= 30; i++) {

            inputPin.getItems().add(i);
        }

        //Validieren
        validationSupport.registerValidator(inputName, Validator.createEmptyValidator("Der Name muss ausgefüllt werden"));
        validationSupport.registerValidator(inputIdentifier, Validator.createEmptyValidator("Die Identifizierung muss ausgefüllt werden"));
        validationSupport.registerValidator(inputSwitchServer, Validator.createEmptyValidator("Der Schaltserver muss ausgewählt werden"));
        validationSupport.registerValidator(inputPin, Validator.createEmptyValidator("Der Pin muss ausgewählt werden"));

        //Validator deaktivieren wenn externe Daten aktiv
        inputExternalData.selectedProperty().addListener(e -> {

            if(inputExternalData.isSelected()) {

                validationSupport.registerValidator(inputSwitchServer, false, (Control c, TextField newValue) -> ValidationResult.fromErrorIf( c, "", false));
                validationSupport.registerValidator(inputPin, false, (Control c, TextField newValue) -> ValidationResult.fromErrorIf( c, "", false));
                validationSupport.registerValidator(inputIdentifier, Validator.createEmptyValidator("Die Identifizierung muss ausgefüllt werden"));
            } else {

                validationSupport.registerValidator(inputSwitchServer, Validator.createEmptyValidator("Der Schaltserver muss ausgewählt werden"));
                validationSupport.registerValidator(inputPin, Validator.createEmptyValidator("Der Pin muss ausgewählt werden"));
                validationSupport.registerValidator(inputIdentifier, false, (Control c, TextField newValue) -> ValidationResult.fromErrorIf( c, "", false));
            }
        });

        buttonSave.disableProperty().bind(validationSupport.invalidProperty());
    }

    public void setSwitchServers(Map<String, SwitchServer> switchServers) {
        this.switchServers = switchServers;

        //Schaltserver AUswahl initalisieren
        for(SwitchServer switchServer : switchServers.values()) {

            if(switchServer.isEnabled() && switchServer.isReadGpioEnabled()) {

                inputSwitchServer.getItems().add(switchServer);
            }
        }
    }

    public Input getElement() {
        return input;
    }

    public void setElement(Input input) {

        this.input = input;
        if(input.getHash() == null || input.getHash().equals("")) {
            input.setHash(input.createHash());
        }

        //Daten setzen
        inputHash.setText(input.getHash());
        inputName.setText(input.getName());
        inputComment.setText(input.getComment());
        inputIdentifier.setText(input.getIdentifier());
        inputSwitchServer.setValue(switchServers.get(input.getSwitchServer()));
        inputPin.setValue(input.getPin());
        inputInverse.setSelected(input.isInverse());
        inputExternalData.setSelected(input.isUseExternalData());
        inputDisabled.setSelected(input.isDisabled());
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
