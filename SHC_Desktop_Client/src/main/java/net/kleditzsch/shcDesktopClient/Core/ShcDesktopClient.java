package net.kleditzsch.shcDesktopClient.Core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcCore.ClientData.AutomationDevice.AutomationDeviceResponse;
import net.kleditzsch.shcCore.ClientData.Login.LoginResponse;
import net.kleditzsch.shcCore.Json.*;
import net.kleditzsch.shcCore.Util.LoggerUtil;
import net.kleditzsch.shcDesktopClient.HttpInterface.ConnectionManager;
import net.kleditzsch.shcDesktopClient.Service.ConnectionService;
import net.kleditzsch.shcDesktopClient.Settings.Settings;
import net.kleditzsch.shcDesktopClient.View.MainViewController;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SHC Dasktop ClientData
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
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
     * Verbindungsüberwachung
     */
    protected ConnectionService connectionService = new ConnectionService();

    /**
     * Instance der Main Klasse
     */
    protected static ShcDesktopClient instance;

    /**
     * Logger
     */
    private static Logger logger;

    @Override
    public void start(Stage primaryStage) {

        //Debug Modus / Logger
        List<String> arguments = getParameters().getRaw();
        if(arguments.contains("-d") || arguments.contains("--Debug")) {

            //Standard Log Level
            LoggerUtil.setLogLevel(Level.CONFIG);
            LoggerUtil.setLogFileLevel(Level.CONFIG);
        } else if(arguments.contains("-df") || arguments.contains("--Debug-Fine")) {

            //Alle Log Daten ausgeben
            LoggerUtil.setLogLevel(Level.FINEST);
            LoggerUtil.setLogFileLevel(Level.FINEST);
        } else {

            //Fehler in Log Datei Schreiben
            LoggerUtil.setLogLevel(Level.OFF);
            LoggerUtil.setLogFileLevel(Level.SEVERE);
            LoggerUtil.setLogFile(Paths.get(System.getProperty("user.home"), ".shcDesktopApp", "error.log"));
        }
        logger = LoggerUtil.getLogger(ShcDesktopClient.class);

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
            primaryStage.getIcons().add(new Image(ShcDesktopClient.class.getResourceAsStream("/Icons/App/appIcon.png")));
            primaryStage.setTitle("SHC Desktop Client");
            primaryStage.setScene(scene);

            //Fenster Anzeigen
            primaryStage.show();
            primaryStage.toFront();
            logger.info("Hauptfenster geladen");

            //Listener für das Schliesen
            primaryStage.setOnCloseRequest(e -> {

                //TODO Confirm Dialog einbinden
                //if(UiDialogHelper.showConfirmDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Beenden", null, "Willst du die App wirklich beenden?")) {

                    saveAndExit();
                    Platform.exit();
                //}
                //e.consume();
            });

        } catch (IOException e) {

            UiDialogHelper.showExceptionDialog(e);
            Platform.exit();
        }

        //Verbindungsüberwachungs Service starten
        connectionService.setRestartOnFailure(true);
        connectionService.setMaximumFailureCount(5);
        connectionService.start();
        logger.info("Connection Service gestartet");
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

        logger.info("Desktop App initalisiert");
    }

    /**
     * initialisiert die Gson Json API
     */
    protected void initGson() {

        builder = new GsonBuilder();

        builder.registerTypeAdapter(AutomationDeviceResponse.class, new AutomationDeviceResponseSerializer());
        builder.registerTypeAdapter(LoginResponse.class, new LoginResponseSerializer());

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
     * beendet die Anwendung
     */
    public void saveAndExit() {

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
                settings.getDoubleSetting(Settings.SETTING_WINDOW_WIDTH).setValue(primaryStage.getScene().getWidth());
                settings.getDoubleSetting(Settings.SETTING_WINDOW_HEIGHT).setValue(primaryStage.getScene().getHeight());
            }

            try {

                settings.dump();
                logger.info("Anwendungseinstellungen gespeichert");
            } catch (IOException e1) {

                logger.log(Level.SEVERE, "Anwendungseinstellungen speichern fehlgeschlagen", e1);
            }
        }
    }

    /**
     * gibt die Benutzerkennung zurück
     *
     * @return Benutzerkennung
     */
    public String getUserAgent() {

        return "SHC Desktop Client von " + System.getProperty("user.name") + " unter " + System.getProperty("os.name") + " " + System.getProperty("os.version");
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
