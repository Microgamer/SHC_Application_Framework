package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Automationdevice;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Automation.AutomationDeviceEditor;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.AvmSocket;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.EdimaxSocket;
import net.kleditzsch.shcCore.Automation.Interface.AutomationDevice;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Automatisierungsgeräte löschen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DeleteHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(DeleteHandler.class);

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

        logger.info("Automatisierungsgerät löschen");
        successResponse = new SuccessResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                //Befehl ausführen
                if(params.containsKey("hash")) {

                    AutomationDeviceEditor automationDeviceEditor = ShcApplicationServer.getInstance().getAutomationDeviceEditor();
                    synchronized (ShcApplicationServer.getInstance().getAutomationDeviceEditor()) {

                        AutomationDevice automationDevice = automationDeviceEditor.getAutomationDevices().get(params.get("hash"));
                        if(automationDevice != null) {

                            if(automationDevice instanceof SensorValue && !((SensorValue) automationDevice).isSystemValue()) {

                                automationDeviceEditor.getAutomationDevices().remove(params.get("hash"));
                                successResponse.setSuccess(true);
                            } else if(!(automationDevice instanceof SensorValue)) {

                                if(automationDevice instanceof AvmSocket) {

                                    //Sensowerte löschen
                                    automationDeviceEditor.getAutomationDevices().remove(((AvmSocket) automationDevice).getTempSensorHash());
                                    automationDeviceEditor.getAutomationDevices().remove(((AvmSocket) automationDevice).getPowerSensorHash());
                                    automationDeviceEditor.getAutomationDevices().remove(((AvmSocket) automationDevice).getEnergySensorHash());
                                } else if(automationDevice instanceof EdimaxSocket && ((EdimaxSocket) automationDevice).getSocketType() == EdimaxSocket.TYPE_SP_2101W) {

                                    //Sensorwerte löschen
                                    automationDeviceEditor.getAutomationDevices().remove(((EdimaxSocket) automationDevice).getPowerSensorHash());
                                    automationDeviceEditor.getAutomationDevices().remove(((EdimaxSocket) automationDevice).getEnergySensorHash());
                                }
                                automationDeviceEditor.getAutomationDevices().remove(params.get("hash"));
                                successResponse.setSuccess(true);
                                logger.info("Automatisierungsgerät \"" + automationDevice.getName() + "\" erfolgreich gelöscht");
                            } else {

                                //System Sensorwerte können nicht gelöscht werden
                                successResponse.setSuccess(false);
                                successResponse.setMessage("System Sensorwerte können nicht gelöscht werden");
                                logger.warning(successResponse.getMessage());
                            }
                        } else {

                            //ungültiger Hash
                            successResponse.setSuccess(false);
                            successResponse.setMessage("ungültiger Hash");
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
