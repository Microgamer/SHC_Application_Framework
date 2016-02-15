package net.kleditzsch.shcDesktopClient.View.Admin.Form;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcCore.ClientData.User.UserData;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.UserFormController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Dialogverwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class FormDialogManager {

    /**
     * erzeugt ein Modales Dialogfenster
     *
     * @return Dialog Stage
     */
    protected static Stage createModalDialog() {

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(ShcDesktopClient.getInstance().getPrimaryStage());
        return dialog;
    }

    /**
     * öffnet den Benutzer Formular Dialog
     *
     * @param userData Benutzer Daten
     * @param userGroupDataList Liste der Benutzergruppen
     * @return Benutzer Daten
     */
    public static Optional<UserData> showUserDataDialog(UserData userData, List<UserGroupData> userGroupDataList) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/UserForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Benutzer Formular");
            UserFormController userFormController = loader.getController();
            userFormController.setGroups(userGroupDataList);
            userFormController.setUser(userData);
            dialog.showAndWait();

            if(!userFormController.isCanceld()) {

                return Optional.of(userFormController.getUser());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }
}
