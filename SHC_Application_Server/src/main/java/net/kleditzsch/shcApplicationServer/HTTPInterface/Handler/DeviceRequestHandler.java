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
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Geräte Anfragemanager
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DeviceRequestHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(DeviceRequestHandler.class);

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
            String json;
            switch (params.get("action")) {

                case "listdevices":

                    logger.info("Geräte listen");
                    deviceResponse = new DeviceResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Geräte auflisten
                            DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
                            synchronized (deviceManager) {

                                for(String clientHash : deviceManager.getDevices().keySet()) {

                                    ClientDevice clientDevice = deviceManager.getDevices().get(clientHash);
                                    DeviceData deviceData = new DeviceData();
                                    deviceData.setClientHash(clientDevice.getClientHash());
                                    deviceData.setUserAgend(clientDevice.getUserAgend());
                                    deviceData.setAllowed(clientDevice.isAllowed());
                                    deviceData.setLastLogin(clientDevice.getLastLogin());
                                    deviceResponse.getDeviceDataList().add(deviceData);
                                }
                            }

                            //Antwort
                            deviceResponse.setSuccess(true);
                            logger.info("Geräte gelistet");
                            json = gson.toJson(deviceResponse);
                            logger.fine(json);
                            return json;
                        }
                        //nicht Berechtigt
                        deviceResponse.setSuccess(false);
                        deviceResponse.setErrorCode(101);
                        deviceResponse.setMessage("Fehlende Berechtigung");
                        logger.warning(deviceResponse.getMessage());
                        json = gson.toJson(deviceResponse);
                        logger.fine(json);
                        return json;
                    }
                    //Ungültige Session
                    deviceResponse.setSuccess(false);
                    deviceResponse.setErrorCode(100);
                    deviceResponse.setMessage("Ungültige Session");
                    logger.warning(deviceResponse.getMessage());
                    json = gson.toJson(deviceResponse);
                    logger.fine(json);
                    return json;
                case "setdeviceallowed":

                    logger.info("Gerät erlauben");
                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.DEVICE_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("hash")) {

                                DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
                                synchronized (deviceManager) {

                                    ClientDevice clientDevice = deviceManager.getDevices().get(params.get("hash"));
                                    if (clientDevice != null) {

                                        clientDevice.setAllowed(true);
                                        successResponse.setSuccess(true);
                                        logger.info("Gerät erleubt");
                                    } else {

                                        //ungültige Daten
                                        successResponse.setSuccess(false);
                                        successResponse.setMessage("ungültige Daten");
                                        logger.warning(successResponse.getMessage());
                                    }
                                }
                            } else {

                                //fehlender Parameter hash
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"hash\"");
                                logger.warning(successResponse.getMessage());
                            }
                            json = gson.toJson(successResponse);
                            logger.fine(json);
                            return json;
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        logger.warning(successResponse.getMessage());
                        json = gson.toJson(successResponse);
                        logger.fine(json);
                        return json;
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    logger.warning(successResponse.getMessage());
                    json = gson.toJson(successResponse);
                    logger.fine(json);
                    return json;
                case "setdevicedenied":

                    logger.info("Gerät sperren");
                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("hash")) {

                                DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
                                synchronized (deviceManager) {

                                    ClientDevice clientDevice = deviceManager.getDevices().get(params.get("hash"));
                                    if (clientDevice != null) {

                                        clientDevice.setAllowed(false);
                                        successResponse.setSuccess(true);
                                        logger.info("Gerät gesperrt");
                                    } else {

                                        //ungültige Daten
                                        successResponse.setSuccess(false);
                                        successResponse.setMessage("ungültige Daten");
                                        logger.warning(successResponse.getMessage());
                                    }
                                }
                            } else {

                                //fehlender Parameter hash
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"hash\"");
                                logger.warning(successResponse.getMessage());
                            }
                            json = gson.toJson(successResponse);
                            logger.fine(json);
                            return json;
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        logger.warning(successResponse.getMessage());
                        json = gson.toJson(successResponse);
                        logger.fine(json);
                        return json;
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    logger.warning(successResponse.getMessage());
                    json = gson.toJson(successResponse);
                    logger.fine(json);
                    return json;
                case "deletedevice":

                    logger.info("Gerät löschen");
                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("hash")) {

                                DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
                                synchronized (deviceManager) {

                                    ClientDevice clientDevice = deviceManager.getDevices().get(params.get("hash"));
                                    if (clientDevice != null) {

                                        deviceManager.getDevices().remove(clientDevice.getClientHash());
                                        successResponse.setSuccess(true);
                                        logger.info("Gerät gelöscht");
                                    } else {

                                        //ungültige Daten
                                        successResponse.setSuccess(false);
                                        successResponse.setMessage("ungültige Daten");
                                        logger.warning(successResponse.getMessage());
                                    }
                                }
                            } else {

                                //fehlender Parameter hash
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"hash\"");
                                logger.warning(successResponse.getMessage());
                            }
                            json = gson.toJson(successResponse);
                            logger.fine(json);
                            return json;
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        logger.warning(successResponse.getMessage());
                        json = gson.toJson(successResponse);
                        logger.fine(json);
                        return json;
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    logger.warning(successResponse.getMessage());
                    json = gson.toJson(successResponse);
                    logger.fine(json);
                    return json;
            }
        }
        return "";
    }
}
