package net.kleditzsch.AVM.FritzBox.SmartHome;

import net.kleditzsch.AVM.FritzBox.SmartHome.Device.SmarthomeDevice;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oliver on 27.12.15.
 */
public class FritzBoxSmarthome {

    private FritzBoxHandler fritzBoxHandler = new FritzBoxHandler();

    /**
     * @param password
     */
    public FritzBoxSmarthome(String password) {

        fritzBoxHandler.login("fritz.box", "", password);
    }

    /**
     * @param username
     * @param password
     */
    public FritzBoxSmarthome(String username, String password) {

        fritzBoxHandler.login("fritz.box", username, password);
    }

    /**
     * @param fritzBoxAddress
     * @param username
     * @param password
     */
    public FritzBoxSmarthome(String fritzBoxAddress, String username, String password) {

        fritzBoxHandler.login(fritzBoxAddress, username, password);
    }

    /**
     * gibt eine Liste mit den Geräte IDs zurück
     *
     * @return
     * @throws IOException
     */
    public String[] getDeviceList() throws IOException {

        String response = fritzBoxHandler.sendHttpRequest("/webservices/homeautoswitch.lua?switchcmd=getswitchlist");
        return response.split(",");
    }

    public Set<SmarthomeDevice> listDevices() throws IOException {

        Set<SmarthomeDevice> smartHomeDevices = new HashSet<>();
        String[] devices = getDeviceList();
        if(devices.length > 0) {

            String response = fritzBoxHandler.sendHttpRequest("/webservices/homeautoswitch.lua?switchcmd=getdevicelistinfos&ain=" + devices[0]);
            System.out.println(response);
        }
        return smartHomeDevices;
    }
}
