package net.kleditzsch.shcDesktopClient.View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcCore.Util.LoggerUtil;
import net.kleditzsch.shcDesktopClient.HttpInterface.ConnectionManager;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;

/**
 * Controller des Hauptfensters
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class MainViewController {

    private static Logger logger = LoggerUtil.getLogger(MainViewController.class);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="mainBorderPane"
    private BorderPane mainBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="circleState"
    private Circle circleState; // Value injected by FXMLLoader

    @FXML // fx:id="labelState"
    private Label labelState; // Value injected by FXMLLoader

    @FXML // fx:id="sliderSize"
    private Slider sliderSize; // Value injected by FXMLLoader

    @FXML // fx:id="buttonAdmisitration"
    private Button buttonAdmisitration; // Value injected by FXMLLoader

    /**
     * Verbindungsstatus
     */
    protected boolean state = true;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert mainBorderPane != null : "fx:id=\"mainBorderPane\" was not injected: check your FXML file 'MainView.fxml'.";
        assert circleState != null : "fx:id=\"circleState\" was not injected: check your FXML file 'MainView.fxml'.";
        assert labelState != null : "fx:id=\"labelState\" was not injected: check your FXML file 'MainView.fxml'.";
        assert sliderSize != null : "fx:id=\"sliderSize\" was not injected: check your FXML file 'MainView.fxml'.";
        assert buttonAdmisitration != null : "fx:id=\"buttonAdmisitration\" was not injected: check your FXML file 'MainView.fxml'.";

        //Statusbar initaliseren
        setState(false);
        setSizeSliderVisible(false);
        buttonAdmisitration.setOnAction(e -> MainViewLoader.loadAdminMenueView());

        //Prüfen ob eingeloggt
        ConnectionManager cm = ShcDesktopClient.getInstance().getConnectionManager();
        if(!cm.isConnected()) {

            //Status setzen
            setState(false);

            if(cm.autoLogin()) {

                setState(true);
                logger.info("autologin erfolgreich");
            } else {

                //Anmeldefenster zeigen
                FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Login/Login.fxml"));
                Parent pane;
                try {

                    pane = loader.load();
                    mainBorderPane.setCenter(pane);
                    logger.info("FXML Datei \"FXML/Login/Login.fxml\" geladen");
                } catch (IOException e1) {

                    logger.log(Level.SEVERE, "Laden der FXML Datei \"FXML/Login/Login.fxml\" fehlgeschlagen", e1);
                }
            }
        }

        if (cm.isConnected()) {

            //Dashboard lasen
            //TODO Raumansicht laden

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

            if(connected) {

                circleState.setFill(Color.GREEN);
                labelState.setText("verbunden");
            } else {

                circleState.setFill(Color.RED);
                labelState.setText("getrennt");
            }
            this.state = connected;
        }
    }

    /**
     * setzt die Sichtbarkeit des Sliders zum einstellen der Elemente Größe
     *
     * @param visible Sichtbarkeit
     */
    public void setSizeSliderVisible(boolean visible) {

        sliderSize.setVisible(visible);
    }

    /**
     * gibt das Property des Sliders zurück
     *
     * @return Value Property
     */
    public DoubleProperty getSizeSliderProperty() {

        return sliderSize.valueProperty();
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
