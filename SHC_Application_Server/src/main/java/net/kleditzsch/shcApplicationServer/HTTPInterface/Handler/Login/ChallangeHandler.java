package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Login;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcCore.Core.BasicElement;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Behandelt eine Challange Anfrage
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ChallangeHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(ChallangeHandler.class);

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson   Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        //Chalange erzeugen
        logger.info("Challange angefragt");
        String challange = BasicElement.createHash().substring(0, 15);
        synchronized (ShcApplicationServer.getInstance().getSessionEditor()) {

            ShcApplicationServer.getInstance().getSessionEditor().getChallenges().add(challange);
        }
        logger.info("Challange: " + challange);
        return challange;
    }
}
