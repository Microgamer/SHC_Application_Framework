package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Automationdevice;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Automation.AutomationDeviceEditor;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.SwitchServer.SwitchServerEditor;
import net.kleditzsch.shcCore.ClientData.AutomationDevice.AutomationDeviceResponse;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Automatisierungsgeräte auflisten
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

        AutomationDeviceResponse automationDeviceResponse;
        User sessionUser = checkSession(params);
        String json;

        logger.info("Automatisierungsgeräte Listen");
        automationDeviceResponse = new AutomationDeviceResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.ELEMENT_ADMINISTRATION)) {

                //Automationsgeräte auflisten
                AutomationDeviceEditor automationDeviceEditor = ShcApplicationServer.getInstance().getAutomationDeviceEditor();
                synchronized (ShcApplicationServer.getInstance().getAutomationDeviceEditor()) {

                    automationDeviceResponse.getAutomationDevices().putAll(automationDeviceEditor.getAutomationDevices());

                    //Schaltserver Auflisten
                    SwitchServerEditor switchServerEditor = ShcApplicationServer.getInstance().getSwitchServerEditor();
                    automationDeviceResponse.getSwitchServers().putAll(switchServerEditor.getSwitchServers());
                }

                //Antwort
                automationDeviceResponse.setSuccess(true);
                logger.info("Automatisierungsgeräte erfolgreich gelistet");
                json = gson.toJson(automationDeviceResponse);
                logger.fine(json);
                return json;
            }
            //nicht Berechtigt
            automationDeviceResponse.setSuccess(false);
            automationDeviceResponse.setErrorCode(101);
            automationDeviceResponse.setMessage("Fehlende Berechtigung");
            logger.warning(automationDeviceResponse.getMessage());
            json = gson.toJson(automationDeviceResponse);
            logger.fine(json);
            return json;
        }
        //Ungültige Session
        automationDeviceResponse.setSuccess(false);
        automationDeviceResponse.setErrorCode(100);
        automationDeviceResponse.setMessage("Ungültige Session");
        logger.warning(automationDeviceResponse.getMessage());
        json = gson.toJson(automationDeviceResponse);
        logger.fine(json);
        return json;
    }
}
