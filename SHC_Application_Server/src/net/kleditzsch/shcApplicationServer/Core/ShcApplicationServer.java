package net.kleditzsch.shcApplicationServer.Core;

import net.kleditzsch.shcApplicationServer.Database.Redis;
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

        //Datenbank Verbindung
        initDatabase();
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
