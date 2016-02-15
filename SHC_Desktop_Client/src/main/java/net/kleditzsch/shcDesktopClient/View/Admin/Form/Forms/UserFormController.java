package net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.kleditzsch.shcCore.ClientData.User.UserData;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;
import net.kleditzsch.shcCore.User.UserGroup;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import org.controlsfx.tools.ValueExtractor;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Benutzer Formular
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserFormController {

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

    @FXML // fx:id="intutAuthHash"
    private TextField intutAuthHash; // Value injected by FXMLLoader@FXML

    @FXML // fx:id="inputGroups"
    private CheckComboBox<String> inputGroups;

    @FXML // fx:id="buttonNewHash"
    private Button buttonNewHash; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCancel"
    private Button buttonCancel; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSave"
    private Button buttonSave; // Value injected by FXMLLoader

    /**
     * Benutzer
     */
    protected UserData userData;

    /**
     * Benutzergruppen
     */
    protected List<UserGroupData> userGrouoDataList;

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
    void generateNewAuthHash(ActionEvent event) {

        String newHash = UserData.createHash().substring(0, 11);
        intutAuthHash.setText(newHash);
    }

    @FXML
    void save(ActionEvent event) {

        if(!validationSupport.isInvalid()) {

            userData.setHash(inputHash.getText());
            userData.setName(inputName.getText());
            userData.setPasswordHash(intutAuthHash.getText());

            Set<String> userGroups = new HashSet<>();
            IndexedCheckModel<String> checkModel = inputGroups.getCheckModel();
            for(int i = 0; i < checkModel.getItemCount(); i++) {

                if(checkModel.isChecked(i)) {

                    String item = checkModel.getItem(i);
                    for(UserGroupData userGroupData : userGrouoDataList) {

                        if(item.equalsIgnoreCase(userGroupData.getName())) {

                            userGroups.add(userGroupData.getHash());
                        }
                    }
                }
            }
            checkModel.clearChecks();
            userData.getUserGroups().clear();
            userData.getUserGroups().addAll(userGroups);
            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert formGridPane != null : "fx:id=\"formGridPane\" was not injected: check your FXML file 'UserForm.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'UserForm.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'UserForm.fxml'.";
        assert intutAuthHash != null : "fx:id=\"intutAuthHash\" was not injected: check your FXML file 'UserForm.fxml'.";
        assert inputGroups != null : "fx:id=\"inputGroups\" was not injected: check your FXML file 'UserForm.fxml'.";
        assert buttonNewHash != null : "fx:id=\"buttonNewHash\" was not injected: check your FXML file 'UserForm.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'UserForm.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'UserForm.fxml'.";

        //Validatoren
        validationSupport.registerValidator(inputName, Validator.createEmptyValidator("Der Benutzername muss ausgefüllt werden"));
        validationSupport.registerValidator(inputGroups, Validator.createEmptyValidator("es muss Mindestens eine Benutzergruppe gewählt werden"));

        buttonSave.disableProperty().bind(validationSupport.invalidProperty());
    }

    public void setGroups(List<UserGroupData> userGroupDataList) {

        userGrouoDataList = userGroupDataList;
        for(UserGroupData userGroupData : userGroupDataList) {

            inputGroups.getItems().add(userGroupData.getName());
        }
    }

    public void setUser(UserData userData) {

        this.userData = userData;
        if(userData.getHash() == null || userData.getHash().equals("")) {
            userData.setHash(UserData.createHash());
            userData.setPasswordHash(UserData.createHash().substring(0, 11));
        }

        inputHash.setText(userData.getHash());
        inputName.setText(userData.getName());
        intutAuthHash.setText(userData.getPasswordHash());
        Set<String> userGroups = userData.getUserGroups();

        IndexedCheckModel<String> checkModel = inputGroups.getCheckModel();
        checkModel.clearChecks();
        for(int i = 0; i < checkModel.getItemCount(); i++) {

            String item = checkModel.getItem(i);
            for(UserGroupData userGroupData : userGrouoDataList) {

                if(item.equals(userGroupData.getName()) && userGroups.contains(userGroupData.getHash())) {

                    checkModel.check(i);
                    break;
                }
            }
        }
    }

    public UserData getUser() {

        return this.userData;
    }

    public boolean isCanceld() {
        return canceld;
    }
}
