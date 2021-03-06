package net.kleditzsch.shcApplicationServer.Util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.kleditzsch.Util.CliUtil;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Settings.Settings;
import net.kleditzsch.shcCore.Settings.IntegerSetting;
import net.kleditzsch.shcCore.Settings.Interface.Setting;
import net.kleditzsch.shcCore.Settings.StringSetting;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Editor für die Datenbank Konfiguration
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class CliConfigEditor {

    private static Logger logger = LoggerUtil.getLogger(CliConfigEditor.class);

    /**
     * bearbeitet die Datenbank Konfiguration
     */
    public static void startDatabaseConfig() {

        System.out.println("Datenbank Einstellungen");

        Path dbConfigFile = Paths.get("db.config.json");

        //Einstellungen laden
        JsonElement json = null;
        try {

            json = new JsonParser().parse(Files.newBufferedReader(dbConfigFile));
        } catch (IOException e) {

            json = new JsonParser().parse("{\"host\":\"127.0.0.1\",\"port\":6379,\"timeout\":1000,\"pass\":\"\",\"db\":0}");
        }

        JsonObject config = json.getAsJsonObject();

        try {

            //IP Adresse
            Optional<String> ip = CliUtil.inputIpAddressOption("Redis Server IP Adresse", config.get("host").getAsString(), 5);
            if(ip.isPresent()) {

                config.addProperty("host", ip.get());
            }

            //Port
            Optional<Integer> port = CliUtil.inputIntegerOption("Redis Server Port", config.get("port").getAsInt(), 0, 65535, 5);
            if(port.isPresent()) {

                config.addProperty("port", port.get());
            }

            //Timeout
            Optional<Integer> timeout = CliUtil.inputIntegerOption("Redis Server Timeout [in ms]", config.get("timeout").getAsInt(), 500, 10000, 5);
            if(timeout.isPresent()) {

                config.addProperty("timeout", timeout.get());
            }

            //Passwort
            Optional<String> pass = CliUtil.inputIpAddressOption("Redis Server Passwort", config.get("pass").getAsString(), 5);
            if(pass.isPresent()) {

                config.addProperty("pass", pass.get());
            }

            //Datenbank Index
            Optional<Integer> db = CliUtil.inputIntegerOption("Redis Server Datenbankindex", config.get("db").getAsInt(), 0, 100, 5);
            if(db.isPresent()) {

                config.addProperty("db", db.get());
            }
        } catch (IOException e) {

            //Debug Ausgabe
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }

        //Einstellungen speichern
        BufferedWriter out;
        try {

            out = Files.newBufferedWriter(dbConfigFile, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            out.write(config.toString());
            out.close();
            logger.info("Einstellungen erfolgreich gespeichert");
        } catch (IOException e) {

            //Debug Ausgabe
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    /**
     * bearbeitet die Anwendungseinstellungen
     */
    public static void startApplicationConfig() {

        Settings settings = ShcApplicationServer.getInstance().getSettings();

        try {

            //Server Port
            IntegerSetting serverPort = settings.getIntegerSetting(Settings.SETTING_SERVER_PORT);
            Optional<Integer> port = CliUtil.inputIntegerOption("Server Port", serverPort.getValue(), 0, 65535, 5);
            if(port.isPresent()) {

                serverPort.setValue(port.get());
            }

            StringSetting certificatePassword = settings.getStringSetting(Settings.SETTING_SERVER_CERTIFICATE_PASSWORD);
            Optional<String> password = CliUtil.inputStringOption("Passwort des SSL Zertifikates: ", certificatePassword.getValue());
            if(password.isPresent()) {

                certificatePassword.setValue(password.get());
            }

            IntegerSetting serverStateLed = settings.getIntegerSetting(Settings.SETTING_SERVER_STATELED_PIN);
            Optional<Integer> pin = CliUtil.inputIntegerOption("Pin Nummer [wiringpi] für die Status LED", serverStateLed.getValue(), 0, 35, 5);
            if(pin.isPresent()) {

                serverStateLed.setValue(pin.get());
            }

            //EInstellungen speichern
            settings.saveData();

            logger.info("Einstellungen erfolgreich gespeichert");
        } catch (IOException e) {

            //Debug Ausgabe
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }
}
