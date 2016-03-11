package net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.kleditzsch.shcCore.Automation.Devices.SensorValue.*;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.VirtualSensorValue;
import net.kleditzsch.shcCore.Core.BasicElement;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.CheckModel;
import org.controlsfx.validation.ValidationSupport;

/**
 * Fromular Virtuelle Sensoren
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualSensorFormController {

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

    @FXML // fx:id="inputSensorValues"
    private CheckComboBox<SensorValue> inputSensorValues; // Value injected by FXMLLoader

    /**
     * Typ des Virtuellen Sensors
     */
    protected int virtualSensorType;

    /**
     * Datenelement
     */
    protected VirtualSensorValue element;

    /**
     * Liste der Schaltserver
     */
    protected List<SensorValue> sensorValues;

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
            CheckModel<SensorValue> checkModel = inputSensorValues.getCheckModel();
            element.getSensorValues().clear();
            for(SensorValue sensorValue : checkModel.getCheckedItems()) {

                element.getSensorValues().add(sensorValue.getHash());
            }
            element.setDisabled(inputDisabled.isSelected());

            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert formGridPane != null : "fx:id=\"formGridPane\" was not injected: check your FXML file 'VirtualSensorValueForm.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'VirtualSensorValueForm.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'VirtualSensorValueForm.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'VirtualSensorValueForm.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'VirtualSensorValueForm.fxml'.";
        assert inputComment != null : "fx:id=\"inputComment\" was not injected: check your FXML file 'VirtualSensorValueForm.fxml'.";
        assert inputDisabled != null : "fx:id=\"inputDisabled\" was not injected: check your FXML file 'VirtualSensorValueForm.fxml'.";
        assert inputSensorValues != null : "fx:id=\"inputSensorValues\" was not injected: check your FXML file 'VirtualSensorValueForm.fxml'.";

    }

    public void setVirtualSensorType(int virtualSensorType) {
        this.virtualSensorType = virtualSensorType;
    }

    public void setSensorValues(List<SensorValue> sensorValues) {

        this.sensorValues = sensorValues;
        for(SensorValue sensorValue : sensorValues) {

            switch (virtualSensorType) {

                case VirtualSensorValue.VIRTUAL_ACTUAL_POWER:

                    if(sensorValue instanceof ActualPowerValue) {

                        inputSensorValues.getItems().add(sensorValue);
                    }
                    break;
                case VirtualSensorValue.VIRTUAL_ENERGY:

                    if(sensorValue instanceof EnergyValue) {

                        inputSensorValues.getItems().add(sensorValue);
                    }
                    break;
                case VirtualSensorValue.VIRTUAL_GAS_AMOUNT:

                    if(sensorValue instanceof GasAmountValue) {

                        inputSensorValues.getItems().add(sensorValue);
                    }
                    break;
                case VirtualSensorValue.VIRTUAL_WATER_AMOUNT:

                    if(sensorValue instanceof WaterAmountValue) {

                        inputSensorValues.getItems().add(sensorValue);
                    }
                    break;
                case VirtualSensorValue.VIRTUAL_LIGHT_INTENSITY:

                    if(sensorValue instanceof LightIntensityValue) {

                        inputSensorValues.getItems().add(sensorValue);
                    }
                    break;
                case VirtualSensorValue.VIRTUAL_TEMPERATURE:

                    if(sensorValue instanceof TemperatureValue) {

                        inputSensorValues.getItems().add(sensorValue);
                    }
                    break;
            }
        }
    }

    public VirtualSensorValue getElement() {
        return element;
    }

    public void setElement(VirtualSensorValue element) {

        this.element = element;
        if(element.getHash() == null || element.getHash().equals("")) {
            element.setHash(BasicElement.createHash());
        }

        //Daten setzen
        inputHash.setText(element.getHash());
        inputName.setText(element.getName());
        inputComment.setText(element.getComment());

        CheckModel<SensorValue> checkModel = inputSensorValues.getCheckModel();
        checkModel.clearChecks();
        for(String hash : element.getSensorValues()) {

            for(SensorValue sensorValue : inputSensorValues.getItems()) {

                if(sensorValue.getHash().equals(hash)) {

                    checkModel.check(sensorValue);
                }
            }
        }
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
