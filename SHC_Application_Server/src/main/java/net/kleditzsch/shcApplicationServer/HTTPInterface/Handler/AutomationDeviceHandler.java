package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Automation.AutomationDeviceEditor;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.SwitchServer.SwitchServerEditor;
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
                            automationDeviceResponse.getAutomationDevices().putAll(automationDeviceEditor.getAutomationDevices());

                            //Schaltserver Auflisten
                            SwitchServerEditor switchServerEditor = ShcApplicationServer.getInstance().getSwitchServerEditor();
                            automationDeviceResponse.getSwitchServers().putAll(switchServerEditor.getSwitchServers());

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
                            if(params.containsKey("hash")) {


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
                case "deletedevice":

                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("hash")) {


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
