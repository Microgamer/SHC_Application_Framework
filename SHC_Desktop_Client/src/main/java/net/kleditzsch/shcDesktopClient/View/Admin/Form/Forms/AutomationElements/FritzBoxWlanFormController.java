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
import net.kleditzsch.shcCore.Automation.Devices.Switchable.FritzBoxWirelessLan;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Fritz!Box Wlan
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class FritzBoxWlanFormController {

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

    @FXML // fx:id="inputWlan"
    private ChoiceBox<String> inputWlan; // Value injected by FXMLLoader

    /**
     * Datenelement
     */
    protected FritzBoxWirelessLan element;

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
            switch (inputWlan.getValue()) {

                case "2,4GHz WLan":

                    element.setFunction(FritzBoxWirelessLan.FUNCTION_SWITCH_2GHZ_WLAN);
                    break;
                case "5GHz WLan":

                    element.setFunction(FritzBoxWirelessLan.FUNCTION_SWITCH_5GHZ_WLAN);
                    break;
                case "Gäste WLan":

                    element.setFunction(FritzBoxWirelessLan.FUNCTION_SWITCH_GUEST_WLAN);
                    break;
            }
            element.setDisabled(inputDisabled.isSelected());

            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert formGridPane != null : "fx:id=\"formGridPane\" was not injected: check your FXML file 'FritzBoxWlanForm.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'FritzBoxWlanForm.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'FritzBoxWlanForm.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'FritzBoxWlanForm.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'FritzBoxWlanForm.fxml'.";
        assert inputComment != null : "fx:id=\"inputComment\" was not injected: check your FXML file 'FritzBoxWlanForm.fxml'.";
        assert inputDisabled != null : "fx:id=\"inputDisabled\" was not injected: check your FXML file 'FritzBoxWlanForm.fxml'.";
        assert inputWlan != null : "fx:id=\"inputWlan\" was not injected: check your FXML file 'FritzBoxWlanForm.fxml'.";

        //Formularfelder initalisieren
        inputWlan.getItems().addAll(
                "2,4GHz WLan",
                "5GHz WLan",
                "Gäste WLan"
        );

        //Validieren
        validationSupport.registerValidator(inputName, Validator.createEmptyValidator("Der Name muss ausgefüllt werden"));
        validationSupport.registerValidator(inputWlan, Validator.createEmptyValidator("Das WLan muss ausgewählt werden"));

        buttonSave.disableProperty().bind(validationSupport.invalidProperty());
    }

    public FritzBoxWirelessLan getElement() {
        return element;
    }

    public void setElement(FritzBoxWirelessLan element) {

        this.element = element;
        if(element.getHash() == null || element.getHash().equals("")) {
            element.setHash(element.createHash());
        }

        //Daten setzen
        inputHash.setText(element.getHash());
        inputName.setText(element.getName());
        inputComment.setText(element.getComment());
        switch (element.getFunction()) {

            case FritzBoxWirelessLan.FUNCTION_SWITCH_5GHZ_WLAN:

                inputWlan.setValue("5GHz WLan");
                break;
            case FritzBoxWirelessLan.FUNCTION_SWITCH_GUEST_WLAN:

                inputWlan.setValue("Gäste WLan");
                break;
            case FritzBoxWirelessLan.FUNCTION_SWITCH_2GHZ_WLAN:
            default:

                inputWlan.setValue("2,4GHz WLan");
                break;

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
