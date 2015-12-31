package net.kleditzsch.shcApplicationServer.Core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.kleditzsch.shcApplicationServer.Database.Redis;
import net.kleditzsch.shcApplicationServer.Json.Deserializer.UserDeserializer;
import net.kleditzsch.shcApplicationServer.Json.Serializer.UserSerializer;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcCore.User.User;
import redis.clients.jedis.Jedis;

/**
 * Basisklasse
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ShcApplicationServer {

    /**
     * Singleton Instanz
     */
    private static ShcApplicationServer instance;

    /**
     * Datenbankverbindung
     */
    private Jedis redis;

    /**
     * Gson Builder
     */
    private GsonBuilder builder;

    /**
     * Benutzer verwaltung
     */
    private UserEditor userEditor;

    /**
     * Eintrittspunkt in die Anwendung
     *
     * @param args
     */
    public static void main(String[] args) {

        instance = new ShcApplicationServer();
        instance.initAppliaction();
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

        //Benutzer laden
        userEditor = new UserEditor();
        userEditor.loadData();
    }

    /**
     * gibt die Benutzerverwaltung zurück
     *
     * @return
     */
    public UserEditor getUserEditor() {

        return userEditor;
    }

    /**
     * gibt die aktuelle Instanz der Anwendung zurück
     *
     * @return
     */
    public static ShcApplicationServer getInstance() {
        return instance;
    }
}
