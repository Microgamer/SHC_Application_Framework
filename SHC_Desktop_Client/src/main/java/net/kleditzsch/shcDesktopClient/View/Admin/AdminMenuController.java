package net.kleditzsch.shcDesktopClient.View.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.HttpInterface.ConnectionManager;
import net.kleditzsch.shcDesktopClient.Util.UiNotificationHelper;
import net.kleditzsch.shcDesktopClient.View.MainViewLoader;

/**
 * Admin Menü
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AdminMenuController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootBorderPane"
    private BorderPane rootBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="buttonUser"
    private Button buttonUser; // Value injected by FXMLLoader

    @FXML // fx:id="buttonDevices"
    private Button buttonDevices; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSettings"
    private Button buttonSettings; // Value injected by FXMLLoader

    @FXML // fx:id="buttonInfo"
    private Button buttonInfo; // Value injected by FXMLLoader

    @FXML // fx:id="buttonRooms"
    private Button buttonRooms; // Value injected by FXMLLoader

    @FXML // fx:id="buttonElements"
    private Button buttonElements; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSensorpoints"
    private Button buttonSensorpoints; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSwitchfunctions"
    private Button buttonSwitchfunctions; // Value injected by FXMLLoader

    @FXML // fx:id="buttonServerState"
    private Button buttonServerState; // Value injected by FXMLLoader

    @FXML // fx:id="buttonMaximize"
    private Button buttonMaximize; // Value injected by FXMLLoader

    @FXML // fx:id="buttonFullscreen"
    private Button buttonFullscreen; // Value injected by FXMLLoader

    @FXML // fx:id="buttonExit"
    private Button buttonExit; // Value injected by FXMLLoader

    @FXML
    void openDeviceAdministration(ActionEvent event) {

        if(ShcDesktopClient.getInstance().getConnectionManager().checkPermission(Permissions.DEVICE_ADMINISTRATION)) {

            MainViewLoader.loadDeviceAdministartionView();
        } else {

            UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Fehlende Berechtigung", "Du bist nicht Berechtigt diese Aktion aus zu führen");
        }
    }

    @FXML
    void openSettingAdministration(ActionEvent event) {

        MainViewLoader.loadSettingsAdministartionView();
    }

    @FXML
    void openUserAdministration(ActionEvent event) {

        if(ShcDesktopClient.getInstance().getConnectionManager().checkPermission(Permissions.USER_ADMINISTRATION)) {

            MainViewLoader.loadUserAdministartionView();
        } else {

            UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Fehlende Berechtigung", "Du bist nicht Berechtigt diese Aktion aus zu führen");
        }
    }

    @FXML
    void openSensorPointsAdminstration(ActionEvent event) {

    }

    @FXML
    void openInfoView(ActionEvent event) {

    }

    @FXML
    void openRoomAdministration(ActionEvent event) {

    }

    @FXML
    void openServerStateView(ActionEvent event) {

    }

    @FXML
    void openSwitchFunctionAdminstration(ActionEvent event) {

    }

    @FXML
    void openElementAdministration(ActionEvent event) {

        if(ShcDesktopClient.getInstance().getConnectionManager().checkPermission(Permissions.ELEMENT_ADMINISTRATION)) {

            MainViewLoader.loadElementsAdministartionView();
        } else {

            UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Fehlende Berechtigung", "Du bist nicht Berechtigt diese Aktion aus zu führen");
        }
    }

    @FXML
    void fullscreenApp(ActionEvent event) {

        Stage primaryStage = ShcDesktopClient.getInstance().getPrimaryStage();
        if(!primaryStage.isFullScreen()) {

            primaryStage.setFullScreen(true);
        } else {

            primaryStage.setFullScreen(false);
        }
    }

    @FXML
    void maximizeApp(ActionEvent event) {

        Stage primaryStage = ShcDesktopClient.getInstance().getPrimaryStage();
        if(!primaryStage.isMaximized()) {

            primaryStage.setMaximized(true);
        } else {

            primaryStage.setMaximized(false);
        }
    }

    @FXML
    void exitApp(ActionEvent event) {

        if(UiDialogHelper.showConfirmDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Beenden", null, "Willst du die App wirklich beenden?")) {

            ShcDesktopClient.getInstance().saveAndExit();
            Platform.exit();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootBorderPane != null : "fx:id=\"rootBorderPane\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonUser != null : "fx:id=\"buttonUser\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonDevices != null : "fx:id=\"buttonDevices\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonSettings != null : "fx:id=\"buttonSettings\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonInfo != null : "fx:id=\"buttonInfo\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonRooms != null : "fx:id=\"buttonRooms\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonElements != null : "fx:id=\"buttonElements\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonSensorpoints != null : "fx:id=\"buttonSensorpoints\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonSwitchfunctions != null : "fx:id=\"buttonSwitchfunctions\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonServerState != null : "fx:id=\"buttonServerState\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonMaximize != null : "fx:id=\"buttonMaximize\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonFullscreen != null : "fx:id=\"buttonFullscreen\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonExit != null : "fx:id=\"buttonExit\" was not injected: check your FXML file 'Menu.fxml'.";


        ConnectionManager cm = ShcDesktopClient.getInstance().getConnectionManager();
        if(!cm.checkPermission(Permissions.USER_ADMINISTRATION)) {

            buttonUser.setDisable(true);
        }
        if(!cm.checkPermission(Permissions.DEVICE_ADMINISTRATION)) {

            buttonDevices.setDisable(true);
        }
        if(true) { //!cm.checkPermission(Permissions.DEVICE_ADMINISTRATION)) {

            buttonInfo.setDisable(true);
        }
        if(true) { //!cm.checkPermission(Permissions.DEVICE_ADMINISTRATION)) {

            buttonRooms.setDisable(true);
        }
        if(!cm.checkPermission(Permissions.ELEMENT_ADMINISTRATION)) {

            buttonElements.setDisable(true);
        }
        if(true) { //!cm.checkPermission(Permissions.DEVICE_ADMINISTRATION)) {

            buttonSensorpoints.setDisable(true);
        }
        if(true) { //!cm.checkPermission(Permissions.DEVICE_ADMINISTRATION)) {

            buttonSwitchfunctions.setDisable(true);
        }
        if(true) { //!cm.checkPermission(Permissions.DEVICE_ADMINISTRATION)) {

            buttonServerState.setDisable(true);
        }
    }
}

