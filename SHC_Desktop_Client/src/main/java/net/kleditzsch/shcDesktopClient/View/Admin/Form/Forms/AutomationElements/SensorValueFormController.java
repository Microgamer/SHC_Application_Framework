package net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;
import net.kleditzsch.shcCore.Core.BasicElement;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Sensorwerte Formular
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SensorValueFormController {

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

    @FXML // fx:id="buttonIdentifier"
    private Button buttonIdentifier; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCancel"
    private Button buttonCancel; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSave"
    private Button buttonSave; // Value injected by FXMLLoader

    @FXML // fx:id="inputComment"
    private TextField inputComment; // Value injected by FXMLLoader

    @FXML // fx:id="inputDisabled"
    private CheckBox inputDisabled; // Value injected by FXMLLoader

    @FXML // fx:id="inputIdentifier"
    private TextField inputIdentifier; // Value injected by FXMLLoader

    @FXML // fx:id="InputLastContact"
    private TextField inputLastContact; // Value injected by FXMLLoader

    @FXML // fx:id="inputValue"
    private TextField inputValue; // Value injected by FXMLLoader

    /**
     * Datenelement
     */
    protected SensorValue element;

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
    void creatNewIdentifier(ActionEvent event) {

        String identifier = BasicElement.createHash().substring(0, 10);
        inputIdentifier.setText(identifier);
    }

    @FXML
    void save(ActionEvent event) {

        if(!validationSupport.isInvalid()) {

            element.setHash(inputHash.getText());
            element.setName(inputName.getText());
            element.setComment(inputComment.getText());
            element.setIdentifier(inputIdentifier.getText());
            element.setDisabled(inputDisabled.isSelected());

            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert formGridPane != null : "fx:id=\"formGridPane\" was not injected: check your FXML file 'SensorValueForm.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'SensorValueForm.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'SensorValueForm.fxml'.";
        assert buttonIdentifier != null : "fx:id=\"buttonIdentifier\" was not injected: check your FXML file 'SensorValueForm.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'SensorValueForm.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'SensorValueForm.fxml'.";
        assert inputComment != null : "fx:id=\"inputComment\" was not injected: check your FXML file 'SensorValueForm.fxml'.";
        assert inputDisabled != null : "fx:id=\"inputDisabled\" was not injected: check your FXML file 'SensorValueForm.fxml'.";
        assert inputIdentifier != null : "fx:id=\"inputIdentifier\" was not injected: check your FXML file 'SensorValueForm.fxml'.";
        assert inputLastContact != null : "fx:id=\"inputLastContact\" was not injected: check your FXML file 'SensorValueForm.fxml'.";
        assert inputValue != null : "fx:id=\"inputValue\" was not injected: check your FXML file 'SensorValueForm.fxml'.";

        //Validieren
        validationSupport.registerValidator(inputName, Validator.createEmptyValidator("Der Name muss ausgefüllt werden"));
        validationSupport.registerValidator(inputIdentifier, Validator.createEmptyValidator("Der Identifizierer muss ausgefüllt werden"));

        buttonSave.disableProperty().bind(validationSupport.invalidProperty());
    }

    public SensorValue getElement() {
        return element;
    }

    public void setElement(SensorValue element) {

        this.element = element;
        if(element.getHash() == null || element.getHash().equals("")) {
            element.setHash(BasicElement.createHash());
            element.setIdentifier(BasicElement.createHash().substring(0, 10));
        }

        //Daten setzen
        inputHash.setText(element.getHash());
        inputName.setText(element.getName());
        inputComment.setText(element.getComment());
        inputIdentifier.setText(element.getIdentifier());
        if(element.getLastPushTime() != null) {

            inputLastContact.setText(element.getLastPushTime().toString());
        } else {

            inputLastContact.setText("nie");
        }
        inputValue.setText(element.getDisplayValue());
        inputDisabled.setSelected(element.isDisabled());

        if(element.isSystemValue()) {

            inputIdentifier.setDisable(true);
        }
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
