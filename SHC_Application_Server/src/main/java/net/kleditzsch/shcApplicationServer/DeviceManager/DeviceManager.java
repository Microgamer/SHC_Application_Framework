package net.kleditzsch.shcApplicationServer.DeviceManager;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Database.DatabaseEditor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DeviceManager implements DatabaseEditor {

    /**
     * Redis Keys für die Datenhaltung
     */
    private static final String KEY_DEVICES = "shc:devices";

    /**
     * bekannte Geräte
     */
    protected final Map<String, ClientDevice> devices = new HashMap<>();

    /**
     * lädt die Daten des Editors aus der Datenbank
     */
    @Override
    public void loadData() {

        Jedis db = ShcApplicationServer.getInstance().getRedis();
        Map<String, String> devices = db.hgetAll(KEY_DEVICES);

        Gson gson = ShcApplicationServer.getInstance().getGson();

        this.devices.clear();

        //Sitzungen laden
        for(String key : devices.keySet()) {

            String deviceJson = devices.get(key);
            ClientDevice device = gson.fromJson(deviceJson, ClientDevice.class);
            this.devices.put(device.getClientHash(), device);
        }
    }

    /**
     * gibt die Liste der bekannten Geräte zurück
     *
     * @return bekannten Geräte
     */
    public Map<String, ClientDevice> getDevices() {

        return this.devices;
    }

    /**
     * schreibt die Daten des Editors in die Datenbank
     */
    @Override
    public void saveData() {

        Pipeline pipeline = ShcApplicationServer.getInstance().getRedis().pipelined();
        Gson gson = ShcApplicationServer.getInstance().getGson();
        pipeline.del(KEY_DEVICES);

        for(String sid : devices.keySet()) {

            ClientDevice device = devices.get(sid);
            String deviceJson = gson.toJson(device);

            pipeline.hset(KEY_DEVICES, device.getClientHash(), deviceJson);
        }

        pipeline.sync();
    }
}
