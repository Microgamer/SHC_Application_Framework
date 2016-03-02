package net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.ScriptDouble;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * An/Aus Script
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ScriptDoubleFormController {

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

    @FXML // fx:id="inputDisabled"
    private CheckBox inputDisabled; // Value injected by FXMLLoader

    @FXML // fx:id="inputSwitchServer"
    private ComboBox<SwitchServer> inputSwitchServer; // Value injected by FXMLLoader

    @FXML // fx:id="inputCommandOn"
    private TextArea inputCommandOn; // Value injected by FXMLLoader

    @FXML // fx:id="inputCommandOff"
    private TextArea inputCommandOff; // Value injected by FXMLLoader

    /**
     * Datenelement
     */
    protected ScriptDouble element;

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

            element.setHash(inputHash.getText());
            element.setName(inputName.getText());
            element.setComment(inputComment.getText());
            element.setSwitchServer((inputSwitchServer.getValue() != null ? inputSwitchServer.getValue().getHash() : ""));
            element.setOnCommand(inputCommandOn.getText());
            element.setOffCommand(inputCommandOff.getText());
            element.setDisabled(inputDisabled.isSelected());

            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert formGridPane != null : "fx:id=\"formGridPane\" was not injected: check your FXML file 'ScriptSingleForm.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'ScriptSingleForm.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'ScriptSingleForm.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'ScriptSingleForm.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'ScriptSingleForm.fxml'.";
        assert inputComment != null : "fx:id=\"inputComment\" was not injected: check your FXML file 'ScriptSingleForm.fxml'.";
        assert inputDisabled != null : "fx:id=\"inputDisabled\" was not injected: check your FXML file 'ScriptSingleForm.fxml'.";
        assert inputSwitchServer != null : "fx:id=\"inputSwitchServer\" was not injected: check your FXML file 'ScriptSingleForm.fxml'.";
        assert inputCommandOn != null : "fx:id=\"inputCommandOn\" was not injected: check your FXML file 'ScriptDoubleForm.fxml'.";
        assert inputCommandOff != null : "fx:id=\"inputCommandOff\" was not injected: check your FXML file 'ScriptDoubleForm.fxml'.";

        //Formularfelder initalisieren
        inputSwitchServer.setConverter(new StringConverter<SwitchServer>() {
            @Override
            public String toString(SwitchServer object) {

                System.out.println(object);
                if(object == null) {

                    return "Server";
                }
                return object.toString();
            }

            @Override
            public SwitchServer fromString(String string) {

                for(SwitchServer switchServer : inputSwitchServer.getItems()) {

                    if(switchServer.getName().equals(string)) {

                        return switchServer;
                    }
                }
                return null;
            }
        });

        //Validieren
        validationSupport.registerValidator(inputName, Validator.createEmptyValidator("Der Name muss ausgefüllt werden"));
        validationSupport.registerValidator(inputSwitchServer, Validator.createEmptyValidator("Der Schaltserver muss ausgewählt werden"));
        validationSupport.registerValidator(inputCommandOn, Validator.createEmptyValidator("Das an Kommando muss ausgefüllt werden"));
        validationSupport.registerValidator(inputCommandOff, Validator.createEmptyValidator("Das aus Kommando muss ausgefüllt werden"));

        buttonSave.disableProperty().bind(validationSupport.invalidProperty());
    }

    public void setSwitchServers(Map<String, SwitchServer> switchServers) {
        this.switchServers = switchServers;

        //Schaltserver AUswahl initalisieren
        inputSwitchServer.getItems().add(null);
        for(SwitchServer switchServer : switchServers.values()) {

            if(switchServer.isEnabled() && switchServer.isScriptEnabled()) {

                inputSwitchServer.getItems().add(switchServer);
            }
        }
    }

    public ScriptDouble getElement() {
        return element;
    }

    public void setElement(ScriptDouble element) {

        this.element = element;
        if(element.getHash() == null || element.getHash().equals("")) {
            element.setHash(element.createHash());
        }

        //Daten setzen
        inputHash.setText(element.getHash());
        inputName.setText(element.getName());
        inputComment.setText(element.getComment());
        inputSwitchServer.setValue(switchServers.get(element.getSwitchServer()));
        inputCommandOn.setText(element.getOnCommand());
        inputCommandOff.setText(element.getOffCommand());
        inputDisabled.setSelected(element.isDisabled());
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
