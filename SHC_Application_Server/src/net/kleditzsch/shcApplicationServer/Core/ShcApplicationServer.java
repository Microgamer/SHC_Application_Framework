package net.kleditzsch.shcApplicationServer.Core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.kleditzsch.shcApplicationServer.Database.Redis;
import net.kleditzsch.shcApplicationServer.Json.Deserializer.UserDeserializer;
import net.kleditzsch.shcApplicationServer.Json.Serializer.UserSerializer;
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
     * Eintrittspunkt in die Anwendung
     *
     * @param args
     */
    public static void main(String[] args) {

        instance = new ShcApplicationServer();

    }

    /**
     * initalisiert die Anwendung
     */
    private ShcApplicationServer() {

        //Gson initalisieren
        initGson();

        //Datenbank Verbindung
        initDatabase();
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
     * gibt die aktuelle Instanz der Anwendung zurück
     *
     * @return
     */
    public static ShcApplicationServer getInstance() {
        return instance;
    }
}
