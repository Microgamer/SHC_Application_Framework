package net.kleditzsch.shcApplicationServer.Automation;

import com.google.gson.Gson;
import javafx.util.Duration;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Database.DatabaseEditor;
import net.kleditzsch.shcCore.Automation.AutomationElements;
import net.kleditzsch.shcCore.Automation.Devices.Readable.Input;
import net.kleditzsch.shcCore.Automation.Devices.Readable.UserAtHome;
import net.kleditzsch.shcCore.Automation.Devices.SensorValue.*;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.*;
import net.kleditzsch.shcCore.Automation.Interface.AutomationDevice;
import net.kleditzsch.shcCore.Automation.Interface.AutomationElement.AutomationElement;
import net.kleditzsch.shcCore.Automation.Interface.Readable.Readable;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.VirtualSensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Switchable.Switchable;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * lesbare/schaltbare ELemente und Sensorwerte verwalten
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AutomationDeviceEditor implements DatabaseEditor {

    public static final String KEY_DEVICES_ELEMENTS = "shc:device";

    /**
     * Elemente
     */
    Map<String, AutomationDevice> automationDevices = new HashMap<>();

    /**
     * lädt die Daten des Editors aus der Datenbank
     */
    @Override
    public void loadData() {

        //Datenbank Verbindung holen
        Jedis redis = ShcApplicationServer.getInstance().getRedis();

        //Gson Objekt holen
        Gson gson = ShcApplicationServer.getInstance().getGson();

        Map<String, String> elements;

        //Lesbare Elemente
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.INPUT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, Input.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.USER_AT_HOME);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, UserAtHome.class));
        }

        //Schaltbare Elemente
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.AVM_SOCKET);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, AvmSocket.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.EDIMAX_SOCKET);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, EdimaxSocket.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.FRITZ_BOX_REBOOT_RECONNECT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, FritzBoxRebootReconnect.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.FRITZ_BOX_WLAN);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, FritzBoxWirelessLan.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.OUTPUT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, Output.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.RADIO_SOCKET);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, RadioSocket.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.REBOOT_SHUTDOWN);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, RebootShutdown.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.SCRIPT_DOUBLE);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, ScriptDouble.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.SCRIPT_SINGLE);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, ScriptSingle.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.WAKE_ON_LAN);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, WakeOnLan.class));
        }

        //Sensorwerte
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.ACTUAL_POWER);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, ActualPowerValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.AIR_PRESSURE);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, AirPressureValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.ALTITUDE);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, AltitudeValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.BATTERIE_LEVEL);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, BatteryLevelValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.DISTANCE);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, DistanceValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.DURATION);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, DurationValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.ENERGY);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, EnergyValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.GAS_AMOUNT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, GasAmountValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.HUMIDITY);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, HumidityValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.LIGHT_INTENSITY);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, LightIntensityValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.MOISTURE);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, MoistureValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.STRING);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, StringValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.TEMPERATURE);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, TemperatureValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.WATER_AMOUNT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, WaterAmountValue.class));
        }

        //Virtuele Sensorwerte
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.VIRTUAL_ACTUAL_POWER);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, VirtualActualPowerValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.VIRTUAL_ENERGY);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, VirtualEnergyValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.VIRTUAL_GAS_AMOUNT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, VirtualGasAmountValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.VIRTUAL_LIGHT_INTENSITY);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, VirtualLightIntensityValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.VIRTUAL_TEMPERATURE);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, VirtualTemperatureValue.class));
        }
        elements = redis.hgetAll(KEY_DEVICES_ELEMENTS + ":" + AutomationElements.VIRTUAL_WATER_AMOUNT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            automationDevices.put(hash, gson.fromJson(elementJson, VirtualWaterAmountValue.class));
        }
    }

    /**
     * gibt ein lesbares Element zurück
     *
     * @param hash Identifizierung
     * @return lesbares Element
     */
    public Readable getReadableByHash(String hash) {

        AutomationDevice ad = automationDevices.get(hash);;
        if(ad instanceof Readable) {

            return (Readable) ad;
        }
        return null;
    }

    /**
     * gibt alle lesbaren Elemente als Objekt zurück
     *
     * @param hashs Liste mit Identifizierungen
     * @return Liste der lesbaren Elemente
     */
    public Set<Readable> getReadablesFromHashSet(Set<String> hashs) {

        Set<Readable> responseSet = new HashSet<>();
        for(String hash : hashs) {

            AutomationDevice element = automationDevices.get(hash);
            if(element != null && element instanceof Readable) {

                responseSet.add((Readable) element);
            }
        }
        return responseSet;
    }

    /**
     * gibt die Liste aller lesbaren Elemente zurück
     *
     * @return Liste aller lesbaren Elemente
     */
    public Map<String, Readable> getReadables() {

        Map<String, Readable> responseMap = new HashMap<>();
        for(String hash : automationDevices.keySet()) {

            AutomationDevice element = automationDevices.get(hash);
            if(element != null && element instanceof Readable) {

                responseMap.put(hash, (Readable) element);
            }
        }
        return responseMap;
    }

    /**
     * fügt ein lesbares Element hinzu
     *
     * @param element lesbares Element
     * @return true bei Erfolg
     */
    public boolean addReadable(Readable element) {

        if(!automationDevices.containsKey(element.getHash())) {

            automationDevices.put(element.getHash(), element);
            return true;
        }
        return false;
    }

    /**
     * löscht ein lesbares Element
     *
     * @param element lesbares Element
     * @return true bei Erfolg
     */
    public boolean removeReadable(Readable element) {

        if(automationDevices.containsKey(element.getHash())) {

            automationDevices.remove(element.getHash());
            return true;
        }
        return false;
    }

    /**
     * gibt ein schaltbares Element zurück
     *
     * @param hash Identifizierung
     * @return schaltbares Element
     */
    public Switchable getSwitchableByHash(String hash) {

        AutomationDevice ad = automationDevices.get(hash);;
        if(ad instanceof Switchable) {

            return (Switchable) ad;
        }
        return null;
    }

    /**
     * gibt alle schaltbaren Elemente als Liste zurück
     *
     * @param hashs Liste mit Identifizierungen
     * @return Liste der schaltbaren Elemente
     */
    public Set<Switchable> getSwitchablesFromHashSet(Set<String> hashs) {

        Set<Switchable> responseSet = new HashSet<>();
        for(String hash : hashs) {

            AutomationDevice element = automationDevices.get(hash);
            if(element != null && element instanceof Readable) {

                responseSet.add((Switchable) element);
            }
        }
        return responseSet;
    }

    /**
     * gibt die Liste aller schaltbaren Elemente zurück
     *
     * @return Liste aller schaltbaren Elemente
     */
    public Map<String, Switchable> getSwitchables() {

        Map<String, Switchable> responseMap = new HashMap<>();
        for(String hash : automationDevices.keySet()) {

            AutomationDevice element = automationDevices.get(hash);
            if(element != null && element instanceof Switchable) {

                responseMap.put(hash, (Switchable) element);
            }
        }
        return responseMap;
    }

    /**
     * fügt ein schaltbares Element hinzu
     *
     * @param element schaltbares Element
     * @return true bei erfolg
     */
    public boolean addSwitchable(Switchable element) {

        if(!automationDevices.containsKey(element.getHash())) {

            automationDevices.put(element.getHash(), element);
            return true;
        }
        return false;
    }

    /**
     * löscht ein schaltbares Element
     *
     * @param element schaltbares Element
     * @return true bei Erfolg
     */
    public boolean removeSwitchable(Switchable element) {

        if(automationDevices.containsKey(element.getHash())) {

            automationDevices.remove(element.getHash());
            return true;
        }
        return false;
    }

    /**
     * gint den Sensorwert zurück
     *
     * @param hash identifizierer
     * @return Sensorwert
     */
    public SensorValue getSensorValueByHash(String hash) {

        AutomationDevice ad = automationDevices.get(hash);;
        if(ad instanceof SensorValue) {

            return (SensorValue) ad;
        }
        return null;
    }

    /**
     * gibt alle Sensorwert als Liste zurück
     *
     * @param hashs Liste mit Identifizierungen
     * @return Sensorwert
     */
    public Set<SensorValue> getSensorValueFromHashSet(Set<String> hashs) {

        Set<SensorValue> responseSet = new HashSet<>();
        for(String hash : hashs) {

            AutomationDevice element = automationDevices.get(hash);
            if(element != null && element instanceof SensorValue) {

                responseSet.add((SensorValue) element);
            }
        }
        return responseSet;
    }

    /**
     * gibt die Liste aller Sensorwerte zurück
     *
     * @return Liste aller Sensorwerte
     */
    public Map<String, SensorValue> getSensorValues() {

        Map<String, SensorValue> responseMap = new HashMap<>();
        for(String hash : automationDevices.keySet()) {

            AutomationDevice element = automationDevices.get(hash);
            if(element != null && element instanceof SensorValue) {

                responseMap.put(hash, (SensorValue) element);
            }
        }
        return responseMap;
    }

    /**
     * gibt einen Sensorwert zurürck
     *
     * @param identifier Identifizierer
     * @return Sensorwert
     */
    public SensorValue getSensorValueFromIdentifier(String identifier) {

        for(String hash : automationDevices.keySet()) {

            AutomationDevice element = automationDevices.get(hash);
            if(element != null && element instanceof SensorValue && ((SensorValue) element).getIdentifier().equals(identifier)) {

                return (SensorValue) element;
            }
        }
        return null;
    }

    /**
     * gibt eine Liste mit allen Sensorwerten zu den Identifizierern zurück
     *
     * @param identifiers Identifizierer
     * @return Sensorwert
     */
    public Set<SensorValue> getSensorValueFromIdentifierSet(Set<String> identifiers) {

        Set<SensorValue> responseSet = new HashSet<>();
        for(String identifier : identifiers) {

            for(String hash : automationDevices.keySet()) {

                AutomationDevice element = automationDevices.get(hash);
                if(element != null && element instanceof SensorValue && ((SensorValue) element).getIdentifier().equals(identifier)) {

                    responseSet.add((SensorValue) element);
                    break;
                }
            }
        }
        return responseSet;
    }

    /**
     * fügt einen Sensorwert hinzu
     *
     * @param element Sensorwert
     * @return true ber Erfolg
     */
    public boolean addSensorValue(SensorValue element) {

        if(!automationDevices.containsKey(element.getHash())) {

            //prüfen ob Identifizierer doppelt vergeben
            for(String hash : automationDevices.keySet()) {

                AutomationDevice sensorValue = automationDevices.get(hash);
                if(element != null && element instanceof SensorValue && ((SensorValue) element).getIdentifier().equals(element.getIdentifier())) {

                    return false;
                }
            }

            automationDevices.put(element.getHash(), element);
            return true;
        }
        return false;
    }

    /**
     * löscht einen Sensorwert
     *
     * @param element Sensorwert
     * @return true bei erfolg
     */
    public boolean removeSensorValue(SensorValue element) {

        if(automationDevices.containsKey(element.getHash())) {

            automationDevices.remove(element.getHash());
            return true;
        }
        return false;
    }

    /**
     * gibt einen Virtuellen Sensorwert zurück
     *
     * @param hash Identifizierung
     * @return Virtueller Sensorwert
     */
    public VirtualSensorValue getVirtualSensorValueByHash(String hash) {

        AutomationDevice ad = automationDevices.get(hash);;
        if(ad instanceof VirtualSensorValue) {

            return (VirtualSensorValue) ad;
        }
        return null;
    }

    /**
     * gibt eine Liste mit Virtuellen Sensorwerten zurück
     *
     * @param hashs Liste mit Identifizierungen
     * @return Liste mit Virtuellen Sensorwerten
     */
    public Set<VirtualSensorValue> getVirtualSensorValueFromHashSet(Set<String> hashs) {

        Set<VirtualSensorValue> responseSet = new HashSet<>();
        for(String hash : hashs) {

            AutomationDevice element = automationDevices.get(hash);
            if(element != null && element instanceof VirtualSensorValue) {

                responseSet.add((VirtualSensorValue) element);
            }
        }
        return responseSet;
    }

    /**
     * gibt die Liste aller Virtuellen Sensorwerte zurück
     *
     * @return Liste aller Virtuellen Sensorwerte
     */
    public Map<String, VirtualSensorValue> getVirtualSensorValues() {

        Map<String, VirtualSensorValue> responseMap = new HashMap<>();
        for(String hash : automationDevices.keySet()) {

            AutomationDevice element = automationDevices.get(hash);
            if(element != null && element instanceof VirtualSensorValue) {

                responseMap.put(hash, (VirtualSensorValue) element);
            }
        }
        return responseMap;
    }

    /**
     * fügt einen Virtuellen Sensorwert hinzu
     *
     * @param element Virtueller Sensorwert
     * @return true bei Erfolg
     */
    public boolean addVirtualSensorValue(VirtualSensorValue element) {

        if(!automationDevices.containsKey(element.getHash())) {

            automationDevices.put(element.getHash(), element);
            return true;
        }
        return false;
    }

    /**
     * löscht einen Virtuellen Sensorwert
     *
     * @param element Virtuellen Sensorwert
     * @return true bei Erfolg
     */
    public boolean removeVirtualSensorValue(VirtualSensorValue element) {

        if(automationDevices.containsKey(element.getHash())) {

            automationDevices.remove(element.getHash());
            return true;
        }
        return false;
    }

    /**
     * gibt die Liste mit allen Automationsgeräten zurück
     *
     * @return Liste mit allen Automationsgeräten
     */
    public Map<String, AutomationDevice> getAutomationDevices() {

        return automationDevices;
    }

    /**
     * schreibt die Daten des Editors in die Datenbank
     */
    @Override
    public void saveData() {

        Pipeline pipeline = ShcApplicationServer.getInstance().getRedis().pipelined();
        Gson gson = ShcApplicationServer.getInstance().getGson();
        for(int i = 200; i <= 600; i++) {

            pipeline.del(KEY_DEVICES_ELEMENTS + ":" + i);
        }

        synchronized (this) {

            for(String hash : automationDevices.keySet()) {

                AutomationDevice element = automationDevices.get(hash);
                pipeline.hset(KEY_DEVICES_ELEMENTS + ":" + element.getType(), hash, gson.toJson(element));
            }
        }
        pipeline.sync();
    }
}
