package net.kleditzsch.shcApplicationServer.Settings;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Database.DatabaseEditor;
import net.kleditzsch.shcCore.Settings.Setting;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * Seinstellungen Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Settings implements DatabaseEditor {

    /**
     * Redis Keys für die Datenhaltung
     */
    private static final String KEY_SETTINGS = "shc:settings";

    /**
     * Einstellungen
     */
    public static final String SETTING_SERVER_PORT = "setting.server.port";
    public static final String SETTING_SERVER_STATELED_PIN = "setting.server.stateled.pin";

    public static final String SETTING_SUNRISE_OFFSET = "setting.sunrise.offset";
    public static final String SETTING_SUNSET_OFFSET = "setting.sunset.offset";

    public static final String SETTING_LATITUDE = "setting.location.latitude";
    public static final String SETTING_LONGITUDE = "setting.server.longitude";

    public static final String SETTING_FRITZBOX_ADDRESS = "setting.fritzbox.address";
    public static final String SETTING_FRITZBOX_USER = "setting.fritzbox.user";
    public static final String SETTING_FRITZBOX_PASSWORD = "setting.fritzbox.password";

    public static final String SETTING_ENERGY_ELECTRIC_PRICE = "setting.energy.electric.price";
    public static final String SETTING_ENERGY_WATER_PRICE = "setting.energy.water.price";
    public static final String SETTING_ENERGY_GAS_PRICE = "setting.energy.gas.price";

    /**
     * Einstellungen
     */
    private Map<String, Setting> settings = new HashMap<>();

    /**
     * Bekannte Einstellungen
     */
    private Map<String, Setting> knownSettings = new HashMap<>();

    /**
     * registriert dalle Einstellungen
     */
    public Settings() {

        //Server Port
        Setting<Double> serverPort = new Setting<>(SETTING_SERVER_PORT, 8080d, 8080d);
        knownSettings.put(serverPort.getName(), serverPort);

        //Server Status LED
        Setting<Double> serverStateLed = new Setting<>(SETTING_SERVER_STATELED_PIN, -1d, -1d);
        knownSettings.put(serverStateLed.getName(), serverStateLed);

        //Offset Sonnenaufgang
        Setting<Double> sunriseOffset = new Setting<>(SETTING_SUNRISE_OFFSET, 0d, 0d);
        knownSettings.put(sunriseOffset.getName(), sunriseOffset);

        //Offset Sonnenuntergang
        Setting<Double> sunsetOffset = new Setting<>(SETTING_SUNSET_OFFSET, 0d, 0d);
        knownSettings.put(sunsetOffset.getName(), sunsetOffset);

        //Längengrad
        Setting<Double> latitude = new Setting<>(SETTING_LATITUDE, 50.0, 50.0);
        knownSettings.put(latitude.getName(), latitude);

        //Breitengrad
        Setting<Double> longitude = new Setting<>(SETTING_LONGITUDE, 12.0, 12.0);
        knownSettings.put(longitude.getName(), longitude);

        //Fritz!Box Addresse
        Setting<String> fritzboxAddress = new Setting<>(SETTING_FRITZBOX_ADDRESS, "fritz.box", "fritz.box");
        knownSettings.put(fritzboxAddress.getName(), fritzboxAddress);

        //Fritz!Box User
        Setting<String> fritzboxUser = new Setting<>(SETTING_FRITZBOX_USER, "", "");
        knownSettings.put(fritzboxUser.getName(), fritzboxUser);

        //Fritz!Box Passwort
        Setting<String> fritzboxPassowrd = new Setting<>(SETTING_FRITZBOX_PASSWORD, "", "");
        knownSettings.put(fritzboxPassowrd.getName(), fritzboxPassowrd);

        //Strompreis
        Setting<Double> energyElectricPrice = new Setting<>(SETTING_ENERGY_ELECTRIC_PRICE, 0.0, 0.0);
        knownSettings.put(energyElectricPrice.getName(), energyElectricPrice);

        //Wasserpreis
        Setting<Double> energyWaterPrice = new Setting<>(SETTING_ENERGY_WATER_PRICE, 0.0, 0.0);
        knownSettings.put(energyWaterPrice.getName(), energyWaterPrice);

        //Gaspreis
        Setting<Double> energyGasPrice = new Setting<>(SETTING_ENERGY_GAS_PRICE, 0.0, 0.0);
        knownSettings.put(energyGasPrice.getName(), energyGasPrice);
    }

    /**
     * lädt die Einstellungen aus der Datenbank
     */
    public void loadData() {

        Jedis db = ShcApplicationServer.getInstance().getRedis();
        Map<String, String> settings = db.hgetAll(KEY_SETTINGS);

        Gson gson = ShcApplicationServer.getInstance().getGson();

        //Einstellungen laden
        for(String key : settings.keySet()) {

            String settingJson = settings.get(key);
            Setting setting = gson.fromJson(settingJson, Setting.class);
            this.settings.put(setting.getName(), setting);
        }

        //mit bekannten EInstellungen falls nötig auffüllen
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
     * @param name Name der Einstellung
     * @return Einstellung
     */
    public Setting getSetting(String name) {

        return settings.get(name);
    }

    /**
     * schreibt die EInstellungen in die Datenbank
     */
    public void saveData() {

        Pipeline pipeline = ShcApplicationServer.getInstance().getRedis().pipelined();
        Gson gson = ShcApplicationServer.getInstance().getGson();
        pipeline.del(KEY_SETTINGS);

        for(String name : settings.keySet()) {

            Setting setting = settings.get(name);
            String settingJson = gson.toJson(setting);

            pipeline.hset(KEY_SETTINGS, setting.getName(), settingJson);
        }

        pipeline.sync();
    }
}
