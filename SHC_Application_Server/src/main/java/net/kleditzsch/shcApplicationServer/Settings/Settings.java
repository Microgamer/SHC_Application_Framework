package net.kleditzsch.shcApplicationServer.Settings;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Database.DatabaseEditor;
import net.kleditzsch.shcCore.Settings.BooleanSetting;
import net.kleditzsch.shcCore.Settings.DoubleSetting;
import net.kleditzsch.shcCore.Settings.IntegerSetting;
import net.kleditzsch.shcCore.Settings.Interface.Setting;
import net.kleditzsch.shcCore.Settings.StringSetting;
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
    public static final String SETTING_SERVER_CERTIFICATE_PASSWORD = "setting.server.certificate.password";
    public static final String SETTING_SERVER_STATELED_PIN = "setting.server.stateled.pin";

    public static final String SETTING_SUNRISE_OFFSET = "setting.sunrise.offset";
    public static final String SETTING_SUNSET_OFFSET = "setting.sunset.offset";

    public static final String SETTING_LATITUDE = "setting.location.latitude";
    public static final String SETTING_LONGITUDE = "setting.location.longitude";

    public static final String SETTING_FRITZBOX_ACTIVE = "setting.fritzbox.active";
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
        IntegerSetting serverPort = new IntegerSetting(SETTING_SERVER_PORT, 8080, 8080);
        knownSettings.put(serverPort.getName(), serverPort);
        StringSetting certificatePassword = new StringSetting(SETTING_SERVER_CERTIFICATE_PASSWORD, "password", "password");
        knownSettings.put(certificatePassword.getName(), certificatePassword);

        //Server Status LED
        IntegerSetting serverStateLed = new IntegerSetting(SETTING_SERVER_STATELED_PIN, -1, -1);
        knownSettings.put(serverStateLed.getName(), serverStateLed);

        //Offset Sonnenaufgang
        IntegerSetting sunriseOffset = new IntegerSetting(SETTING_SUNRISE_OFFSET, 0, 0);
        knownSettings.put(sunriseOffset.getName(), sunriseOffset);

        //Offset Sonnenuntergang
        IntegerSetting sunsetOffset = new IntegerSetting(SETTING_SUNSET_OFFSET, 0, 0);
        knownSettings.put(sunsetOffset.getName(), sunsetOffset);

        //Längengrad
        DoubleSetting latitude = new DoubleSetting(SETTING_LATITUDE, 50.0, 50.0);
        knownSettings.put(latitude.getName(), latitude);

        //Breitengrad
        DoubleSetting longitude = new DoubleSetting(SETTING_LONGITUDE, 12.0, 12.0);
        knownSettings.put(longitude.getName(), longitude);

        //Fritz!Box Addresse
        BooleanSetting fritzboxActive = new BooleanSetting(SETTING_FRITZBOX_ACTIVE, false, false);
        knownSettings.put(fritzboxActive.getName(), fritzboxActive);

        //Fritz!Box Addresse
        StringSetting fritzboxAddress = new StringSetting(SETTING_FRITZBOX_ADDRESS, "fritz.box", "fritz.box");
        knownSettings.put(fritzboxAddress.getName(), fritzboxAddress);

        //Fritz!Box User
        StringSetting fritzboxUser = new StringSetting(SETTING_FRITZBOX_USER, "", "");
        knownSettings.put(fritzboxUser.getName(), fritzboxUser);

        //Fritz!Box Passwort
        StringSetting fritzboxPassowrd = new StringSetting(SETTING_FRITZBOX_PASSWORD, "", "");
        knownSettings.put(fritzboxPassowrd.getName(), fritzboxPassowrd);

        //Strompreis
        DoubleSetting energyElectricPrice = new DoubleSetting(SETTING_ENERGY_ELECTRIC_PRICE, 0.0, 0.0);
        knownSettings.put(energyElectricPrice.getName(), energyElectricPrice);

        //Wasserpreis
        DoubleSetting energyWaterPrice = new DoubleSetting(SETTING_ENERGY_WATER_PRICE, 0.0, 0.0);
        knownSettings.put(energyWaterPrice.getName(), energyWaterPrice);

        //Gaspreis
        DoubleSetting energyGasPrice = new DoubleSetting(SETTING_ENERGY_GAS_PRICE, 0.0, 0.0);
        knownSettings.put(energyGasPrice.getName(), energyGasPrice);
    }

    /**
     * lädt die Einstellungen aus der Datenbank
     */
    public void loadData() {

        Jedis db = ShcApplicationServer.getInstance().getRedis();

        Gson gson = ShcApplicationServer.getInstance().getGson();

        this.settings.clear();

        //Einstellungen laden
        Map<String, String> settings;
        //String
        settings = db.hgetAll(KEY_SETTINGS + ":" + Setting.TYPE_STRING);
        for(String key : settings.keySet()) {

            String settingJson = settings.get(key);
            Setting setting = gson.fromJson(settingJson, StringSetting.class);
            this.settings.put(setting.getName(), setting);
        }
        //Integer
        settings = db.hgetAll(KEY_SETTINGS + ":" + Setting.TYPE_INTEGER);
        for(String key : settings.keySet()) {

            String settingJson = settings.get(key);
            Setting setting = gson.fromJson(settingJson, IntegerSetting.class);
            this.settings.put(setting.getName(), setting);
        }
        //Double
        settings = db.hgetAll(KEY_SETTINGS + ":" + Setting.TYPE_DOUBLE);
        for(String key : settings.keySet()) {

            String settingJson = settings.get(key);
            Setting setting = gson.fromJson(settingJson, DoubleSetting.class);
            this.settings.put(setting.getName(), setting);
        }
        //Boolean
        settings = db.hgetAll(KEY_SETTINGS + ":" + Setting.TYPE_BOOLEAN);
        for(String key : settings.keySet()) {

            String settingJson = settings.get(key);
            Setting setting = gson.fromJson(settingJson, BooleanSetting.class);
            this.settings.put(setting.getName(), setting);
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
     * @param name Name der Einstellung
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
     * schreibt die EInstellungen in die Datenbank
     */
    public void saveData() {

        synchronized (this) {

            Pipeline pipeline = ShcApplicationServer.getInstance().getRedis().pipelined();
            Gson gson = ShcApplicationServer.getInstance().getGson();
            pipeline.del(KEY_SETTINGS);

            for(String name : settings.keySet()) {

                Setting setting = settings.get(name);
                String settingJson = gson.toJson(setting);

                pipeline.hset(KEY_SETTINGS + ":" + setting.getType(), setting.getName(), settingJson);
            }

            pipeline.sync();
        }
    }
}
