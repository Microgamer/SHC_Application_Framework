package net.kleditzsch.shcApplicationServer.Database;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Verwaltet die Datenbankverbindung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Redis {

    /**
     * Datenbank Client
     */
    private Jedis jedis;

    /**
     * baut die Verbindung zur Datenbank auf
     *
     * @return
     */
    public Jedis connect() {

        //Redis Verbindung aufbauen
        String host;
        int port;
        int timeout;
        String pass;
        int db;

        //DB Config Lesen
        Path dbConfigFile = Paths.get("db.config.json");
        System.out.println(dbConfigFile.toAbsolutePath());
        try(BufferedReader in = Files.newBufferedReader(dbConfigFile)) {

            JsonObject jsonObject = new JsonParser().parse(in).getAsJsonObject();
            host = jsonObject.get("host").getAsString();
            port = jsonObject.get("port").getAsInt();
            timeout = jsonObject.get("timeout").getAsInt();
            pass = jsonObject.get("pass").getAsString();
            db = jsonObject.get("db").getAsInt();

            //DB Verbinden
            try {

                jedis = new Jedis(host, port, timeout * 1000);
                if(pass.length() > 0) {

                    jedis.auth(pass);
                }
                jedis.select(db);
            } catch (JedisConnectionException ex) {

                ex.printStackTrace();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        return jedis;
    }

    /**
     * gibt die aktuelle Datenbankverbindung zurück
     *
     * @return
     */
    public Jedis getConnection() {

        return jedis;
    }
}
