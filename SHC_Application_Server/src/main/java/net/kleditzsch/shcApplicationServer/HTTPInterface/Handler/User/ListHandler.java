package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.User;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcCore.ClientData.User.UserAdministrationResponse;
import net.kleditzsch.shcCore.ClientData.User.UserData;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Benutzer und Gruppen auflisten
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

        UserAdministrationResponse userAdministrationResponse;
        User sessionUser = checkSession(params);
        String json;

        logger.info("Benutzer listen");
        userAdministrationResponse = new UserAdministrationResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                //Benutzer auflisten
                UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                synchronized (ShcApplicationServer.getInstance().getUserEditor()) {

                    Set<User> users = userEditor.getUsers();
                    Set<UserGroup> groups = userEditor.getUserGroups();

                    //Daten zum senden Vorbereiten
                    //Benutzer
                    for (User user1 : users) {

                        UserData userData = new UserData();
                        userData.setHash(user1.getHash());
                        userData.setName(user1.getName());
                        userData.setPasswordHash(user1.getPasswordHash());
                        userData.setOriginator(user1.isOriginator());
                        for (UserGroup userGroup : user1.getUserGroups()) {

                            userData.getUserGroups().add(userGroup.getHash());
                        }

                        userAdministrationResponse.getUserDataList().add(userData);
                    }

                    //Benutzergruppen
                    for (UserGroup userGroup : groups) {

                        UserGroupData groupData = new UserGroupData();
                        groupData.setHash(userGroup.getHash());
                        groupData.setName(userGroup.getName());
                        groupData.setDescripion(userGroup.getDescripion());
                        groupData.setSystemGroup(userGroup.isSystemGroup());
                        groupData.getPermissions().addAll(userGroup.getPermissions());

                        userAdministrationResponse.getGroupDataList().add(groupData);
                    }

                    //Berechtigungen
                    userAdministrationResponse.getPermissions().addAll(Permissions.listPermissions());

                    //Antwort
                    userAdministrationResponse.setSuccess(true);
                    logger.info("Geräte gelistet");
                    json = gson.toJson(userAdministrationResponse);
                    logger.fine(json);
                    return json;
                }

            }
            //nicht Berechtigt
            userAdministrationResponse.setSuccess(false);
            userAdministrationResponse.setErrorCode(101);
            userAdministrationResponse.setMessage("Fehlende Berechtigung");
            logger.warning(userAdministrationResponse.getMessage());
            json = gson.toJson(userAdministrationResponse);
            logger.fine(json);
            return json;
        }
        //Ungültige Session
        userAdministrationResponse.setSuccess(false);
        userAdministrationResponse.setErrorCode(100);
        userAdministrationResponse.setMessage("Ungültige Session");
        logger.warning(userAdministrationResponse.getMessage());
        json = gson.toJson(userAdministrationResponse);
        logger.fine(json);
        return json;
    }
}
