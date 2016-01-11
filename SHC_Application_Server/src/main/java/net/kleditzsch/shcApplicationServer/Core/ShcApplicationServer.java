package net.kleditzsch.shcApplicationServer.Core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.kleditzsch.shcApplicationServer.Database.Redis;
import net.kleditzsch.shcApplicationServer.Json.Deserializer.User.UserDeserializer;
import net.kleditzsch.shcApplicationServer.Json.Serializer.User.UserSerializer;
import net.kleditzsch.shcApplicationServer.Room.RoomEditor;
import net.kleditzsch.shcApplicationServer.Session.SessionEditor;
import net.kleditzsch.shcApplicationServer.Settings.Settings;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcApplicationServer.Util.CliConfigEditor;
import net.kleditzsch.shcCore.User.User;
import redis.clients.jedis.Jedis;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Basisklasse
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ShcApplicationServer {

    /**
     * Server Version
     */
    public static final String VERSION = "0.1.0";

    /**
     * Singleton Instanz
     */
    private static ShcApplicationServer instance;

    /**
     * Debug Modus Aktiv?
     */
    private static boolean debug = false;

    /**
     * Datenbankverbindung
     */
    private Jedis redis;

    /**
     * Gson Builder
     */
    private GsonBuilder builder;

    /**
     * EInstellungen
     */
    private Settings settings;

    /**
     * Benutzer verwaltung
     */
    private UserEditor userEditor;

    /**
     * Sessionverwaltung
     */
    private SessionEditor sessionEditor;

    /**
     * Raum Verwaltung
     */
    private RoomEditor roomEditor;

    /**
     * Eintrittspunkt in die Anwendung
     *
     * @param args
     */
    public static void main(String[] args) {

        //Kommandozeilen Parameter in ein SET übernehmen
        Set<String> arguments = new HashSet<>();
        Collections.addAll(arguments, args);

        //Version anzeigen
        if(arguments.contains("-v") || arguments.contains("--version")) {

            System.out.println("Version: " + VERSION);
            return;
        }

        //Debug Modus
        if(arguments.contains("-d") || arguments.contains("--Debug")) {

            debug = true;
            return;
        }

        //Datenbank Konfiguration
        Path dbConfigFile = Paths.get("db.config.json");
        if(arguments.contains("-db") || arguments.contains("--Database") || !Files.exists(dbConfigFile)) {

            CliConfigEditor.startDatabaseConfig();
            return;
        }

        //Anwendung initalisieren
        instance = new ShcApplicationServer();
        instance.initAppliaction();

        //Datenbank Konfiguration
        if(arguments.contains("-c") || arguments.contains("--config")) {

            CliConfigEditor.startApplicationConfig();
            return;
        }
    }

    /**
     * initalisiert die Anwendung
     */
    private void initAppliaction() {

        //Gson initalisieren
        initGson();

        //Datenbank Verbindung
        initDatabase();

        //Daten laden
        initData();
    }

    /**
     * initialisiert die Gson Json API
     */
    private void initGson() {

        builder = new GsonBuilder();

        //Serialisierer und Deserialisierer anmelden
        builder.registerTypeAdapter(User.class, new UserSerializer());
        builder.registerTypeAdapter(User.class, new UserDeserializer());
    }

    /**
     * erzeugt ein Gson Objekt aus dem Builder heraus
     *
     * @return
     */
    public Gson getGson() {

        return builder.create();
    }

    /**
     * baut die Datenbankverbindung auf
     */
    private void initDatabase() {

        Redis redis = new Redis();
        this.redis = redis.connect();
    }

    /**
     * gibt die Datenbankverbindung zurück
     *
     * @return
     */
    public Jedis getRedis() {
        return redis;
    }

    /**
     * läd alle SHC Daten aus der Datenbank
     */
    private void initData() {

        //Einstellungen laden
        settings = new Settings();
        settings.loadData();

        //Benutzer laden
        userEditor = new UserEditor();
        userEditor.loadData();

        //Sessions laden
        sessionEditor = new SessionEditor();
        sessionEditor.loadData();

        //Räume laden
        roomEditor = new RoomEditor();
        roomEditor.loadData();
    }

    /**
     * gibt die Einstellungs Verwaltung zurück
     *
     * @return
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * gibt die Benutzer Verwaltung zurück
     *
     * @return
     */
    public UserEditor getUserEditor() {

        return userEditor;
    }

    /**
     * gibt den Raum Editor zurück
     *
     * @return
     */
    public RoomEditor getRoomEditor() {

        return roomEditor;
    }

    /**
     * gibt die aktuelle Instanz der Anwendung zurück
     *
     * @return
     */
    public static ShcApplicationServer getInstance() {
        return instance;
    }

    /**
     * gibt an ob der Debug Modus aktiv ist
     *
     * @return true wenn aktiv
     */
    public static boolean isDebug() {
        return debug;
    }
}
