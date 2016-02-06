package net.kleditzsch.shcDesktopClient.Core;

/**
 * SHC Dasktop Client
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcDesktopClient.Data.Settings.Settings;
import net.kleditzsch.shcDesktopClient.View.Main.MainViewController;

import java.io.IOException;

public class ShcDesktopClient extends Application {

    /**
     * Gson Builder
     */
    protected GsonBuilder builder;

    /**
     * Einstellungen
     */
    protected Settings settings;

    /**
     * Hauptfenster
     */
    protected Stage primaryStage;

    /**
     * Klassen lader
     */
    protected ClassLoader classLoader;

    /**
     * Controller der Hauptfensters
     */
    protected MainViewController mainViewController;

    /**
     * Instance der Main Klasse
     */
    protected static ShcDesktopClient instance;

    @Override
    public void start(Stage primaryStage) {

        //Initalisieren
        ShcDesktopClient.instance = this;
        this.primaryStage = primaryStage;
        classLoader = getClass().getClassLoader();

        //Anwendung initalisieren
        this.initApp();

        //Hauptfenster initalisieren
        FXMLLoader loader = new FXMLLoader(classLoader.getResource("FXML/MainView.fxml"));
        Parent root;
        try {

            //Einstellungen laden
            double width = (double) settings.getSetting(Settings.SETTING_WINDOW_WIDTH).getValue();
            double height = (double) settings.getSetting(Settings.SETTING_WINDOW_HEIGHT).getValue();
            double posX = (double) settings.getSetting(Settings.SETTING_WINDOW_POS_X).getValue();
            double posY = (double) settings.getSetting(Settings.SETTING_WINDOW_POS_Y).getValue();
            double maximized = (double) settings.getSetting(Settings.SETTING_WINDOW_MAXIMIZED).getValue();

            root = loader.load();
            mainViewController = loader.getController();
            Scene scene = new Scene(root, width, height);

            //Fenster verbereiten
            if(posX != 0 || posY != 0) {

                //Position
                primaryStage.setX(posX);
                primaryStage.setY(posY);
            }
            if(maximized == 1) {

                //Maximiert
                primaryStage.setMaximized(true);
            } else if(maximized == 2) {

                //Maximiert
                primaryStage.setFullScreen(true);
            }
            primaryStage.setTitle("SHC Desktop Client");
            primaryStage.setScene(scene);

            //Fenster Anzeigen
            primaryStage.show();
            primaryStage.toFront();

            //Listener für das Schliesen
            primaryStage.setOnCloseRequest(e -> {

                saveStageState();
                Platform.exit();
            });

        } catch (IOException e) {

            UiDialogHelper.showExceptionDialog(e);
            Platform.exit();
        }
    }

    /**
     * Anwendung initalisieren
     */
    protected void initApp() {

        //Gson Initalisieren
        this.initGson();

        //Einstellungen
        settings = new Settings();
        try {

            settings.load();
        } catch (IOException e) {

            UiDialogHelper.showExceptionDialog(e);
            Platform.exit();
        }


    }

    /**
     * initialisiert die Gson Json API
     */
    protected void initGson() {

        builder = new GsonBuilder();

        //Serialisierer und Deserialisierer anmelden

    }

    /**
     * erzeugt ein Gson Objekt aus dem Builder heraus
     *
     * @return Gson Objekt
     */
    public Gson getGson() {

        return builder.create();
    }

    /**
     * gibt die Einstellungen zurück
     *
     * @return Einstellungen
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Speichert den Zustand des Fensters
     */
    public void saveStageState() {

        if(primaryStage != null) {

            settings.getSetting(Settings.SETTING_WINDOW_POS_X).setValue(primaryStage.getX());
            settings.getSetting(Settings.SETTING_WINDOW_POS_Y).setValue(primaryStage.getY());
            if(primaryStage.isMaximized()) {

                settings.getSetting(Settings.SETTING_WINDOW_MAXIMIZED).setValue(1d);
            } else if(primaryStage.isFullScreen()) {

                settings.getSetting(Settings.SETTING_WINDOW_MAXIMIZED).setValue(2d);
            } else {

                settings.getSetting(Settings.SETTING_WINDOW_MAXIMIZED).setValue(0d);
                settings.getSetting(Settings.SETTING_WINDOW_WIDTH).setValue(primaryStage.getWidth());
                settings.getSetting(Settings.SETTING_WINDOW_HEIGHT).setValue(primaryStage.getHeight());
            }

            try {

                settings.dump();
            } catch (IOException e1) {

                UiDialogHelper.showExceptionDialog(e1);
            }
        }
    }

    /**
     * gibt das Hauptfenster zurück
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * gibt den Klassenlader zurück
     *
     * @return Klassenslader
     */
    public ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * Controller der Hauptfensters
     *
     * @return FXML Controller
     */
    public MainViewController getMainViewController() {
        return mainViewController;
    }

    /**
     * gibt die Instanz der Hauptklasse zurück
     *
     * @return Hauptklasse
     */
    public static ShcDesktopClient getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
