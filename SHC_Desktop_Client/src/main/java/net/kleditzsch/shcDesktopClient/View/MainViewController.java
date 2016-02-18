package net.kleditzsch.shcDesktopClient.View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcDesktopClient.HttpInterface.ConnectionManager;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.Util.UiNotificationHelper;
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
    private BorderPane mainBorderPane; // Value injected by FXMLLoader

    /**
     * Verbindungsstatus
     */
    protected boolean state = true;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert stateBar != null : "fx:id=\"stateBar\" was not injected: check your FXML file 'MainView.fxml'.";
        assert mainBorderPane != null : "fx:id=\"mainBorderPane\" was not injected: check your FXML file 'MainView.fxml'.";

        //Statusbar initaliseren
        Button adminButton = new Button("Verwaltung");
        adminButton.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(5), new Insets(5))));
        adminButton.setTextFill(Color.WHITE);
        stateBar.getRightItems().add(adminButton);
        adminButton.setOnAction(e -> {

            MainViewLoader.loadAdminMenueView();
        });
        setState(false);

        //Prüfen ob eingeloggt
        ConnectionManager cm = ShcDesktopClient.getInstance().getConnectionManager();
        if(!cm.isConnected()) {

            //Status setzen
            setState(false);

            if(cm.autoLogin()) {

                setState(true);
            } else {

                //Anmeldefenster zeigen
                FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Login/Login.fxml"));
                Parent pane;
                try {

                    pane = loader.load();
                    mainBorderPane.setCenter(pane);
                } catch (IOException e1) {

                    UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
                }
            }
        }

        if (cm.isConnected()) {

            //Dashboard lasen
            System.out.println("Dash Laden");
        } else {

            //TODO Fehler Hilfeseite
        }
    }

    /**
     * setzt den Verbindungsstatus in der Fußleiste
     *
     * @param connected Verbunden
     */
    public void setState(boolean connected) {

        if(connected != this.state) {

            Button state = new Button(" ");
            stateBar.getLeftItems().clear();
            stateBar.getLeftItems().add(state);

            if(connected) {

                state.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), new Insets(5))));
                stateBar.setText("verbunden");
            } else {

                state.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), new Insets(5))));
                stateBar.setText("getrennt");
            }
            this.state = connected;
        }
    }

    /**
     * gibt das Progress Property der Statusleiste zurück
     *
     * @return Progress Property
     */
    public DoubleProperty progressProperty() {

        return stateBar.progressProperty();
    }

    /**
     * gibt das Haupt Pane zurüeck
     *
     * @return BorderPane
     */
    public BorderPane getMainPane() {

        return mainBorderPane;
    }
}
