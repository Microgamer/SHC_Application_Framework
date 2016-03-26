package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Device;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.DeviceManager.ClientDevice;
import net.kleditzsch.shcApplicationServer.DeviceManager.DeviceManager;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcCore.ClientData.Device.DeviceData;
import net.kleditzsch.shcCore.ClientData.Device.DeviceResponse;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ListHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(ListHandler.class);

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson   Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        DeviceResponse deviceResponse;
        User sessionUser = checkSession(params);
        String json;

        logger.info("Geräte listen");
        deviceResponse = new DeviceResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                //Geräte auflisten
                DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
                synchronized (ShcApplicationServer.getInstance().getDeviceManager()) {

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
    }
}
