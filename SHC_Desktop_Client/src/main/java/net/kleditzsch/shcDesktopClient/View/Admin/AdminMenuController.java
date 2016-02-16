package net.kleditzsch.shcDesktopClient.View.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
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

    @FXML // fx:id="buttonUser"
    private FlowPane buttonUser; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSettings"
    private FlowPane buttonSettings; // Value injected by FXMLLoader

    @FXML
    void openDeviceAdministration(MouseEvent event) {

        this.openDeviceAdminstration();
    }

    @FXML
    void openDeviceAdministrationTouch(TouchEvent event) {

        this.openDeviceAdminstration();
    }

    @FXML
    void openSettingsAdministration(MouseEvent event) {

        this.openSettingsAdminstration();
    }

    @FXML
    void openSettingsAdministrationTouch(TouchEvent event) {

        this.openSettingsAdminstration();
    }

    @FXML
    void openUserAdministration(MouseEvent event) {

        this.openUserAdminstration();
    }

    @FXML
    void openUserAdministrationTouch(TouchEvent event) {

        this.openUserAdminstration();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert buttonUser != null : "fx:id=\"buttonUser\" was not injected: check your FXML file 'Menu.fxml'.";
        assert buttonSettings != null : "fx:id=\"buttonSettings\" was not injected: check your FXML file 'Menu.fxml'.";

    }

    /**
     * lädt die Benutzerverwaltung
     */
    protected void openUserAdminstration() {

        MainViewLoader.loadUserAdministartionView();
    }

    protected void openDeviceAdminstration() {

        MainViewLoader.loadDeviceAdministartionView();
    }

    protected void openSettingsAdminstration() {


    }
}

