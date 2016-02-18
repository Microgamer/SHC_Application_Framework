package net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.Elements;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Benutzer Gruppen Formular
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserGroupFormController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="buttonCancel"
    private Button buttonCancel; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSave"
    private Button buttonSave; // Value injected by FXMLLoader

    @FXML // fx:id="inputHash"
    private TextField inputHash; // Value injected by FXMLLoader

    @FXML // fx:id="inputName"
    private TextField inputName; // Value injected by FXMLLoader

    @FXML // fx:id="inputDescription"
    private TextArea inputDescription; // Value injected by FXMLLoader

    @FXML // fx:id="inputPermissions"
    private CheckComboBox<String> inputPermissions; // Value injected by FXMLLoader

    /**
     * Benutzergruppe
     */
    protected UserGroupData userGroupData;

    /**
     * Berechtigungen (alle)
     */
    protected List<String> permissionList;

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

            userGroupData.setHash(inputHash.getText());
            userGroupData.setName(inputName.getText());
            userGroupData.setDescripion(inputDescription.getText());

            Set<String> permissions = new HashSet<>();
            IndexedCheckModel<String> checkModel = inputPermissions.getCheckModel();
            for(int i = 0; i < checkModel.getItemCount(); i++) {

                if(checkModel.isChecked(i)) {

                    String item = checkModel.getItem(i);
                    permissions.add(item);
                }
            }
            checkModel.clearChecks();
            userGroupData.getPermissions().clear();
            userGroupData.getPermissions().addAll(permissions);
            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            canceld = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'UserGroupForm.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'UserGroupForm.fxml'.";
        assert inputHash != null : "fx:id=\"inputHash\" was not injected: check your FXML file 'UserGroupForm.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'UserGroupForm.fxml'.";
        assert inputDescription != null : "fx:id=\"inputDescription\" was not injected: check your FXML file 'UserGroupForm.fxml'.";
        assert inputPermissions != null : "fx:id=\"inputPermissions\" was not injected: check your FXML file 'UserGroupForm.fxml'.";

        //Validatoren
        validationSupport.registerValidator(inputName, Validator.createEmptyValidator("Der Gruppenname muss ausgefüllt werden"));

        buttonSave.disableProperty().bind(validationSupport.invalidProperty());
    }

    /**
     * setzt die Liste mit allen Berechtigungen
     *
     * @param permissionList Berechtigungen
     */
    public void setPermissionList(List<String> permissionList) {

        this.permissionList = permissionList;
        for(String permission : permissionList) {

            inputPermissions.getItems().add(permission);
        }
    }

    /**
     * gibt die bearbeitete Gruppe zurück
     *
     * @return Benutzergruppe
     */
    public UserGroupData getUserGroupData() {
        return userGroupData;
    }

    /**
     * setzt die Gruppe
     *
     * @param userGroupData Benutzergruppe
     */
    public void setUserGroupData(UserGroupData userGroupData) {

        this.userGroupData = userGroupData;
        if(userGroupData.getHash() == null || userGroupData.getHash().equals("")) {
            userGroupData.setHash(UserGroupData.createHash());
        }

        inputHash.setText(userGroupData.getHash());
        inputName.setText(userGroupData.getName());
        inputDescription.setText(userGroupData.getDescripion());
        Set<String> groupPermissions = userGroupData.getPermissions();

        IndexedCheckModel<String> checkModel = inputPermissions.getCheckModel();
        checkModel.clearChecks();
        for(int i = 0; i < checkModel.getItemCount(); i++) {

            String item = checkModel.getItem(i);
            if(groupPermissions.contains(item)) {

                checkModel.check(i);
                break;
            }
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
