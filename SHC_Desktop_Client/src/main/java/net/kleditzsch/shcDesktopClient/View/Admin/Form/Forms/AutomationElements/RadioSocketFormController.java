package net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.RadioSocket;
import net.kleditzsch.shcCore.Settings.ListSetting;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.Settings.Settings;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.CheckModel;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Funksteckdosen Formular
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RadioSocketFormController {

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

    @FXML // fx:id="inputSwitchServer"
    private CheckComboBox<SwitchServer> inputSwitchServers; // Value injected by FXMLLoader

    @FXML // fx:id="inputProtocol"
    private ComboBox<String> inputProtocol; // Value injected by FXMLLoader

    @FXML // fx:id="inputSystemCode"
    private TextField inputSystemCode; // Value injected by FXMLLoader

    @FXML // fx:id="inputDeviceCode"
    private TextField inputDeviceCode; // Value injected by FXMLLoader

    @FXML // fx:id="inputContinues"
    private ChoiceBox<Integer> inputContinues; // Value injected by FXMLLoader

    @FXML // fx:id="inputUseId"
    private CheckBox inputUseId; // Value injected by FXMLLoader

    /**
     * Datenelement
     */
    protected RadioSocket element;

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
            element.getSwitchServers().clear();
            CheckModel<SwitchServer> switchServerCheckModel = inputSwitchServers.getCheckModel();
            for(SwitchServer switchServer : switchServerCheckModel.getCheckedItems()) {

                element.getSwitchServers().add(switchServer.getHash());
            }
            element.setProtocol(inputProtocol.getValue());
            element.setSystemCode(inputSystemCode.getText());
            element.setDeviceCode(inputDeviceCode.getText());
            element.setContinues(inputContinues.getValue());
            element.setUseID(inputUseId.isSelected());
            element.setInverse(inputInverse.isSelected());
            element.setDisabled(inputDisabled.isSelected());

            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;

            //Protokoll wenn noch nicht bekannt speichern
            ListSetting protocols = ShcDesktopClient.getInstance().getSettings().getListSetting(Settings.SETTING_CLIENT_PROTOCOLS);

            if(!protocols.getValue().contains(element.getProtocol())) {

                protocols.getValue().add(element.getProtocol());
            }
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert formGridPane != null : "fx:id=\"formGridPane\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputComment != null : "fx:id=\"inputComment\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputInverse != null : "fx:id=\"inputInverse\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputDisabled != null : "fx:id=\"inputDisabled\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputSwitchServers != null : "fx:id=\"inputSwitchServers\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputProtocol != null : "fx:id=\"inputProtocol\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputSystemCode != null : "fx:id=\"inputSystemCode\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputDeviceCode != null : "fx:id=\"inputDeviceCode\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputContinues != null : "fx:id=\"inputContinues\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";
        assert inputUseId != null : "fx:id=\"inputUseId\" was not injected: check your FXML file 'RadioSocketForm.fxml'.";

        //Formularfelder initalisieren
        inputProtocol.setEditable(true);
        inputProtocol.getItems().addAll(
                ShcDesktopClient.getInstance().getSettings().getListSetting(Settings.SETTING_CLIENT_PROTOCOLS).getValue()
        );
        inputProtocol.getEditor().textProperty().addListener(e -> {

            if(!inputProtocol.isEditable()) {

                return;
            }

            String text = inputProtocol.getEditor().getText();
            inputProtocol.setValue(text);
        });
        for(int i = 1; i <= 10; i++) {

            inputContinues.getItems().add(i);
        }

        //Validieren
        validationSupport.registerValidator(inputName, Validator.createEmptyValidator("Der Name muss ausgefüllt werden"));
        validationSupport.registerValidator(inputSwitchServers, Validator.createEmptyValidator("Es muss mindestens ein Schaltserver ausgewählt werden"));
        validationSupport.registerValidator(inputProtocol, Validator.createEmptyValidator("Das Protokoll muss ausgewählt werden"));
        validationSupport.registerValidator(inputSystemCode, Validator.createEmptyValidator("Der Systemcode muss ausgefüllt werden"));
        validationSupport.registerValidator(inputDeviceCode, Validator.createEmptyValidator("Der Gerätecode muss ausgefüllt werden"));

        buttonSave.disableProperty().bind(validationSupport.invalidProperty());

    }

    public void setSwitchServers(Map<String, SwitchServer> switchServers) {
        this.switchServers = switchServers;

        //Schaltserver AUswahl initalisieren
        for(SwitchServer switchServer : switchServers.values()) {

            if(switchServer.isEnabled() && switchServer.isSend433MHzEnabled()) {

                inputSwitchServers.getItems().add(switchServer);
            }
        }
    }

    public RadioSocket getElement() {
        return element;
    }

    public void setElement(RadioSocket element) {

        this.element = element;
        if(element.getHash() == null || element.getHash().equals("")) {
            element.setHash(element.createHash());
        }

        //Daten setzen
        inputHash.setText(element.getHash());
        inputName.setText(element.getName());
        inputComment.setText(element.getComment());
        for(String switchServerHash : element.getSwitchServers()) {

            CheckModel<SwitchServer> switchServerCheckModel = inputSwitchServers.getCheckModel();
            for(int i = 0; i < switchServerCheckModel.getItemCount(); i++) {

                SwitchServer switchServer = inputSwitchServers.getItems().get(i);
                if(switchServer.getHash().equals(switchServerHash)) {

                    switchServerCheckModel.check(switchServer);
                }
            }
        }
        inputProtocol.setValue(element.getProtocol());
        inputSystemCode.setText(element.getSystemCode());
        inputDeviceCode.setText(element.getDeviceCode());
        inputContinues.setValue(element.getContinues());
        inputUseId.setSelected(element.isUseID());
        inputInverse.setSelected(element.isInverse());
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
