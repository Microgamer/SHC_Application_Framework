package net.kleditzsch.shcCore.ClientData.AutomationDevice;

import net.kleditzsch.shcCore.Automation.AutomationElements;
import net.kleditzsch.shcCore.Automation.Devices.Readable.Input;
import net.kleditzsch.shcCore.Automation.Devices.Readable.UserAtHome;
import net.kleditzsch.shcCore.Automation.Devices.SensorValue.*;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.*;
import net.kleditzsch.shcCore.Automation.Interface.AutomationDevice;
import net.kleditzsch.shcCore.ClientData.AbstractResponse;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;

import java.util.HashMap;
import java.util.Map;

/**
 * Liste der Automations Elemente
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AutomationDeviceResponse extends AbstractResponse {

    /**
     * Liste mit allen Automatisierungsgeräten
     */
    protected Map<String, AutomationDevice> automationDevices = new HashMap();

    /**
     * Liste der Schaltserver
     */
    protected Map<String, SwitchServer> switchServers = new HashMap<>();

    /**
     * gibt die Liste mit allen Automatisierungsgeräten zurück
     *
     * @return Liste mit allen Automatisierungsgeräten
     */
    public Map<String, AutomationDevice> getAutomationDevices() {
        return automationDevices;
    }

    /**
     * gibt die Liste mit allen Schaltservern zurück
     *
     * @return
     */
    public Map<String, SwitchServer> getSwitchServers() {
        return switchServers;
    }

    /**
     * gibt die Klasse zum Typ zurück
     *
     * @param type Typ
     * @return Klasse
     */
    public static Class getClassForType(int type) {

        switch (type) {

            case AutomationElements.INPUT:

                return Input.class;
            case AutomationElements.USER_AT_HOME:

                return UserAtHome.class;
            case AutomationElements.AVM_SOCKET:

                return AvmSocket.class;
            case AutomationElements.EDIMAX_SOCKET:

                return EdimaxSocket.class;
            case AutomationElements.FRITZ_BOX_WLAN:

                return FritzBoxWirelessLan.class;
            case AutomationElements.FRITZ_BOX_REBOOT_RECONNECT:

                return FritzBoxRebootReconnect.class;
            case AutomationElements.OUTPUT:

                return Output.class;
            case AutomationElements.RADIO_SOCKET:

                return RadioSocket.class;
            case AutomationElements.REBOOT_SHUTDOWN:

                return RebootShutdown.class;
            case AutomationElements.SCRIPT_SINGLE:

                return ScriptSingle.class;
            case AutomationElements.SCRIPT_DOUBLE:

                return ScriptDouble.class;
            case AutomationElements.WAKE_ON_LAN:

                return WakeOnLan.class;
            case AutomationElements.VIRTUAL_SOCKET:

                return VirtualSocket.class;
            case AutomationElements.ACTUAL_POWER:

                return ActualPowerValue.class;
            case AutomationElements.AIR_PRESSURE:

                return AirPressureValue.class;
            case AutomationElements.ALTITUDE:

                return AltitudeValue.class;
            case AutomationElements.BATTERIE_LEVEL:

                return BatteryLevelValue.class;
            case AutomationElements.DISTANCE:

                return DistanceValue.class;
            case AutomationElements.DURATION:

                return DurationValue.class;
            case AutomationElements.ENERGY:

                return EnergyValue.class;
            case AutomationElements.GAS_AMOUNT:

                return GasAmountValue.class;
            case AutomationElements.HUMIDITY:

                return HumidityValue.class;
            case AutomationElements.LIGHT_INTENSITY:

                return LightIntensityValue.class;
            case AutomationElements.MOISTURE:

                return MoistureValue.class;
            case AutomationElements.STRING:

                return StringValue.class;
            case AutomationElements.TEMPERATURE:

                return TemperatureValue.class;
            case AutomationElements.WATER_AMOUNT:

                return WaterAmountValue.class;
            case AutomationElements.VIRTUAL_ACTUAL_POWER:

                return VirtualActualPowerValue.class;
            case AutomationElements.VIRTUAL_ENERGY:

                return VirtualEnergyValue.class;
            case AutomationElements.VIRTUAL_GAS_AMOUNT:

                return VirtualGasAmountValue.class;
            case AutomationElements.VIRTUAL_LIGHT_INTENSITY:

                return VirtualLightIntensityValue.class;
            case AutomationElements.VIRTUAL_WATER_AMOUNT:

                return VirtualWaterAmountValue.class;
            case AutomationElements.VIRTUAL_TEMPERATURE:

                return VirtualTemperatureValue.class;
        }
        return null;
    }

    /**
     * gibt die Klasse zum Typ zurück
     *
     * @param type Typ
     * @return Klasse
     */
    public static String getTypeName(int type) {

        switch (type) {

            case AutomationElements.INPUT:

                return "Eingang";
            case AutomationElements.USER_AT_HOME:

                return "Benutzer zu Hause";
            case AutomationElements.AVM_SOCKET:

                return "AVM Steckdose";
            case AutomationElements.EDIMAX_SOCKET:

                return "Edimax Steckdose";
            case AutomationElements.FRITZ_BOX_WLAN:

                return "Fritz!Box WLan";
            case AutomationElements.FRITZ_BOX_REBOOT_RECONNECT:

                return "Fritz!Box Reboot/Reconnect";
            case AutomationElements.OUTPUT:

                return "Ausgang";
            case AutomationElements.RADIO_SOCKET:

                return "Funk Steckdose";
            case AutomationElements.REBOOT_SHUTDOWN:

                return "Reboot/Shutdown";
            case AutomationElements.SCRIPT_SINGLE:

                return "einfaches Script";
            case AutomationElements.SCRIPT_DOUBLE:

                return "erweitertes Script";
            case AutomationElements.WAKE_ON_LAN:

                return "Wake on Lan";
            case AutomationElements.VIRTUAL_SOCKET:

                return "Virtuelle Steckdose";
            case AutomationElements.ACTUAL_POWER:

                return "aktueller Energieverbrauch";
            case AutomationElements.AIR_PRESSURE:

                return "Luftdruck";
            case AutomationElements.ALTITUDE:

                return "Standorthöhe";
            case AutomationElements.BATTERIE_LEVEL:

                return "Batterie";
            case AutomationElements.DISTANCE:

                return "Entfernung";
            case AutomationElements.DURATION:

                return "Betriebszeit";
            case AutomationElements.ENERGY:

                return "Energieverbrauch";
            case AutomationElements.GAS_AMOUNT:

                return "Gas Menge";
            case AutomationElements.HUMIDITY:

                return "Luftfeuchte";
            case AutomationElements.LIGHT_INTENSITY:

                return "Lichtstärke";
            case AutomationElements.MOISTURE:

                return "Feuchtigkeit";
            case AutomationElements.STRING:

                return "Zeichenkette";
            case AutomationElements.TEMPERATURE:

                return "Temeratur";
            case AutomationElements.WATER_AMOUNT:

                return "Wasser Menge";
            case AutomationElements.VIRTUAL_ACTUAL_POWER:

                return "Virtueller aktueller Energieverbrauch";
            case AutomationElements.VIRTUAL_ENERGY:

                return "Virtueller Energieverbrauch";
            case AutomationElements.VIRTUAL_GAS_AMOUNT:

                return "Virtuelle Gas Menge";
            case AutomationElements.VIRTUAL_LIGHT_INTENSITY:

                return "Virtuelle Wasser Menge";
            case AutomationElements.VIRTUAL_WATER_AMOUNT:

                return "Virtuelle Lichtstärke";
            case AutomationElements.VIRTUAL_TEMPERATURE:

                return "Virtuelle Temeratur";
        }
        return "unbekannt";
    }
}
