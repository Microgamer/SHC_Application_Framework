package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.DeviceManager.ClientDevice;
import net.kleditzsch.shcApplicationServer.DeviceManager.DeviceManager;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcCore.ClientData.Device.DeviceData;
import net.kleditzsch.shcCore.ClientData.Device.DeviceResponse;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;

import java.util.Map;

/**
 * Geräte Anfragemanager
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DeviceRequestHandler extends AbstractRequestHandler {

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

            DeviceResponse deviceResponse;
            SuccessResponse successResponse;
            User sessionUser = checkSession(params);
            switch (params.get("action")) {

                case "listdevices":

                    deviceResponse = new DeviceResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Geräte auflisten
                            DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
                            for(String clientHash : deviceManager.getDevices().keySet()) {

                                ClientDevice clientDevice = deviceManager.getDevices().get(clientHash);
                                DeviceData deviceData = new DeviceData();
                                deviceData.setClientHash(clientDevice.getClientHash());
                                deviceData.setUserAgend(clientDevice.getUserAgend());
                                deviceData.setAllowed(clientDevice.isAllowed());
                                deviceResponse.getDeviceDataList().add(deviceData);
                            }

                            //Antwort
                            deviceResponse.setSuccess(true);
                            return gson.toJson(deviceResponse);
                        }
                        //nicht Berechtigt
                        deviceResponse.setSuccess(false);
                        deviceResponse.setErrorCode(101);
                        deviceResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(deviceResponse);
                    }
                    //Ungültige Session
                    deviceResponse.setSuccess(false);
                    deviceResponse.setErrorCode(100);
                    deviceResponse.setMessage("Ungültige Session");
                    return gson.toJson(deviceResponse);
                case "setdeviceallowed":

                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("hash")) {

                                DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
                                ClientDevice clientDevice = deviceManager.getDevices().get(params.get("hash"));
                                if (clientDevice != null) {

                                    clientDevice.setAllowed(true);
                                    successResponse.setSuccess(true);
                                } else {

                                    //ungültige Daten
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("ungültige Daten");
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
                case "setdevicedenied":

                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("hash")) {

                                DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
                                ClientDevice clientDevice = deviceManager.getDevices().get(params.get("hash"));
                                if (clientDevice != null) {

                                    clientDevice.setAllowed(false);
                                    successResponse.setSuccess(true);
                                } else {

                                    //ungültige Daten
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("ungültige Daten");
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
                case "deletedevice":

                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("hash")) {

                                DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
                                ClientDevice clientDevice = deviceManager.getDevices().get(params.get("hash"));
                                if (clientDevice != null) {

                                    deviceManager.getDevices().remove(clientDevice.getClientHash());
                                    successResponse.setSuccess(true);
                                } else {

                                    //ungültige Daten
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("ungültige Daten");
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
