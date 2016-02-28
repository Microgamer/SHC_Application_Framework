package net.kleditzsch.shcApplicationServer.SwitchServer;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Database.DatabaseEditor;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;
import net.kleditzsch.shcCore.SwitchServer.MicroControllerSwitchServer;
import net.kleditzsch.shcCore.SwitchServer.RaspberryPiSwitchServer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * Schaltserver Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SwitchServerEditor implements DatabaseEditor {

    /**
     * Redis Keys für die Datenhaltung
     */
    private static final String KEY_SWICTH_SERVERS = "shc:switchServers";

    /**
     * Liste aller Schaltserver
     */
    protected Map<String, SwitchServer> switchServers = new HashMap<>();

    /**
     * lädt die Daten des Editors aus der Datenbank
     */
    @Override
    public void loadData() {

        Jedis db = ShcApplicationServer.getInstance().getRedis();
        Map<String, String> rasberryPiSwitchServers = db.hgetAll(KEY_SWICTH_SERVERS + ":" + SwitchServer.SWITCH_SERVER_RASPBERRY_PI);
        Map<String, String> microControllerSwitchServers = db.hgetAll(KEY_SWICTH_SERVERS + ":" + SwitchServer.SWITCH_SERVER_MICRO_CONTROLLER);

        Gson gson = ShcApplicationServer.getInstance().getGson();

        this.switchServers.clear();

        //Raspberry Pi
        for(String key : rasberryPiSwitchServers.keySet()) {

            String switchServerJson = rasberryPiSwitchServers.get(key);
            RaspberryPiSwitchServer swicthServer = gson.fromJson(switchServerJson, RaspberryPiSwitchServer.class);
            this.switchServers.put(swicthServer.getHash(), swicthServer);
        }

        //Mico Controller
        for(String key : microControllerSwitchServers.keySet()) {

            String switchServerJson = microControllerSwitchServers.get(key);
            MicroControllerSwitchServer swicthServer = gson.fromJson(switchServerJson, MicroControllerSwitchServer.class);
            this.switchServers.put(swicthServer.getHash(), swicthServer);
        }
    }

    /**
     * gibt einen Schaltserver zurück
     *
     * @param hash Identifizierung
     * @return Schaltserver
     */
    public SwitchServer getSwitchServer(String hash) {

        return this.switchServers.get(hash);
    }

    /**
     * gibt die Liste aller Schaltserver zurück
     *
     * @return Liste der Schaltserver
     */
    public Map<String, SwitchServer> getSwitchServers() {

        return this.switchServers;
    }
    /**
     * schreibt die Daten des Editors in die Datenbank
     */
    @Override
    public void saveData() {

        Pipeline pipeline = ShcApplicationServer.getInstance().getRedis().pipelined();
        Gson gson = ShcApplicationServer.getInstance().getGson();
        pipeline.del(KEY_SWICTH_SERVERS + ":" + SwitchServer.SWITCH_SERVER_RASPBERRY_PI, KEY_SWICTH_SERVERS + ":" + SwitchServer.SWITCH_SERVER_MICRO_CONTROLLER);

        synchronized (this) {

            for(String hash : this.switchServers.keySet()) {

                SwitchServer switchServer = this.switchServers.get(hash);
                String switchServerJson = gson.toJson(switchServer);
                switch(switchServer.getType()) {

                    case SwitchServer.SWITCH_SERVER_RASPBERRY_PI:

                        pipeline.hset(KEY_SWICTH_SERVERS + ":" + SwitchServer.SWITCH_SERVER_RASPBERRY_PI, switchServer.getHash(), switchServerJson);
                        break;
                    case SwitchServer.SWITCH_SERVER_MICRO_CONTROLLER:

                        pipeline.hset(KEY_SWICTH_SERVERS + ":" + SwitchServer.SWITCH_SERVER_MICRO_CONTROLLER, switchServer.getHash(), switchServerJson);
                        break;
                }
            }
        }
        pipeline.sync();
    }
}
