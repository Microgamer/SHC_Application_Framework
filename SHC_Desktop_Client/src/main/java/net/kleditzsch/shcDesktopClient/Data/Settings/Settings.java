package net.kleditzsch.shcDesktopClient.Data.Settings;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.kleditzsch.shcCore.Core.BasicElement;
import net.kleditzsch.shcCore.Settings.Setting;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

/**
 * Anwendungseinstellungen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Settings {

    /**
     * Einstellungen
     */
    public static final String SETTING_WINDOW_POS_X = "setting.window.posX";
    public static final String SETTING_WINDOW_POS_Y = "setting.window.posY";
    public static final String SETTING_WINDOW_HEIGHT = "setting.window.height";
    public static final String SETTING_WINDOW_WIDTH = "setting.window.width";
    public static final String SETTING_WINDOW_MAXIMIZED = "setting.window.maximized";

    public static final String SETTING_SERVER_ADDRESS = "setting.server.address";
    public static final String SETTING_SERVER_PORT = "setting.server.port";
    public static final String SETTING_SERVER_CLIENT_HASH = "setting.server.client.hash";
    public static final String SETTING_SERVER_USER = "setting.server.user";
    public static final String SETTING_SERVER_IDENTIFIER = "setting.server.identifier";

    /**
     * Einstellungen
     */
    private Map<String, Setting> settings = new HashMap<>();

    /**
     * Bekannte Einstellungen
     */
    private Map<String, Setting> knownSettings = new HashMap<>();

    /**
     * registriert alle Einstellungen
     */
    public Settings() {

        //Fensterposition
        Setting<Double> posX = new Setting<>(SETTING_WINDOW_POS_X, 0d, 0d);
        knownSettings.put(posX.getName(), posX);
        Setting<Double> posY = new Setting<>(SETTING_WINDOW_POS_Y, 0d, 0d);
        knownSettings.put(posY.getName(), posY);

        //Fenstergröße
        Setting<Double> width = new Setting<>(SETTING_WINDOW_WIDTH, 800d, 800d);
        knownSettings.put(width.getName(), width);
        Setting<Double> height = new Setting<>(SETTING_WINDOW_HEIGHT, 600d, 600d);
        knownSettings.put(height.getName(), height);

        //Maximiert
        Setting<Double> maximized = new Setting<>(SETTING_WINDOW_MAXIMIZED, 0d, 0d);
        knownSettings.put(maximized.getName(), maximized);

        //Verbindungsdaten
        Setting<String> address = new Setting<>(SETTING_SERVER_ADDRESS, "127.0.0.1", "127.0.0.1");
        knownSettings.put(address.getName(), address);
        Setting<Double> port = new Setting<>(SETTING_SERVER_PORT, 443d, 443d);
        knownSettings.put(port.getName(), port);
        Setting<String> clientHash = new Setting<>(SETTING_SERVER_CLIENT_HASH, "", "");
        knownSettings.put(clientHash.getName(), clientHash);
        Setting<String> user = new Setting<>(SETTING_SERVER_USER, "", "");
        knownSettings.put(user.getName(), user);
        Setting<String> identifier = new Setting<>(SETTING_SERVER_IDENTIFIER, "", "");
        knownSettings.put(identifier.getName(), identifier);
    }

    /**
     * läd die gespeicherten Einstellunegn
     *
     * @throws IOException
     */
    public void load() throws IOException {

        Path userHome = Paths.get(System.getProperty("user.home"), ".shcDesktopApp", "AppSettings.json");
        if(Files.exists(userHome)) {

            //JSON deserialisieren
            Gson gson = ShcDesktopClient.getInstance().getGson();
            JsonElement je = new JsonParser().parse(Files.newBufferedReader(userHome, StandardCharsets.UTF_8));
            JsonArray ja = je.getAsJsonArray();
            for (JsonElement settingJson : ja) {

                Setting setting = gson.fromJson(settingJson.getAsString(), Setting.class);
                settings.put(setting.getName(), setting);
            }
        }

        //mit bekannten Einstellungen falls nötig auffüllen
        for(String name : knownSettings.keySet()) {

            if(!settings.containsKey(name)) {

                Setting setting = knownSettings.get(name);
                this.settings.put(setting.getName(), setting);
            }
        }
    }

    /**
     * gibt eine Einstellung zurück
     *
     * @param name Name
     * @return Einstellung
     */
    public Setting getSetting(String name) {

        return settings.get(name);
    }

    /**
     * speichert die Einstellungen
     *
     * @throws IOException
     */
    public void dump() throws IOException {

        Path userHome = Paths.get(System.getProperty("user.home"), ".shcDesktopApp", "AppSettings.json");
        if(!Files.exists(userHome)) {

            Files.createDirectories(userHome.getParent());
            Files.createFile(userHome);
        }

        //JSON serialisieren
        Gson gson = ShcDesktopClient.getInstance().getGson();
        JsonArray ja = new JsonArray();
        for (String settingName : settings.keySet()) {

            Setting setting = settings.get(settingName);
            ja.add(gson.toJson(setting));
        }

        BufferedWriter out = Files.newBufferedWriter(userHome, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        out.write(ja.toString());
        out.close();
    }
}
