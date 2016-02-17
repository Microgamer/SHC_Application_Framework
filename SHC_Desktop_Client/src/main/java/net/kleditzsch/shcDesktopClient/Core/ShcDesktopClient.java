package net.kleditzsch.shcDesktopClient.Core;

/**
 * SHC Dasktop ClientData
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
import net.kleditzsch.shcCore.Json.LocalDateSerializer;
import net.kleditzsch.shcCore.Json.LocalDateTimeSerializer;
import net.kleditzsch.shcCore.Json.LocalTimeSerializer;
import net.kleditzsch.shcDesktopClient.HttpInterface.ConnectionManager;
import net.kleditzsch.shcDesktopClient.Data.Settings.Settings;
import net.kleditzsch.shcDesktopClient.View.MainViewController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
     * Verbindungs Manager
     */
    protected ConnectionManager connectionManager;

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

        //Verbindungs Manager initalisieren
        String serverAddress = settings.getStringSetting(Settings.SETTING_SERVER_ADDRESS).getValue();
        int serverPort = settings.getIntegerSetting(Settings.SETTING_SERVER_PORT).getValue();
        this.connectionManager = new ConnectionManager(serverAddress, serverPort);

        //Hauptfenster initalisieren
        FXMLLoader loader = new FXMLLoader(classLoader.getResource("FXML/MainView.fxml"));
        Parent root;
        try {

            //Einstellungen laden
            double width = settings.getDoubleSetting(Settings.SETTING_WINDOW_WIDTH).getValue();
            double height = settings.getDoubleSetting(Settings.SETTING_WINDOW_HEIGHT).getValue();
            double posX = settings.getDoubleSetting(Settings.SETTING_WINDOW_POS_X).getValue();
            double posY = settings.getDoubleSetting(Settings.SETTING_WINDOW_POS_Y).getValue();
            boolean maximized = settings.getBooleanSetting(Settings.SETTING_WINDOW_MAXIMIZED).getValue();
            boolean fullscreen = settings.getBooleanSetting(Settings.SETTING_WINDOW_FULLSCREEN).getValue();

            root = loader.load();
            mainViewController = loader.getController();
            Scene scene = new Scene(root, width, height);

            //Fenster verbereiten
            if(posX != 0 || posY != 0) {

                //Position
                primaryStage.setX(posX);
                primaryStage.setY(posY);
            }
            if(maximized) {

                //Maximiert
                primaryStage.setMaximized(true);
            } else if(fullscreen) {

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

            UiDialogHelper.showExceptionDialog(primaryStage, e);
            Platform.exit();
        }


    }

    /**
     * initialisiert die Gson Json API
     */
    protected void initGson() {

        builder = new GsonBuilder();

        //Serialisierer und Deserialisierer anmelden
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        builder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
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

            settings.getDoubleSetting(Settings.SETTING_WINDOW_POS_X).setValue(primaryStage.getX());
            settings.getDoubleSetting(Settings.SETTING_WINDOW_POS_Y).setValue(primaryStage.getY());
            if(primaryStage.isMaximized()) {

                settings.getBooleanSetting(Settings.SETTING_WINDOW_MAXIMIZED).setValue(true);
            } else if(primaryStage.isFullScreen()) {

                settings.getBooleanSetting(Settings.SETTING_WINDOW_FULLSCREEN).setValue(true);
            } else {

                settings.getBooleanSetting(Settings.SETTING_WINDOW_MAXIMIZED).setValue(false);
                settings.getBooleanSetting(Settings.SETTING_WINDOW_FULLSCREEN).setValue(false);
                settings.getDoubleSetting(Settings.SETTING_WINDOW_WIDTH).setValue(primaryStage.getWidth());
                settings.getDoubleSetting(Settings.SETTING_WINDOW_HEIGHT).setValue(primaryStage.getHeight());
            }

            try {

                settings.dump();
            } catch (IOException e1) {

                UiDialogHelper.showExceptionDialog(primaryStage, e1);
            }
        }
    }

    /**
     * gibt den Verbindungsmanager zurück
     *
     * @return Verbindungsmanager
     */
    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    /**
     * gibt das Hauptfenster zurück
     *
     * @return Hauptfenster
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
