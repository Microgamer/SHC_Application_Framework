package net.kleditzsch.shcDesktopClient.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;

import java.io.IOException;

/**
 * Lädt die Panes der Main view
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class MainViewLoader {

    /**
     * lädt die Login View
     */
    public static void loadLoginView() {

        MainViewLoader.load("FXML/Login/Login.fxml");
    }

    /**
     * lädt das Adminstrations Menü
     */
    public static void loadAdminMenueView() {

        MainViewLoader.load("FXML/Admin/Menu.fxml");
    }

    /**
     * lädt die Benutzer Adminstrations
     */
    public static void loadUserAdministartionView() {

        MainViewLoader.load("FXML/Admin/User/UserAdminstration.fxml");
    }

    /**
     * lädt die Benutzergruppen Adminstrations
     */
    public static void loadUserGroupAdministartionView() {

        MainViewLoader.load("FXML/Admin/User/UserGroupAdministarion.fxml");
    }

    /**
     * lädt die Geräte Adminstrations
     */
    public static void loadDeviceAdministartionView() {

        MainViewLoader.load("FXML/Admin/Device/DeviceAdministration.fxml");
    }

    /**
     * lädt die Einstellungs Adminstration
     */
    public static void loadSettingsAdministartionView() {

        MainViewLoader.load("FXML/Admin/Settings/SettingsAdministration.fxml");
    }

    /**
     * lädt die Element Adminstration
     */
    public static void loadElementsAdministartionView() {

        MainViewLoader.load("FXML/Admin/Element/ElementAdministration.fxml");
    }

    /**
     * lädt eine FXML Datei als View
     *
     * @param loaction Speicherort
     */
    protected static void load(String loaction) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(loaction));
        Parent pane;
        try {

            pane = loader.load();
            ShcDesktopClient.getInstance().getMainViewController().getMainPane().setCenter(pane);
        } catch (IOException e1) {

            e1.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
    }
}