package net.kleditzsch.shcDesktopClient.View.Main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import org.controlsfx.control.StatusBar;

/**
 * Controller des Hauptfensters
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class MainViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="stateBar"
    private StatusBar stateBar; // Value injected by FXMLLoader

    @FXML // fx:id="mainBorderPane"
    private MenuBar mainBorderPane; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert stateBar != null : "fx:id=\"stateBar\" was not injected: check your FXML file 'MainView.fxml'.";
        assert mainBorderPane != null : "fx:id=\"mainBorderPane\" was not injected: check your FXML file 'MainView.fxml'.";

    }
}
