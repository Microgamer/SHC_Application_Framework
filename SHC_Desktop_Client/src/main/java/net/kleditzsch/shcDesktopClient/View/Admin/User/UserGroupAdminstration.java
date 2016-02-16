package net.kleditzsch.shcDesktopClient.View.Admin.User;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.ClientData.User.UserAdministrationResponse;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.Util.UiNotificationHelper;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.FormDialogManager;
import net.kleditzsch.shcDesktopClient.View.MainViewLoader;
import org.controlsfx.control.MaskerPane;

/**
 * Benutzergruppen Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserGroupAdminstration {

    private static class SystemCell extends TableCell<UserGroupData, Boolean> {

        /**
         * @param item  The new item for the cell.
         * @param empty whether or not this cell represents data from the list. If it
         *              is empty, then it does not represent any domain data, but is a cell
         *              being used to render an "empty" row.
         * @expert
         */
        @Override
        protected void updateItem(Boolean item, boolean empty) {

            if(item != null) {

                if (item.booleanValue()) {

                    setText("ja");
                } else {

                    setText(("nein"));
                }
            } else {

                setText("");
            }
        }
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootStackPane"
    private StackPane rootStackPane; // Value injected by FXMLLoader

    @FXML // fx:id="buttonBack"
    private Button buttonBack; // Value injected by FXMLLoader

    @FXML // fx:id="buttonRefresh"
    private Button buttonRefresh; // Value injected by FXMLLoader

    @FXML // fx:id="groupTable"
    private TableView<UserGroupData> groupTable; // Value injected by FXMLLoader

    @FXML // fx:id="columnGroupName"
    private TableColumn<UserGroupData, String> columnGroupName; // Value injected by FXMLLoader

    @FXML // fx:id="columnSystemGroup"
    private TableColumn<UserGroupData, Boolean> columnSystemGroup; // Value injected by FXMLLoader

    @FXML // fx:id="columnGroupDescription"
    private TableColumn<UserGroupData, String> columnGroupDescription; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonCreateGroup"
    private MenuItem menuButtonCreateGroup; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonEditGroup"
    private MenuItem menuButtonEditGroup; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonDeleteGroup"
    private MenuItem menuButtonDeleteGroup; // Value injected by FXMLLoader

    /**
     * Ladeanzeige
     */
    private MaskerPane maskerPane = new MaskerPane();

    /**
     * Response
     */
    protected UserAdministrationResponse userAdministrationResponse;

    @FXML
    void back(ActionEvent event) {

        MainViewLoader.loadUserAdministartionView();
    }

    @FXML
    void createGroup(ActionEvent event) {

        UserGroupData userGroupDataData = new UserGroupData();
        Optional<UserGroupData> userDataOptional = FormDialogManager.showUserGroupDataDialog(userGroupDataData, userAdministrationResponse.getPermissions());

        if(userDataOptional.isPresent()) {

            //Anfrage senden
            Task<SuccessResponse> task = new Task<SuccessResponse>() {
                @Override
                protected SuccessResponse call() throws Exception {

                    try {

                        return ShcDesktopClient.getInstance().getConnectionManager().addUserGroup(userDataOptional.get());
                    } catch (IOException e) {

                        SuccessResponse sr = new SuccessResponse();
                        sr.setSuccess(false);
                        sr.setMessage(e.getLocalizedMessage());
                        return sr;
                    }
                }
            };
            task.setOnSucceeded((WorkerStateEvent e) -> {

                SuccessResponse successResponse = (SuccessResponse) e.getSource().getValue();
                if(successResponse.isSuccess()) {

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Die Benutzergruppe wurde erfolgreich erstellt");
                    updateList();
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Benutzergruppe erstellen fehlgeschlagen", successResponse.getMessage());
                    if(successResponse.getErrorCode() == 100) {

                        ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                    }
                }
            });
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    @FXML
    void deleteGroup(ActionEvent event) {

        if(UiDialogHelper.showConfirmDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Benutzergruppe löschen", null, "Bist du sicher das du die Benutzergruppe löschen willst?")) {

            int index = groupTable.getSelectionModel().getFocusedIndex();
            UserGroupData userGroupData = groupTable.getItems().get(index);

            //Anfrage senden
            Task<SuccessResponse> task = new Task<SuccessResponse>() {
                @Override
                protected SuccessResponse call() throws Exception {

                    try {

                        return ShcDesktopClient.getInstance().getConnectionManager().deleteUserGroup(userGroupData);
                    } catch (IOException e) {

                        SuccessResponse sr = new SuccessResponse();
                        sr.setSuccess(false);
                        sr.setMessage(e.getLocalizedMessage());
                        return sr;
                    }
                }
            };
            task.setOnSucceeded((WorkerStateEvent e) -> {

                SuccessResponse successResponse = (SuccessResponse) e.getSource().getValue();
                if(successResponse.isSuccess()) {

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Die Benutzergruppe wurde erfolgreich gelöscht");
                    updateList();
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Benutzergruppe löschen fehlgeschlagen", successResponse.getMessage());
                    if(successResponse.getErrorCode() == 100) {

                        ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                    }
                }
            });
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    @FXML
    void editGroup(ActionEvent event) {

        int index = groupTable.getSelectionModel().getFocusedIndex();
        UserGroupData userGroupDataData = groupTable.getItems().get(index);
        Optional<UserGroupData> userDataOptional = FormDialogManager.showUserGroupDataDialog(userGroupDataData, userAdministrationResponse.getPermissions());

        if(userDataOptional.isPresent()) {

            //Anfrage senden
            Task<SuccessResponse> task = new Task<SuccessResponse>() {
                @Override
                protected SuccessResponse call() throws Exception {

                    try {

                        return ShcDesktopClient.getInstance().getConnectionManager().editUserGroup(userDataOptional.get());
                    } catch (IOException e) {

                        SuccessResponse sr = new SuccessResponse();
                        sr.setSuccess(false);
                        sr.setMessage(e.getLocalizedMessage());
                        return sr;
                    }
                }
            };
            task.setOnSucceeded((WorkerStateEvent e) -> {

                SuccessResponse successResponse = (SuccessResponse) e.getSource().getValue();
                if(successResponse.isSuccess()) {

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Die Benutzergruppe wurde erfolgreich bearbeitet");
                    updateList();
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Benutzergruppe bearbeiten fehlgeschlagen", successResponse.getMessage());
                    if(successResponse.getErrorCode() == 100) {

                        ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                    }
                }
            });
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    @FXML
    void refreshList(ActionEvent event) {

        updateList();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootStackPane != null : "fx:id=\"rootStackPane\" was not injected: check your FXML file 'UserGroupAdministarion.fxml'.";
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'UserGroupAdministarion.fxml'.";
        assert buttonRefresh != null : "fx:id=\"buttonRefresh\" was not injected: check your FXML file 'UserGroupAdministarion.fxml'.";
        assert groupTable != null : "fx:id=\"groupTable\" was not injected: check your FXML file 'UserGroupAdministarion.fxml'.";
        assert columnGroupName != null : "fx:id=\"columnGroupName\" was not injected: check your FXML file 'UserGroupAdministarion.fxml'.";
        assert columnSystemGroup != null : "fx:id=\"columnSystemGroup\" was not injected: check your FXML file 'UserGroupAdministarion.fxml'.";
        assert columnGroupDescription != null : "fx:id=\"columnGroupDescription\" was not injected: check your FXML file 'UserGroupAdministarion.fxml'.";
        assert menuButtonCreateGroup != null : "fx:id=\"menuButtonCreateGroup\" was not injected: check your FXML file 'UserGroupAdministarion.fxml'.";
        assert menuButtonEditGroup != null : "fx:id=\"menuButtonEditGroup\" was not injected: check your FXML file 'UserGroupAdministarion.fxml'.";
        assert menuButtonDeleteGroup != null : "fx:id=\"menuButtonDeleteGroup\" was not injected: check your FXML file 'UserGroupAdministarion.fxml'.";

        //Spaltenbreite
        groupTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        columnGroupName.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        columnSystemGroup.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        columnGroupDescription.setMaxWidth(1f * Integer.MAX_VALUE * 50);

        //Spalte Benutzername
        columnGroupName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnSystemGroup.setCellValueFactory(new PropertyValueFactory<>("SystemGroup"));
        columnSystemGroup.setCellFactory(param -> new SystemCell());
        columnGroupDescription.setCellValueFactory(new PropertyValueFactory<>("Descripion"));

        //Optionen erst einmal deaktivieren
        menuButtonCreateGroup.setDisable(true);
        menuButtonEditGroup.setDisable(true);
        menuButtonDeleteGroup.setDisable(true);

        //Ladeanzeige
        rootStackPane.getChildren().add(maskerPane);
        maskerPane.setText("Bitte warten ...");

        updateList();
    }

    protected void updateList() {

        maskerPane.setVisible(true);

        //Benutzerdaten laden
        Task<UserAdministrationResponse> task = new Task<UserAdministrationResponse>() {
            @Override
            protected UserAdministrationResponse call() throws Exception {

                try {

                    return ShcDesktopClient.getInstance().getConnectionManager().getUsersAndGroups();
                } catch (IOException e) {

                    UserAdministrationResponse uar = new UserAdministrationResponse();
                    uar.setSuccess(false);
                    uar.setMessage(e.getLocalizedMessage());
                    return uar;
                }
            }
        };
        task.setOnSucceeded((WorkerStateEvent event) -> {

            userAdministrationResponse = (UserAdministrationResponse) event.getSource().getValue();
            if(userAdministrationResponse.isSuccess()) {

                //Daten
                if(userAdministrationResponse != null) {

                    groupTable.getItems().clear();
                    groupTable.getItems().addAll(FXCollections.observableList(userAdministrationResponse.getGroupDataList()));
                    maskerPane.setVisible(false);
                    menuButtonCreateGroup.setDisable(false);
                    menuButtonEditGroup.setDisable(false);
                    menuButtonDeleteGroup.setDisable(false);
                }
            } else {

                UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Fehler", userAdministrationResponse.getMessage());
                if(userAdministrationResponse.getErrorCode() == 100) {

                    ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                }
            }
        });
        Thread thread = new Thread(task);
        thread.start();
    }
}
