package net.kleditzsch.shcDesktopClient.View.Admin.Elements;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import net.kleditzsch.shcDesktopClient.View.MainViewLoader;
import org.controlsfx.control.CheckComboBox;

/**
 * Elemente Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ElementAdministrationController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootBorderPane"
    private BorderPane rootBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="rootToolbar"
    private ToolBar rootToolbar; // Value injected by FXMLLoader

    @FXML // fx:id="buttonBack"
    private Button buttonBack; // Value injected by FXMLLoader

    @FXML // fx:id="buttonRefresh"
    private Button buttonRefresh; // Value injected by FXMLLoader

    @FXML // fx:id="toolbarSpace"
    private Pane toolbarSpace; // Value injected by FXMLLoader

    @FXML // fx:id="filterLabel"
    private Label filterLabel; // Value injected by FXMLLoader

    @FXML // fx:id="inputTypeFilter"
    private CheckComboBox<?> inputTypeFilter; // Value injected by FXMLLoader

    @FXML // fx:id="inputNameFilter"
    private TextField inputNameFilter; // Value injected by FXMLLoader

    @FXML // fx:id="elementsTable"
    private TableView<?> elementsTable; // Value injected by FXMLLoader

    @FXML // fx:id="columnName"
    private TableColumn<?, ?> columnName; // Value injected by FXMLLoader

    @FXML // fx:id="columnType"
    private TableColumn<?, ?> columnType; // Value injected by FXMLLoader

    @FXML
    void back(ActionEvent event) {

        MainViewLoader.loadAdminMenueView();
    }

    @FXML
    void filterName(KeyEvent event) {

    }

    @FXML
    void refreshList(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootBorderPane != null : "fx:id=\"rootBorderPane\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert rootToolbar != null : "fx:id=\"rootToolbar\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert buttonRefresh != null : "fx:id=\"buttonRefresh\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert toolbarSpace != null : "fx:id=\"toolbarSpace\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert filterLabel != null : "fx:id=\"filterLabel\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert inputTypeFilter != null : "fx:id=\"inputTypeFilter\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert inputNameFilter != null : "fx:id=\"inputNameFilter\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert elementsTable != null : "fx:id=\"elementsTable\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert columnName != null : "fx:id=\"columnName\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert columnType != null : "fx:id=\"columnType\" was not injected: check your FXML file 'ElementAdministration.fxml'.";

        //Abstand zwischen den Toolbar feldern
        toolbarSpace.setPrefWidth(rootToolbar.getPrefWidth() - buttonBack.getWidth() - buttonRefresh.getWidth() - filterLabel.getWidth() - inputNameFilter.getWidth() -inputTypeFilter.getWidth() - 40);
    }

    protected void update() {


    }
}
