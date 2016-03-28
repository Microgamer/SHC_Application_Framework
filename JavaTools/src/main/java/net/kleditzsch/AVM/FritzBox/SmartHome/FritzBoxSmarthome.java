package net.kleditzsch.AVM.FritzBox.SmartHome;

import net.kleditzsch.AVM.FritzBox.SmartHome.Elements.EnergyMeter;
import net.kleditzsch.AVM.FritzBox.SmartHome.Elements.Switch;
import net.kleditzsch.AVM.FritzBox.SmartHome.Elements.TemperatureSensor;
import net.kleditzsch.AVM.FritzBox.SmartHome.Exception.AuthException;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Fritz!Box SmartHome Geräte
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class FritzBoxSmarthome {

    private FritzBoxHandler fritzBoxHandler = new FritzBoxHandler();

    /**
     * @param password Fritz!Box Passwort
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws AuthException
     */
    public FritzBoxSmarthome(String password) throws IOException, NoSuchAlgorithmException, AuthException {

        fritzBoxHandler.login("fritz.box", "", password);
    }

    /**
     * @param username Fritz!Box Benutzername
     * @param password Fritz!Box Passwort
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws AuthException
     */
    public FritzBoxSmarthome(String username, String password) throws IOException, NoSuchAlgorithmException, AuthException {

        fritzBoxHandler.login("fritz.box", username, password);
    }

    /**
     * @param fritzBoxAddress Fritz!Box Adresse
     * @param username Fritz!Box Benutzername
     * @param password Fritz!Box Passwort
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws AuthException
     */
    public FritzBoxSmarthome(String fritzBoxAddress, String username, String password) throws IOException, NoSuchAlgorithmException, AuthException {

        fritzBoxHandler.login(fritzBoxAddress, username, password);
    }

    /**
     * gibt eine Liste mit den Geräte IDs zurück
     *
     * @return Liste der SmartHome Geräte
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public List<String> getDeviceList() throws IOException, NoSuchAlgorithmException {

        String response = fritzBoxHandler.sendHttpRequest("/webservices/homeautoswitch.lua?switchcmd=getswitchlist");
        String[] devices = response.split(",");
        List<String> deviceList = new ArrayList<>();
        for(int i = 0; i < devices.length; i++) {

            String device = devices[i];
            device = device.replaceAll("\\s", "");
            deviceList.add(device);
        }
        return deviceList;
    }

    /**
     * gibt eine Liste der bekannte SmartHome Geräte zurück
     *
     * @return Liste der SmartHome Geräte
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws JDOMException
     */
    public List<SmarthomeDevice> listDevices() throws IOException, NoSuchAlgorithmException, JDOMException {

        List<SmarthomeDevice> smartHomeDevices = new ArrayList<>();
        String response = fritzBoxHandler.sendHttpRequest("/webservices/homeautoswitch.lua?switchcmd=getdevicelistinfos");

        Document doc = new SAXBuilder().build(new StringReader(response));
        Element deviceList = doc.getRootElement();

        for(Element device : deviceList.getChildren()) {

            SmarthomeDevice smarthomeDevice = new SmarthomeDevice();

            //Allgemeine Daten
            smarthomeDevice.setIdentifier(device.getAttributeValue("identifier").replaceAll("\\s", ""));
            smarthomeDevice.setId(device.getAttributeValue("id"));
            smarthomeDevice.setFunctionBitmask(Integer.parseInt(device.getAttributeValue("functionbitmask")));
            smarthomeDevice.setFirmwareVersion(device.getAttributeValue("fwversion"));
            smarthomeDevice.setManufacturer(device.getAttributeValue("manufacturer"));
            smarthomeDevice.setProductName(device.getAttributeValue("productname"));
            smarthomeDevice.setPresent((device.getChildText("present").trim().equals("1")));
            smarthomeDevice.setName(device.getChildText("name"));

            if(smarthomeDevice.isCometDectRadiatorThermostat()) {

                //TODO comming soon
            }
            if(smarthomeDevice.isEnergyMeter()) {

                //Energiemesse
                EnergyMeter energyMeter = new EnergyMeter();
                Element powermeter = device.getChild("powermeter");
                energyMeter.setPower(Long.parseLong(powermeter.getChildText("power")));
                energyMeter.setEnergy(Long.parseLong(powermeter.getChildText("energy")));
                smarthomeDevice.setEnergyMeter(energyMeter);
            }
            if(smarthomeDevice.isTemperatureSensor()) {

                //Temperatur Sensor
                TemperatureSensor temperatureSensor = new TemperatureSensor();
                Element temperature = device.getChild("temperature");
                temperatureSensor.setThemperature(Integer.parseInt(temperature.getChildText("celsius")) / 10);
                temperatureSensor.setOffset(Integer.parseInt(temperature.getChildText("offset")) / 10);
                smarthomeDevice.setTemperatureSensor(temperatureSensor);
            }
            if(smarthomeDevice.isSwitchableSocket()) {

                //schaltbare Steckdose
                Switch aSwitch = new Switch(fritzBoxHandler, smarthomeDevice.getIdentifier());
                Element switchElement = device.getChild("switch");
                aSwitch.setState(Integer.parseInt(switchElement.getChildText("state")));
                aSwitch.setMode(switchElement.getChildText("mode"));
                aSwitch.setLocked(Integer.parseInt(switchElement.getChildText("lock")) == 1);
                smarthomeDevice.setSwitch(aSwitch);
            }
            if(smarthomeDevice.isDectRepeater()) {

                //DECT Repeater
            }
            smartHomeDevices.add(smarthomeDevice);
        }

        return smartHomeDevices;
    }
}
