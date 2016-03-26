package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.User;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.ClientData.User.UserData;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Benutzer Hinzufügen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AddHandler extends AbstractRequestHandler {

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

        //Benutzer erstellen
        logger.info("Benutzer erstellen");
        successResponse = new SuccessResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                if(params.containsKey("data")) {

                    UserData userData = gson.fromJson(params.get("data"), UserData.class);
                    if(userData != null) {

                        UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                        synchronized (ShcApplicationServer.getInstance().getUserEditor()) {

                            if(userEditor.getUserByHash(userData.getHash()) == null && userEditor.getUserByName(userData.getName()) == null) {

                                User user2 = new User();
                                user2.setHash(userData.getHash());
                                user2.setName(userData.getName());
                                user2.setPasswordHash(userData.getPasswordHash());
                                for(String groupHash : userData.getUserGroups()) {

                                    UserGroup userGroup = userEditor.getUserGroupByHash(groupHash);
                                    if(userGroup != null) {

                                        user2.getUserGroups().add(userGroup);
                                    }
                                }

                                if(userEditor.addUser(user2)) {

                                    //erfolgreich
                                    successResponse.setSuccess(true);
                                    logger.info("Benutzer erstellt");
                                } else {

                                    //fehler
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("der Benutzer konnte nicht erstellt werden");
                                    logger.warning(successResponse.getMessage());
                                }
                            } else {

                                //benutzer existiert bereits
                                successResponse.setSuccess(false);
                                successResponse.setMessage("der Benutzer existiert bereits");
                                logger.warning(successResponse.getMessage());
                            }
                        }
                    } else {

                        //ungültige Daten
                        successResponse.setSuccess(false);
                        successResponse.setMessage("ungültige Daten");
                        logger.warning(successResponse.getMessage());
                    }
                } else {

                    //fehlender Parameter Data
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(200);
                    successResponse.setMessage("Fehlender Parameter \"data\"");
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
