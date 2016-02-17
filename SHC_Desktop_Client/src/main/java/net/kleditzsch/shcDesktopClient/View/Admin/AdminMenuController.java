package net.kleditzsch.shcDesktopClient.View.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
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

    @FXML
    void openDeviceAdministration(ActionEvent event) {

        //TODO Rechte prüfen
        MainViewLoader.loadDeviceAdministartionView();
    }

    @FXML
    void openSettingAdministration(ActionEvent event) {

        //TODO Rechte prüfen
        MainViewLoader.loadSettingsAdministartionView();
    }

    @FXML
    void openUserAdministration(ActionEvent event) {

        //TODO Rechte prüfen
        MainViewLoader.loadUserAdministartionView();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootBorderPane != null : "fx:id=\"rootBorderPane\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonUser != null : "fx:id=\"buttonUser\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonDevices != null : "fx:id=\"buttonDevices\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonSettings != null : "fx:id=\"buttonSettings\" was not injected: check your FXML file 'Menu.fxml'.";

        //TODO Rechte prüfen und Buttons für nicht Berechtigte Funktionen ausblenden
    }
}

