package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.User;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Benutzergruppe löschen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DeleteGroupHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(AddHandler.class);

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

        //Benutzergruppe löschen
        logger.info("Benutzergruppe löschen");
        successResponse = new SuccessResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                if(params.containsKey("hash")) {

                    String hash = params.get("hash");
                    if(hash.length() > 10) {

                        UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                        synchronized (ShcApplicationServer.getInstance().getUserEditor()) {

                            UserGroup userGroup = userEditor.getUserGroupByHash(hash);
                            if(userGroup != null) {

                                if(!userGroup.isSystemGroup()) {

                                    if(userEditor.removeUserGroup(userGroup)) {

                                        //erfolgreich
                                        successResponse.setSuccess(true);
                                        logger.info("Benutzergruppe gelöscht");
                                    } else {

                                        //fehler beim löschen
                                        successResponse.setSuccess(false);
                                        successResponse.setMessage("");
                                    }
                                } else {

                                    //Systemgruppe
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("Die Benutzergruppe kann nicht gelöscht werden");
                                    logger.warning(successResponse.getMessage());
                                }
                            } else {

                                //ungültiger Benutzer
                                successResponse.setSuccess(false);
                                successResponse.setMessage("ungültige Benutzergruppe");
                                logger.warning(successResponse.getMessage());
                            }
                        }
                    } else {

                        //ungültiger Hash
                        successResponse.setSuccess(false);
                        successResponse.setMessage("ungültiger Hash");
                        logger.warning(successResponse.getMessage());
                    }
                } else {

                    //fehlender Parameter Hash
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
