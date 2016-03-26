package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.User;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Benutzergruppe bearbeiten
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class EditGroupHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(EditGroupHandler.class);

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

        //Benutzergruppe bearbeiten
        logger.info("Benutzergruppe bearbeiten");
        successResponse = new SuccessResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                if(params.containsKey("data")) {

                    UserGroupData userGroupData = gson.fromJson(params.get("data"), UserGroupData.class);
                    if(userGroupData != null) {

                        UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                        synchronized (ShcApplicationServer.getInstance().getUserEditor()) {

                            if(userEditor.getUserGroupByHash(userGroupData.getHash()) != null) {

                                UserGroup userGroup = userEditor.getUserGroupByHash(userGroupData.getHash());
                                userGroup.setHash(userGroupData.getHash());
                                userGroup.setName(userGroupData.getName());
                                userGroup.setDescripion(userGroupData.getDescripion());
                                userGroup.getPermissions().clear();
                                userGroup.getPermissions().addAll(userGroupData.getPermissions());

                                successResponse.setSuccess(true);
                                logger.info("Benutzergruppe bearbeitet");
                            } else {

                                //benutzer existiert bereits
                                successResponse.setSuccess(false);
                                successResponse.setMessage("die Benutzergruppe existiert nicht");
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
