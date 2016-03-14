package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Automation.AutomationDeviceEditor;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.SwitchServer.SwitchServerEditor;
import net.kleditzsch.shcCore.Automation.Devices.Readable.Input;
import net.kleditzsch.shcCore.Automation.Devices.Readable.UserAtHome;
import net.kleditzsch.shcCore.Automation.Devices.SensorValue.DistanceValue;
import net.kleditzsch.shcCore.Automation.Devices.SensorValue.TemperatureValue;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.*;
import net.kleditzsch.shcCore.Automation.Interface.AutomationDevice;
import net.kleditzsch.shcCore.Automation.Interface.Readable.Readable;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.VirtualSensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Switchable.Switchable;
import net.kleditzsch.shcCore.ClientData.AutomationDevice.AutomationDeviceResponse;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;

import java.util.Map;

/**
 * Automationsgeräte Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AutomationDeviceHandler extends AbstractRequestHandler {

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson   Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        if(params.containsKey("action")) {

            AutomationDeviceResponse automationDeviceResponse;
            SuccessResponse successResponse;
            User sessionUser = checkSession(params);
            switch (params.get("action")) {

                case "listdevices":

                    automationDeviceResponse = new AutomationDeviceResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.ELEMENT_ADMINISTRATION)) {

                            //Automationsgeräte auflisten
                            AutomationDeviceEditor automationDeviceEditor = ShcApplicationServer.getInstance().getAutomationDeviceEditor();
                            synchronized (automationDeviceEditor) {

                                automationDeviceResponse.getAutomationDevices().putAll(automationDeviceEditor.getAutomationDevices());

                                //Schaltserver Auflisten
                                SwitchServerEditor switchServerEditor = ShcApplicationServer.getInstance().getSwitchServerEditor();
                                automationDeviceResponse.getSwitchServers().putAll(switchServerEditor.getSwitchServers());
                            }

                            //Antwort
                            automationDeviceResponse.setSuccess(true);
                            return gson.toJson(automationDeviceResponse);
                        }
                        //nicht Berechtigt
                        automationDeviceResponse.setSuccess(false);
                        automationDeviceResponse.setErrorCode(101);
                        automationDeviceResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(automationDeviceResponse);
                    }
                    //Ungültige Session
                    automationDeviceResponse.setSuccess(false);
                    automationDeviceResponse.setErrorCode(100);
                    automationDeviceResponse.setMessage("Ungültige Session");
                    return gson.toJson(automationDeviceResponse);
                case "adddevice":

                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.DEVICE_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("data") && params.containsKey("type")) {

                                Class typeClass = AutomationDeviceResponse.getClassForType(Integer.parseInt(params.get("type")));
                                if(typeClass != null) {

                                    AutomationDevice automationDevice = (AutomationDevice) gson.fromJson(params.get("data"), typeClass);
                                    if(automationDevice != null) {

                                        AutomationDeviceEditor automationDeviceEditor = ShcApplicationServer.getInstance().getAutomationDeviceEditor();
                                        synchronized (automationDeviceEditor) {

                                            if(automationDevice.getHash().length() > 0) {

                                                //prüfen ob Hash schon vorhanden
                                                if(!automationDeviceEditor.getAutomationDevices().containsKey(automationDevice.getHash())) {

                                                    //neues Gerät speichern
                                                    automationDeviceEditor.getAutomationDevices().put(automationDevice.getHash(), automationDevice);

                                                    successResponse.setSuccess(true);
                                                } else {

                                                    //Hash schon vorhanden
                                                    successResponse.setSuccess(false);
                                                    successResponse.setMessage("Der Hash ist schon vorhanden");
                                                }
                                            } else {

                                                //Fehler ungültiger Hash
                                                successResponse.setSuccess(false);
                                                successResponse.setMessage("Ungültiger Hash");
                                            }
                                        }
                                    } else {

                                        //Fehler ungültige Daten
                                        successResponse.setSuccess(false);
                                        successResponse.setMessage("Ungültige Daten");
                                    }
                                } else {

                                    //Fehler ungältiger Typ
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("Ungültiger Typ");
                                }
                            } else {

                                //fehlender Parameter hash
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"data\" oder \"type\"");
                            }
                            return gson.toJson(successResponse);
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(successResponse);
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    return gson.toJson(successResponse);
                case "editdevice":

                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("data") && params.containsKey("type")) {

                                Class typeClass = AutomationDeviceResponse.getClassForType(Integer.parseInt(params.get("type")));
                                if(typeClass != null) {

                                    AutomationDevice automationDevice = (AutomationDevice) gson.fromJson(params.get("data"), typeClass);
                                    if(automationDevice != null) {

                                        AutomationDeviceEditor automationDeviceEditor = ShcApplicationServer.getInstance().getAutomationDeviceEditor();
                                        synchronized (automationDeviceEditor) {

                                            AutomationDevice knownDevice = automationDeviceEditor.getAutomationDevices().get(automationDevice.getHash());
                                            if(knownDevice != null) {

                                                knownDevice.setName(automationDevice.getName());
                                                knownDevice.setComment(automationDevice.getComment());
                                                knownDevice.setDisabled(automationDevice.isDisabled());
                                                switch (knownDevice.getType()) {

                                                    case AutomationDevice.INPUT:

                                                        Input knownInput = (Input) knownDevice;
                                                        Input automationDeviceInput = (Input) automationDevice;
                                                        knownInput.setIdentifier(automationDeviceInput.getIdentifier());
                                                        knownInput.setSwitchServer(automationDeviceInput.getSwitchServer());
                                                        knownInput.setPin(automationDeviceInput.getPin());
                                                        knownInput.setInverse(automationDeviceInput.isInverse());
                                                        knownInput.setUseExternalData(automationDeviceInput.isUseExternalData());
                                                        knownInput.setInverse(automationDeviceInput.isInverse());
                                                        break;
                                                    case AutomationDevice.USER_AT_HOME:

                                                        UserAtHome konwnUserAtHome = (UserAtHome) knownDevice;
                                                        UserAtHome automationDeviceUserAtHome = (UserAtHome) automationDevice;
                                                        konwnUserAtHome.setIdentifier(automationDeviceUserAtHome.getIdentifier());
                                                        konwnUserAtHome.setIpAddress(automationDeviceUserAtHome.getIpAddress());
                                                        konwnUserAtHome.setTimeout(automationDeviceUserAtHome.getTimeout());
                                                        konwnUserAtHome.setUseExternalData(automationDeviceUserAtHome.isUseExternalData());
                                                        break;
                                                    case AutomationDevice.OUTPUT:

                                                        Output knownOutput = (Output) knownDevice;
                                                        Output automationDeviceOutput = (Output) automationDevice;
                                                        knownOutput.setSwitchServer(automationDeviceOutput.getSwitchServer());
                                                        knownOutput.setPin(automationDeviceOutput.getPin());
                                                        knownOutput.setInverse(automationDeviceOutput.isInverse());
                                                        break;
                                                    case AutomationDevice.AVM_SOCKET:

                                                        AvmSocket knownAvmSocket = (AvmSocket) knownDevice;
                                                        AvmSocket automationDeviceAvmSocket = (AvmSocket) automationDevice;
                                                        knownAvmSocket.setIdentifier(automationDeviceAvmSocket.getIdentifier());
                                                        knownAvmSocket.setInverse(automationDeviceAvmSocket.isInverse());
                                                        break;
                                                    case AutomationDevice.EDIMAX_SOCKET:

                                                        EdimaxSocket konwnEdimaxSocket = (EdimaxSocket) knownDevice;
                                                        EdimaxSocket automationDeviceEdimaxSocket = (EdimaxSocket) automationDevice;
                                                        konwnEdimaxSocket.setIpAddress(automationDeviceEdimaxSocket.getIpAddress());
                                                        konwnEdimaxSocket.setUsername(automationDeviceEdimaxSocket.getUsername());
                                                        konwnEdimaxSocket.setPassword(automationDeviceEdimaxSocket.getPassword());
                                                        konwnEdimaxSocket.setSocketType(automationDeviceEdimaxSocket.getSocketType());
                                                        konwnEdimaxSocket.setInverse(automationDeviceEdimaxSocket.isInverse());
                                                        break;
                                                    case AutomationDevice.RADIO_SOCKET:

                                                        RadioSocket konwnRadioSocket = (RadioSocket) knownDevice;
                                                        RadioSocket automationDeviceRadioSocket = (RadioSocket) automationDevice;
                                                        konwnRadioSocket.setProtocol(automationDeviceRadioSocket.getProtocol());
                                                        konwnRadioSocket.setSystemCode(automationDeviceRadioSocket.getSystemCode());
                                                        konwnRadioSocket.setDeviceCode(automationDeviceRadioSocket.getDeviceCode());
                                                        konwnRadioSocket.setContinues(automationDeviceRadioSocket.getContinues());
                                                        konwnRadioSocket.setUseID(automationDeviceRadioSocket.isUseID());
                                                        konwnRadioSocket.setInverse(automationDeviceRadioSocket.isInverse());
                                                        break;
                                                    case AutomationDevice.WAKE_ON_LAN:

                                                        WakeOnLan konwnWakeOnLan = (WakeOnLan) knownDevice;
                                                        WakeOnLan automationDeviceWakeOnLan = (WakeOnLan) automationDevice;
                                                        konwnWakeOnLan.setMac(automationDeviceWakeOnLan.getMac());
                                                        konwnWakeOnLan.setIpAddress(automationDeviceWakeOnLan.getIpAddress());
                                                        break;
                                                    case AutomationDevice.FRITZ_BOX_WLAN:

                                                        FritzBoxWirelessLan konwnFritzBoxWirelessLan = (FritzBoxWirelessLan) knownDevice;
                                                        FritzBoxWirelessLan automationDeviceFritzBoxWirelessLan = (FritzBoxWirelessLan) automationDevice;
                                                        konwnFritzBoxWirelessLan.setFunction(automationDeviceFritzBoxWirelessLan.getFunction());
                                                        konwnFritzBoxWirelessLan.setInverse(automationDeviceFritzBoxWirelessLan.isInverse());
                                                        break;
                                                    case AutomationDevice.FRITZ_BOX_REBOOT_RECONNECT:

                                                        FritzBoxRebootReconnect konwnFritzFritzBoxRebootReconnect = (FritzBoxRebootReconnect) knownDevice;
                                                        FritzBoxRebootReconnect automationDeviceFritzBoxRebootReconnect= (FritzBoxRebootReconnect) automationDevice;
                                                        konwnFritzFritzBoxRebootReconnect.setFunction(automationDeviceFritzBoxRebootReconnect.getFunction());
                                                        break;
                                                    case AutomationDevice.REBOOT_SHUTDOWN:

                                                        RebootShutdown konwnRebootShutdown = (RebootShutdown) knownDevice;
                                                        RebootShutdown automationDeviceRebootShutdown = (RebootShutdown) automationDevice;
                                                        konwnRebootShutdown.setSwitchServer(automationDeviceRebootShutdown.getSwitchServer());
                                                        konwnRebootShutdown.setFunction(automationDeviceRebootShutdown.getFunction());
                                                        break;
                                                    case AutomationDevice.SCRIPT_SINGLE:

                                                        ScriptSingle konwnScriptSingle = (ScriptSingle) knownDevice;
                                                        ScriptSingle automationDeviceScriptSingle = (ScriptSingle) automationDevice;
                                                        konwnScriptSingle.setSwitchServer(automationDeviceScriptSingle.getSwitchServer());
                                                        konwnScriptSingle.setCommand(automationDeviceScriptSingle.getCommand());
                                                        break;
                                                    case AutomationDevice.SCRIPT_DOUBLE:

                                                        ScriptDouble konwnScriptDouble = (ScriptDouble) knownDevice;
                                                        ScriptDouble automationDeviceScriptDouble = (ScriptDouble) automationDevice;
                                                        konwnScriptDouble.setSwitchServer(automationDeviceScriptDouble.getSwitchServer());
                                                        konwnScriptDouble.setOnCommand(automationDeviceScriptDouble.getOnCommand());
                                                        konwnScriptDouble.setOffCommand(automationDeviceScriptDouble.getOffCommand());
                                                        konwnScriptDouble.setInverse(automationDeviceScriptDouble.isInverse());
                                                        break;
                                                    case AutomationDevice.ACTUAL_POWER:
                                                    case AutomationDevice.ENERGY:
                                                    case AutomationDevice.AIR_PRESSURE:
                                                    case AutomationDevice.ALTITUDE:
                                                    case AutomationDevice.BATTERIE_LEVEL:
                                                    case AutomationDevice.DURATION:
                                                    case AutomationDevice.GAS_AMOUNT:
                                                    case AutomationDevice.WATER_AMOUNT:
                                                    case AutomationDevice.HUMIDITY:
                                                    case AutomationDevice.LIGHT_INTENSITY:
                                                    case AutomationDevice.MOISTURE:
                                                    case AutomationDevice.STRING:

                                                        SensorValue konwnSensorValue = (SensorValue) knownDevice;
                                                        SensorValue automationDeviceSensorValue = (SensorValue) automationDevice;
                                                        konwnSensorValue.setIdentifier(automationDeviceSensorValue.getIdentifier());
                                                        break;
                                                    case AutomationDevice.DISTANCE:

                                                        DistanceValue konwnDistanceValue = (DistanceValue) knownDevice;
                                                        DistanceValue automationDeviceDistanceValue = (DistanceValue) automationDevice;
                                                        konwnDistanceValue.setIdentifier(automationDeviceDistanceValue.getIdentifier());
                                                        konwnDistanceValue.setOffset(automationDeviceDistanceValue.getOffset());
                                                        break;
                                                    case AutomationDevice.TEMPERATURE:

                                                        TemperatureValue konwnDistanceTemperatureValue = (TemperatureValue) knownDevice;
                                                        TemperatureValue automationDeviceTemperatureValue = (TemperatureValue) automationDevice;
                                                        konwnDistanceTemperatureValue.setIdentifier(automationDeviceTemperatureValue.getIdentifier());
                                                        konwnDistanceTemperatureValue.setOffset(automationDeviceTemperatureValue.getOffset());
                                                        break;
                                                    case AutomationDevice.VIRTUAL_ACTUAL_POWER:
                                                    case AutomationDevice.VIRTUAL_ENERGY:
                                                    case AutomationDevice.VIRTUAL_GAS_AMOUNT:
                                                    case AutomationDevice.VIRTUAL_WATER_AMOUNT:
                                                    case AutomationDevice.VIRTUAL_LIGHT_INTENSITY:
                                                    case AutomationDevice.VIRTUAL_TEMPERATURE:

                                                        VirtualSensorValue konwnVirtualSensorValue = (VirtualSensorValue) knownDevice;
                                                        VirtualSensorValue automationDeviceVirtualSensorValue = (VirtualSensorValue) automationDevice;
                                                        konwnVirtualSensorValue.getSensorValues().clear();
                                                        konwnVirtualSensorValue.getSensorValues().addAll(automationDeviceVirtualSensorValue.getSensorValues());
                                                        break;
                                                }
                                                successResponse.setSuccess(true);
                                            } else {

                                                //Fehler ungültiges Element
                                                successResponse.setSuccess(false);
                                                successResponse.setMessage("Ungültiges Element");
                                            }
                                        }
                                    } else {

                                        //Fehler ungültige Daten
                                        successResponse.setSuccess(false);
                                        successResponse.setMessage("Ungültige Daten");
                                    }
                                } else {

                                    //Fehler ungältiger Typ
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("Ungültiger Typ");
                                }
                            } else {

                                //fehlender Parameter hash
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"hash\" oder \"type\"");
                            }
                            return gson.toJson(successResponse);
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(successResponse);
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    return gson.toJson(successResponse);
                case "deletedevice":

                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("hash")) {

                                AutomationDeviceEditor automationDeviceEditor = ShcApplicationServer.getInstance().getAutomationDeviceEditor();
                                synchronized (automationDeviceEditor) {

                                    AutomationDevice automationDevice = automationDeviceEditor.getAutomationDevices().get(params.get("hash"));
                                    if(automationDevice != null) {

                                        if(automationDevice instanceof SensorValue && !((SensorValue) automationDevice).isSystemValue()) {

                                            automationDeviceEditor.getAutomationDevices().remove(params.get("hash"));
                                            successResponse.setSuccess(true);
                                        } else if(!(automationDevice instanceof SensorValue)) {

                                            automationDeviceEditor.getAutomationDevices().remove(params.get("hash"));
                                            successResponse.setSuccess(true);
                                        } else {

                                            //System Sensorwerte können nicht gelöscht werden
                                            successResponse.setSuccess(false);
                                            successResponse.setMessage("System Sensorwerte können nicht gelöscht werden");
                                        }
                                    } else {

                                        //ungültiger Hash
                                        successResponse.setSuccess(false);
                                        successResponse.setMessage("ungültiger Hash");
                                    }
                                }
                            } else {

                                //fehlender Parameter hash
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"hash\"");
                            }
                            return gson.toJson(successResponse);
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(successResponse);
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    return gson.toJson(successResponse);
            }
        }
        return "";
    }
}
