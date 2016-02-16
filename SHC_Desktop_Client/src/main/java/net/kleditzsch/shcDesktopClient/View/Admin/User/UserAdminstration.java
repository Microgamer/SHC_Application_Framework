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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.ClientData.User.UserAdministrationResponse;
import net.kleditzsch.shcCore.ClientData.User.UserData;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.Util.UiNotificationHelper;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.FormDialogManager;
import net.kleditzsch.shcDesktopClient.View.MainViewLoader;
import org.controlsfx.control.MaskerPane;

/**
 * Benutzerverwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserAdminstration {

    private static class OriginatorCell extends TableCell<UserData, Boolean> {

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

    @FXML // fx:id="roodBorderPane"
    private BorderPane roodBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="buttonBack"
    private Button buttonBack; // Value injected by FXMLLoader

    @FXML // fx:id="buttonRefresh"
    private Button buttonRefresh; // Value injected by FXMLLoader

    @FXML // fx:id="buttonUserGroups"
    private Button buttonUserGroups; // Value injected by FXMLLoader

    @FXML // fx:id="userTable"
    private TableView<UserData> userTable; // Value injected by FXMLLoader

    @FXML // fx:id="columnUserName"
    private TableColumn<UserData, String> columnUserName; // Value injected by FXMLLoader

    @FXML // fx:id="columnUserOriginator"
    private TableColumn<UserData, Boolean> columnUserOriginator; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonCreateUser"
    private MenuItem menuButtonCreateUser; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonEditUser"
    private MenuItem menuButtonEditUser; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonDeleteUser"
    private MenuItem menuButtonDeleteUser; // Value injected by FXMLLoader

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

        MainViewLoader.loadAdminMenueView();
    }

    /**
     * Benutzer erstellen
     *
     * @param event
     */
    @FXML
    void createUser(ActionEvent event) {

        UserData userData = new UserData();
        Optional<UserData> userDataOptional = FormDialogManager.showUserDataDialog(userData, userAdministrationResponse.getGroupDataList());

        if(userDataOptional.isPresent()) {

            //Anfrage senden
            Task<SuccessResponse> task = new Task<SuccessResponse>() {
                @Override
                protected SuccessResponse call() throws Exception {

                    try {

                        return ShcDesktopClient.getInstance().getConnectionManager().addUser(userDataOptional.get());
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

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Der Benutzer wurde erfolgreich erstellt");
                    update();
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Benutzer erstellen fehlgeschlagen", successResponse.getMessage());
                    if(successResponse.getErrorCode() == 100) {

                        ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                    }
                }
            });
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    /**
     * Benutzer löschen
     *
     * @param event
     */
    @FXML
    void deleteUser(ActionEvent event) {

        if(UiDialogHelper.showConfirmDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Benutzer löschen", null, "Bist du sicher das du den Benutzer löschen willst?")) {

            int index = userTable.getSelectionModel().getFocusedIndex();
            UserData userData = userTable.getItems().get(index);

            //Anfrage senden
            Task<SuccessResponse> task = new Task<SuccessResponse>() {
                @Override
                protected SuccessResponse call() throws Exception {

                    try {

                        return ShcDesktopClient.getInstance().getConnectionManager().deleteUser(userData);
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

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Der Benutzer wurde erfolgreich gelöscht");
                    update();
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Benutzer löschen fehlgeschlagen", successResponse.getMessage());
                    if(successResponse.getErrorCode() == 100) {

                        ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                    }
                }
            });
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    /**
     * Benutzer bearbeiten
     *
     * @param event
     */
    @FXML
    void editUser(ActionEvent event) {

        int index = userTable.getSelectionModel().getFocusedIndex();
        UserData userData = userTable.getItems().get(index);
        Optional<UserData> userDataOptional = FormDialogManager.showUserDataDialog(userData, userAdministrationResponse.getGroupDataList());

        if(userDataOptional.isPresent()) {

            //Anfrage senden
            Task<SuccessResponse> task = new Task<SuccessResponse>() {
                @Override
                protected SuccessResponse call() throws Exception {

                    try {

                        return ShcDesktopClient.getInstance().getConnectionManager().editUser(userDataOptional.get());
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

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Der Benutzer wurde erfolgreich bearbeitet");
                    update();
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Benutzer bearbeiten fehlgeschlagen", successResponse.getMessage());
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
    void openUserGroupAdmistration(ActionEvent event) {

        MainViewLoader.loadUserGroupAdministartionView();
    }

    @FXML
    void refreshList(ActionEvent event) {

        update();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert roodBorderPane != null : "fx:id=\"roodBorderPane\" was not injected: check your FXML file 'UserAdminstration.fxml'.";
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'UserAdminstration.fxml'.";
        assert buttonUserGroups != null : "fx:id=\"buttonUserGroups\" was not injected: check your FXML file 'UserAdminstration.fxml'.";
        assert userTable != null : "fx:id=\"userTable\" was not injected: check your FXML file 'UserAdminstration.fxml'.";
        assert columnUserName != null : "fx:id=\"columnUserName\" was not injected: check your FXML file 'UserAdminstration.fxml'.";
        assert columnUserOriginator != null : "fx:id=\"columnUserOriginator\" was not injected: check your FXML file 'UserAdminstration.fxml'.";

        //Spaltenbreite
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        columnUserName.setMaxWidth(1f * Integer.MAX_VALUE * 70);
        columnUserOriginator.setMaxWidth(1f * Integer.MAX_VALUE * 30);

        //Spalte Benutzername
        columnUserName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnUserOriginator.setCellValueFactory(new PropertyValueFactory<>("Originator"));
        columnUserOriginator.setCellFactory(param -> new OriginatorCell());

        //Optionen erst einmal deaktivieren
        menuButtonCreateUser.setDisable(true);
        menuButtonEditUser.setDisable(true);
        menuButtonDeleteUser.setDisable(true);

        //Ladeanzeige
        rootStackPane.getChildren().add(maskerPane);
        maskerPane.setText("Bitte warten ...");

        update();
    }

    /**
     * läadt die Benutzer und zeigt sie in der Tabelle an
     */
    protected void update() {

        maskerPane.setVisible(true);

        menuButtonCreateUser.setDisable(true);
        menuButtonEditUser.setDisable(true);
        menuButtonDeleteUser.setDisable(true);

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

                    userTable.getItems().clear();
                    userTable.getItems().addAll(FXCollections.observableList(userAdministrationResponse.getUserDataList()));
                    maskerPane.setVisible(false);
                    menuButtonCreateUser.setDisable(false);
                    menuButtonEditUser.setDisable(false);
                    menuButtonDeleteUser.setDisable(false);
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
