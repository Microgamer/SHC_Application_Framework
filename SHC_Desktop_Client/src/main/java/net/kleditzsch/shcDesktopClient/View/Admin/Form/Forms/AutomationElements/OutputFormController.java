package net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.Output;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Ausgang
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class OutputFormController {

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

    @FXML // fx:id="inputSwitchServer"
    private ChoiceBox<SwitchServer> inputSwitchServer; // Value injected by FXMLLoader

    @FXML // fx:id="inputPin"
    private ChoiceBox<Integer> inputPin; // Value injected by FXMLLoader

    @FXML // fx:id="inputDisabled"
    private CheckBox inputDisabled; // Value injected by FXMLLoader

    /**
     * Datenelement
     */
    protected Output output;

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

            output.setHash(inputHash.getText());
            output.setName(inputName.getText());
            output.setComment(inputComment.getText());
            output.setSwitchServer(inputSwitchServer.getValue().getHash());
            output.setPin(inputPin.getValue());
            output.setInverse(inputInverse.isSelected());
            output.setDisabled(inputDisabled.isSelected());

            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert formGridPane != null : "fx:id=\"formGridPane\" was not injected: check your FXML file 'outputTpl.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'outputTpl.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'outputTpl.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'outputTpl.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'outputTpl.fxml'.";
        assert inputComment != null : "fx:id=\"inputComment\" was not injected: check your FXML file 'outputTpl.fxml'.";
        assert inputInverse != null : "fx:id=\"inputInverse\" was not injected: check your FXML file 'outputTpl.fxml'.";
        assert inputSwitchServer != null : "fx:id=\"inputSwitchServer\" was not injected: check your FXML file 'outputTpl.fxml'.";
        assert inputPin != null : "fx:id=\"inputPin\" was not injected: check your FXML file 'outputTpl.fxml'.";
        assert inputDisabled != null : "fx:id=\"inputDisabled\" was not injected: check your FXML file 'outputTpl.fxml'.";

        //Auswahlfelder initalisieren
        for(int i = 0; i <= 30; i++) {

            inputPin.getItems().add(i);
        }

        //Validieren
        validationSupport.registerValidator(inputName, Validator.createEmptyValidator("Der Name muss ausgefüllt werden"));
        validationSupport.registerValidator(inputSwitchServer, Validator.createEmptyValidator("Der Schaltserver muss ausgewählt werden"));
        validationSupport.registerValidator(inputPin, Validator.createEmptyValidator("Der Pin muss ausgewählt werden"));

        buttonSave.disableProperty().bind(validationSupport.invalidProperty());
    }

    public void setSwitchServers(Map<String, SwitchServer> switchServers) {
        this.switchServers = switchServers;

        //Schaltserver AUswahl initalisieren
        for(SwitchServer switchServer : switchServers.values()) {

            if(switchServer.isEnabled() && switchServer.isWriteGpioEnabled()) {

                inputSwitchServer.getItems().add(switchServer);
            }
        }
    }

    public Output getElement() {
        return output;
    }

    public void setElement(Output output) {

        this.output = output;
        if(output.getHash() == null || output.getHash().equals("")) {
            output.setHash(output.createHash());
        }

        //Daten setzen
        inputHash.setText(output.getHash());
        inputName.setText(output.getName());
        inputComment.setText(output.getComment());
        inputSwitchServer.setValue(switchServers.get(output.getSwitchServer()));
        inputPin.setValue(output.getPin());
        inputInverse.setSelected(output.isInverse());
        inputDisabled.setSelected(output.isDisabled());
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
