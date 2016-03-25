package net.kleditzsch.shcDesktopClient.View.Admin.Room;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import net.kleditzsch.shcDesktopClient.View.MainViewLoader;

/**
 * Raumverwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RoomAdminstrationController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootStackPane"
    private StackPane rootStackPane; // Value injected by FXMLLoader

    @FXML // fx:id="rootBorderPane"
    private BorderPane rootBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="buttonBack"
    private Button buttonBack; // Value injected by FXMLLoader

    @FXML // fx:id="buttonRefresh"
    private Button buttonRefresh; // Value injected by FXMLLoader

    @FXML // fx:id="roomsTable"
    private TableView<?> roomsTable; // Value injected by FXMLLoader

    @FXML // fx:id="columnName"
    private TableColumn<?, ?> columnName; // Value injected by FXMLLoader

    @FXML // fx:id="columnIcon"
    private TableColumn<?, ?> columnIcon; // Value injected by FXMLLoader

    @FXML // fx:id="columnActive"
    private TableColumn<?, ?> columnActive; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonCreate"
    private MenuItem menuButtonCreate; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonEdit"
    private MenuItem menuButtonEdit; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonDelete"
    private MenuItem menuButtonDelete; // Value injected by FXMLLoader

    @FXML
    void back(ActionEvent event) {

        MainViewLoader.loadAdminMenueView();
    }

    @FXML
    void createElement(ActionEvent event) {

    }

    @FXML
    void deleteElement(ActionEvent event) {

    }

    @FXML
    void editElement(ActionEvent event) {

    }

    @FXML
    void refreshList(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootStackPane != null : "fx:id=\"rootStackPane\" was not injected: check your FXML file 'RoomAdministration.fxml'.";
        assert rootBorderPane != null : "fx:id=\"rootBorderPane\" was not injected: check your FXML file 'RoomAdministration.fxml'.";
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'RoomAdministration.fxml'.";
        assert buttonRefresh != null : "fx:id=\"buttonRefresh\" was not injected: check your FXML file 'RoomAdministration.fxml'.";
        assert roomsTable != null : "fx:id=\"roomsTable\" was not injected: check your FXML file 'RoomAdministration.fxml'.";
        assert columnName != null : "fx:id=\"columnName\" was not injected: check your FXML file 'RoomAdministration.fxml'.";
        assert columnIcon != null : "fx:id=\"columnIcon\" was not injected: check your FXML file 'RoomAdministration.fxml'.";
        assert columnActive != null : "fx:id=\"columnActive\" was not injected: check your FXML file 'RoomAdministration.fxml'.";
        assert menuButtonCreate != null : "fx:id=\"menuButtonCreate\" was not injected: check your FXML file 'RoomAdministration.fxml'.";
        assert menuButtonEdit != null : "fx:id=\"menuButtonEdit\" was not injected: check your FXML file 'RoomAdministration.fxml'.";
        assert menuButtonDelete != null : "fx:id=\"menuButtonDelete\" was not injected: check your FXML file 'RoomAdministration.fxml'.";

    }
}
