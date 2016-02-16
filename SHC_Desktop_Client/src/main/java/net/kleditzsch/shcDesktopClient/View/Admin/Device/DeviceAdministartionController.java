package net.kleditzsch.shcDesktopClient.View.Admin.Device;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.WindowEvent;
import net.kleditzsch.shcCore.ClientData.Device.DeviceData;
import net.kleditzsch.shcCore.ClientData.Device.DeviceResponse;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.Util.UiNotificationHelper;
import net.kleditzsch.shcDesktopClient.View.MainViewLoader;
import org.controlsfx.control.MaskerPane;

/**
 * Geräteverwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DeviceAdministartionController {

    private static class AllowedCell extends TableCell<DeviceData, Boolean> {

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

                    setText("erlaubt");
                } else {

                    setText(("verweigert"));
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

    @FXML // fx:id="deviceTable"
    private TableView<DeviceData> deviceTable; // Value injected by FXMLLoader

    @FXML // fx:id="columnUserAgent"
    private TableColumn<DeviceData, String> columnUserAgent; // Value injected by FXMLLoader

    @FXML // fx:id="columnAllowed"
    private TableColumn<DeviceData, Boolean> columnAllowed; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonAllow"
    private MenuItem menuButtonAllow; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonDenied"
    private MenuItem menuButtonDenied; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonDelete"
    private MenuItem menuButtonDelete; // Value injected by FXMLLoader

    /**
     * Ladeanzeige
     */
    private MaskerPane maskerPane = new MaskerPane();

    /**
     * Response
     */
    protected DeviceResponse deviceResponse;

    @FXML
    void allowDevice(ActionEvent event) {

        int index = deviceTable.getSelectionModel().getFocusedIndex();
        DeviceData deviceData = deviceTable.getItems().get(index);
        if(deviceData != null) {

            //Anfrage senden
            Task<SuccessResponse> task = new Task<SuccessResponse>() {
                @Override
                protected SuccessResponse call() throws Exception {

                    try {

                        return ShcDesktopClient.getInstance().getConnectionManager().allowDevice(deviceData);
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

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Das Gerät wurde erfolgreich aktiviert");
                    update();
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Gerät aktivieren fehlgeschlagen", successResponse.getMessage());
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
    void deniedDevice(ActionEvent event) {

        int index = deviceTable.getSelectionModel().getFocusedIndex();
        DeviceData deviceData = deviceTable.getItems().get(index);
        if(deviceData != null) {

            //Anfrage senden
            Task<SuccessResponse> task = new Task<SuccessResponse>() {
                @Override
                protected SuccessResponse call() throws Exception {

                    try {

                        return ShcDesktopClient.getInstance().getConnectionManager().denyDevice(deviceData);
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

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Das Gerät wurde erfolgreich aktiviert");
                    update();
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Gerät aktivieren fehlgeschlagen", successResponse.getMessage());
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
    void deleteDevice(ActionEvent event) {

        int index = deviceTable.getSelectionModel().getFocusedIndex();
        DeviceData deviceData = deviceTable.getItems().get(index);
        if(deviceData != null) {

            //Anfrage senden
            Task<SuccessResponse> task = new Task<SuccessResponse>() {
                @Override
                protected SuccessResponse call() throws Exception {

                    try {

                        return ShcDesktopClient.getInstance().getConnectionManager().deleteDevice(deviceData);
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

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Das Gerät wurde erfolgreich gelöscht");
                    update();
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Gerät löschen fehlgeschlagen", successResponse.getMessage());
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

        update();
    }

    @FXML
    void back(ActionEvent event) {

        MainViewLoader.loadAdminMenueView();
    }

    @FXML
    void showingContextMenu(WindowEvent event) {

        int index = deviceTable.getSelectionModel().getFocusedIndex();
        DeviceData deviceData = deviceTable.getItems().get(index);
        if(deviceData != null) {

            if(deviceData.isAllowed()) {

                menuButtonAllow.setDisable(true);
                menuButtonDenied.setDisable(false);
                menuButtonDelete.setDisable(false);
            } else {

                menuButtonAllow.setDisable(false);
                menuButtonDenied.setDisable(true);
                menuButtonDelete.setDisable(false);
            }
        } else {

            menuButtonAllow.setDisable(true);
            menuButtonDenied.setDisable(true);
            menuButtonDelete.setDisable(true);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootStackPane != null : "fx:id=\"rootStackPane\" was not injected: check your FXML file 'DeviceAdministration.fxml'.";
        assert roodBorderPane != null : "fx:id=\"roodBorderPane\" was not injected: check your FXML file 'DeviceAdministration.fxml'.";
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'DeviceAdministration.fxml'.";
        assert buttonRefresh != null : "fx:id=\"buttonRefresh\" was not injected: check your FXML file 'DeviceAdministration.fxml'.";
        assert deviceTable != null : "fx:id=\"deviceTable\" was not injected: check your FXML file 'DeviceAdministration.fxml'.";
        assert columnUserAgent != null : "fx:id=\"columnUserAgent\" was not injected: check your FXML file 'DeviceAdministration.fxml'.";
        assert columnAllowed != null : "fx:id=\"columnAllowed\" was not injected: check your FXML file 'DeviceAdministration.fxml'.";
        assert menuButtonAllow != null : "fx:id=\"menuButtonAllow\" was not injected: check your FXML file 'DeviceAdministration.fxml'.";
        assert menuButtonDenied != null : "fx:id=\"menuButtonDenied\" was not injected: check your FXML file 'DeviceAdministration.fxml'.";
        assert menuButtonDelete != null : "fx:id=\"menuButtonDelete\" was not injected: check your FXML file 'DeviceAdministration.fxml'.";

        //Spaltenbreite
        deviceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        columnUserAgent.setMaxWidth(1f * Integer.MAX_VALUE * 80);
        columnAllowed.setMaxWidth(1f * Integer.MAX_VALUE * 20);

        //Spalte Benutzername
        columnUserAgent.setCellValueFactory(new PropertyValueFactory<>("UserAgend"));
        columnAllowed.setCellValueFactory(new PropertyValueFactory<>("Allowed"));
        columnAllowed.setCellFactory(param -> new AllowedCell());

        //Optionen erst einmal deaktivieren
        menuButtonAllow.setDisable(true);
        menuButtonDenied.setDisable(true);
        menuButtonDelete.setDisable(true);

        //Ladeanzeige
        rootStackPane.getChildren().add(maskerPane);
        maskerPane.setText("Bitte warten ...");

        update();
    }

    protected void update() {

        maskerPane.setVisible(true);

        menuButtonAllow.setDisable(true);
        menuButtonDenied.setDisable(true);
        menuButtonDelete.setDisable(true);

        //Geräte laden
        Task<DeviceResponse> task = new Task<DeviceResponse>() {
            @Override
            protected DeviceResponse call() throws Exception {

                try {

                    return ShcDesktopClient.getInstance().getConnectionManager().getDevices();
                } catch (IOException e) {

                    DeviceResponse deviceResponse = new DeviceResponse();
                    deviceResponse.setSuccess(false);
                    deviceResponse.setMessage(e.getLocalizedMessage());
                    return deviceResponse;
                }
            }
        };
        task.setOnSucceeded((WorkerStateEvent event) -> {

            deviceResponse = (DeviceResponse) event.getSource().getValue();
            if(deviceResponse.isSuccess()) {

                //Daten
                if(deviceResponse != null) {

                    deviceTable.getItems().clear();
                    deviceTable.getItems().addAll(FXCollections.observableList(deviceResponse.getDeviceDataList()));
                    maskerPane.setVisible(false);
                    menuButtonAllow.setDisable(false);
                    menuButtonDenied.setDisable(false);
                    menuButtonDelete.setDisable(false);
                }
            } else {

                UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Fehler", deviceResponse.getMessage());
                if(deviceResponse.getErrorCode() == 100) {

                    ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                }
            }
        });
        Thread thread = new Thread(task);
        thread.start();
    }
}
