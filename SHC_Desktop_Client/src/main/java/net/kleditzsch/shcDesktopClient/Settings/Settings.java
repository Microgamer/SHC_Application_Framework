package net.kleditzsch.shcDesktopClient.Settings;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.kleditzsch.shcCore.Settings.*;
import net.kleditzsch.shcCore.Settings.Interface.Setting;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
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
    public static final String SETTING_WINDOW_FULLSCREEN = "setting.window.fullscreen";

    public static final String SETTING_SERVER_ADDRESS = "setting.server.address";
    public static final String SETTING_SERVER_PORT = "setting.server.port";
    public static final String SETTING_SERVER_CLIENT_HASH = "setting.server.client.hash";
    public static final String SETTING_SERVER_USER = "setting.server.user";
    public static final String SETTING_SERVER_IDENTIFIER = "setting.server.identifier";

    public static final String SETTING_CLIENT_PROTOCOLS = "setting.client.protocols";

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
        DoubleSetting posX = new DoubleSetting(SETTING_WINDOW_POS_X, 0, 0);
        knownSettings.put(posX.getName(), posX);
        DoubleSetting posY = new DoubleSetting(SETTING_WINDOW_POS_Y, 0, 0);
        knownSettings.put(posY.getName(), posY);

        //Fenstergröße
        DoubleSetting width = new DoubleSetting(SETTING_WINDOW_WIDTH, 800, 800);
        knownSettings.put(width.getName(), width);
        DoubleSetting height = new DoubleSetting(SETTING_WINDOW_HEIGHT, 600, 600);
        knownSettings.put(height.getName(), height);

        //Maximiert/Vollbild
        BooleanSetting maximized = new BooleanSetting(SETTING_WINDOW_MAXIMIZED, false, false);
        knownSettings.put(maximized.getName(), maximized);
        BooleanSetting fullscreen = new BooleanSetting(SETTING_WINDOW_FULLSCREEN, false, false);
        knownSettings.put(fullscreen.getName(), fullscreen);

        //Verbindungsdaten
        StringSetting address = new StringSetting(SETTING_SERVER_ADDRESS, "127.0.0.1", "127.0.0.1");
        knownSettings.put(address.getName(), address);
        IntegerSetting port = new IntegerSetting(SETTING_SERVER_PORT, 8080, 8080);
        knownSettings.put(port.getName(), port);
        StringSetting clientHash = new StringSetting(SETTING_SERVER_CLIENT_HASH, "", "");
        knownSettings.put(clientHash.getName(), clientHash);
        StringSetting user = new StringSetting(SETTING_SERVER_USER, "", "");
        knownSettings.put(user.getName(), user);
        StringSetting identifier = new StringSetting(SETTING_SERVER_IDENTIFIER, "", "");
        knownSettings.put(identifier.getName(), identifier);

        //Client Einstellungen
        ListSetting protocols = new ListSetting();
        protocols.setName(SETTING_CLIENT_PROTOCOLS);
        protocols.getDefaultValue().addAll(Arrays.asList(
                "rcswitch-pi_elro",
                "beamish_switch",
                "byebyestandbye",
                "brennenstuhl",
                "clarus_switch",
                "cleverwatts",
                "coco_switch",
                "cogex",
                "dio_switch",
                "elro_300",
                "elro_300_switch",
                "elro_400",
                "elro_400_switch",
                "elro_800_switch",
                "home_easy_old",
                "impuls",
                "intertechno_old",
                "intertechno_switch",
                "kaku_switch",
                "nexa_switch",
                "kaku_switch_old",
                "pollin",
                "quigg_switch",
                "raw",
                "rev1_switch",
                "rev2_switch",
                "rev3_switch",
                "selectremote",
                "silvercrest",
                "unitech",
                "silvercrest",
                "eHome",
                "rsl366",
                "promax",
                "rc101",
                "rc102",
                "duwi",
                "logilink-switch",
                "techlico_switch"
        ));
        protocols.getValue().addAll(protocols.getDefaultValue());
        knownSettings.put(protocols.getName(), protocols);
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
            JsonArray stringSettings = ja.get(0).getAsJsonArray();
            for (JsonElement settingJson : stringSettings) {

                Setting setting = gson.fromJson(settingJson.getAsString(), StringSetting.class);
                settings.put(setting.getName(), setting);
            }
            JsonArray integerSettings = ja.get(1).getAsJsonArray();
            for (JsonElement settingJson : integerSettings) {

                Setting setting = gson.fromJson(settingJson.getAsString(), IntegerSetting.class);
                settings.put(setting.getName(), setting);
            }
            JsonArray doubleSettings = ja.get(2).getAsJsonArray();
            for (JsonElement settingJson : doubleSettings) {

                Setting setting = gson.fromJson(settingJson.getAsString(), DoubleSetting.class);
                settings.put(setting.getName(), setting);
            }
            JsonArray booleanSettings = ja.get(3).getAsJsonArray();
            for (JsonElement settingJson : booleanSettings) {

                Setting setting = gson.fromJson(settingJson.getAsString(), BooleanSetting.class);
                settings.put(setting.getName(), setting);
            }
            JsonArray listSettings = ja.get(4).getAsJsonArray();
            for (JsonElement settingJson : listSettings) {

                Setting setting = gson.fromJson(settingJson.getAsString(), ListSetting.class);
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
     * gibt eine Einstellung zurück
     *
     * @param name Name der Einstellung
     * @return Einstellung
     */
    public StringSetting getStringSetting(String name) {

        Setting setting =  settings.get(name);
        if(setting instanceof StringSetting) {

            return (StringSetting) setting;
        }
        return null;
    }

    /**
     * gibt eine Einstellung zurück
     *
     * @param name Name der Einstellung
     * @return Einstellung
     */
    public IntegerSetting getIntegerSetting(String name) {

        Setting setting =  settings.get(name);
        if(setting instanceof IntegerSetting) {

            return (IntegerSetting) setting;
        }
        return null;
    }

    /**
     * gibt eine Einstellung zurück
     *
     * @param name Name der Einstellung
     * @return Einstellung
     */
    public DoubleSetting getDoubleSetting(String name) {

        Setting setting =  settings.get(name);
        if(setting instanceof DoubleSetting) {

            return (DoubleSetting) setting;
        }
        return null;
    }

    /**
     * gibt eine Einstellung zurück
     *
     * @param name Name der Einstellung
     * @return Einstellung
     */
    public BooleanSetting getBooleanSetting(String name) {

        Setting setting =  settings.get(name);
        if(setting instanceof BooleanSetting) {

            return (BooleanSetting) setting;
        }
        return null;
    }

    /**
     * gibt eine Einstellung zurück
     *
     * @param name Name der Einstellung
     * @return Einstellung
     */
    public ListSetting getListSetting(String name) {

        Setting setting =  settings.get(name);
        if(setting instanceof ListSetting) {

            return (ListSetting) setting;
        }
        return null;
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
        JsonArray stringSettings = new JsonArray();
        JsonArray integerSettings = new JsonArray();
        JsonArray doubleSettings = new JsonArray();
        JsonArray booleanSettings = new JsonArray();
        JsonArray listSettings = new JsonArray();
        for (String settingName : settings.keySet()) {

            Setting setting = settings.get(settingName);
            if(setting instanceof StringSetting) {

                stringSettings.add(gson.toJson(setting));
            } else if(setting instanceof IntegerSetting) {

                integerSettings.add(gson.toJson(setting));
            } else if(setting instanceof DoubleSetting) {

                doubleSettings.add(gson.toJson(setting));
            } else if(setting instanceof BooleanSetting) {

                booleanSettings.add(gson.toJson(setting));
            } else if(setting instanceof ListSetting) {

                listSettings.add(gson.toJson(setting));
            }
        }

        JsonArray ja = new JsonArray();
        ja.add(stringSettings);
        ja.add(integerSettings);
        ja.add(doubleSettings);
        ja.add(booleanSettings);
        ja.add(listSettings);

        BufferedWriter out = Files.newBufferedWriter(userHome, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        out.write(ja.toString());
        out.close();
    }
}
