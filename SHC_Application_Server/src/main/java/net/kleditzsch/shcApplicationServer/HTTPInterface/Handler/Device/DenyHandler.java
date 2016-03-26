package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Device;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.DeviceManager.ClientDevice;
import net.kleditzsch.shcApplicationServer.DeviceManager.DeviceManager;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Gerät sperren
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DenyHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(DenyHandler.class);

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson   Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        SuccessResponse successResponse;
        User sessionUser = checkSession(params);
        String json;

        logger.info("Gerät sperren");
        successResponse = new SuccessResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                //Befehl ausführen
                if(params.containsKey("hash")) {

                    DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
                    synchronized (ShcApplicationServer.getInstance().getDeviceManager()) {

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
    }
}
