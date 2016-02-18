package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.ClientData.User.UserAdministrationResponse;
import net.kleditzsch.shcCore.ClientData.User.UserData;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;

import java.util.Map;
import java.util.Set;

/**
 * Benutzerverwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserAdministartionRequestHandler extends AbstractRequestHandler {

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        //Berechtigt
        if(params.containsKey("action")) {

            SuccessResponse successResponse;
            UserAdministrationResponse userAdministrationResponse;
            User sessionUser = checkSession(params);;
            switch (params.get("action")) {

                case "listusers":

                    userAdministrationResponse = new UserAdministrationResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Benutzer auflisten
                            UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                            synchronized (userEditor) {

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
                                return gson.toJson(userAdministrationResponse);
                            }

                        }
                        //nicht Berechtigt
                        userAdministrationResponse.setSuccess(false);
                        userAdministrationResponse.setErrorCode(101);
                        userAdministrationResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(userAdministrationResponse);
                    }
                    //Ungültige Session
                    userAdministrationResponse.setSuccess(false);
                    userAdministrationResponse.setErrorCode(100);
                    userAdministrationResponse.setMessage("Ungültige Session");
                    return gson.toJson(userAdministrationResponse);
                case "adduser":

                    //Benutzer erstellen
                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            if(params.containsKey("data")) {

                                UserData userData = gson.fromJson(params.get("data"), UserData.class);
                                if(userData != null) {

                                    UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                                    synchronized (userEditor) {

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
                                            } else {

                                                //fehler
                                                successResponse.setSuccess(false);
                                                successResponse.setMessage("der Benutzer konnte nicht erstellt werden");
                                            }
                                        } else {

                                            //benutzer existiert bereits
                                            successResponse.setSuccess(false);
                                            successResponse.setMessage("der Benutzer existiert bereits");
                                        }
                                    }
                                } else {

                                    //ungültige Daten
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("ungültige Daten");
                                }
                            } else {

                                //fehlender Parameter Data
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"data\"");
                            }
                            return gson.toJson(successResponse);
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(successResponse);
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    return gson.toJson(successResponse);
                case "edituser":

                    //Benutzer bearbeiten
                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            if(params.containsKey("data")) {

                                UserData userData = gson.fromJson(params.get("data"), UserData.class);
                                if(userData != null) {

                                    UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                                    synchronized (userEditor) {

                                        if(userEditor.getUserByHash(userData.getHash()) != null) {

                                            User user2 = userEditor.getUserByHash(userData.getHash());
                                            user2.setName(userData.getName());
                                            user2.setPasswordHash(userData.getPasswordHash());
                                            user2.getUserGroups().clear();
                                            for(String groupHash : userData.getUserGroups()) {

                                                UserGroup userGroup = userEditor.getUserGroupByHash(groupHash);
                                                if(userGroup != null) {

                                                    user2.getUserGroups().add(userGroup);
                                                }
                                            }
                                            successResponse.setSuccess(true);
                                        } else {

                                            //benutzer existiert nicht
                                            successResponse.setSuccess(false);
                                            successResponse.setMessage("der Benutzer existiert nicht");
                                        }
                                    }
                                } else {

                                    //ungültige Daten
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("ungültige Daten");
                                }
                            } else {

                                //fehlender Parameter Data
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"data\"");
                            }
                            return gson.toJson(successResponse);
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(successResponse);
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    return gson.toJson(successResponse);
                case "deleteuser":

                    //Benutzer löschen
                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            if(params.containsKey("hash")) {

                                String hash = params.get("hash");
                                if(hash.length() > 10) {

                                    UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                                    synchronized (userEditor) {

                                        User user2 = userEditor.getUserByHash(hash);
                                        if(user2 != null) {

                                            if(!user2.isOriginator()) {

                                                if(userEditor.removeUser(user2)) {

                                                    //erfolgreich
                                                    successResponse.setSuccess(true);
                                                } else {

                                                    //fehler beim löschen
                                                    successResponse.setSuccess(false);
                                                    successResponse.setMessage("");
                                                }
                                            } else {

                                                //Systembenutzer
                                                successResponse.setSuccess(false);
                                                successResponse.setMessage("Der Benutzer kann nicht gelöscht werden");
                                            }
                                        } else {

                                            //ungültiger Benutzer
                                            successResponse.setSuccess(false);
                                            successResponse.setMessage("ungültiger Benutzer");
                                        }
                                    }
                                } else {

                                    //ungültiger Hash
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("ungültiger Hash");
                                }
                            } else {

                                //fehlender Parameter Hash
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"hash\"");
                            }
                            return gson.toJson(successResponse);
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(successResponse);
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    return gson.toJson(successResponse);

                case "addusergroup":

                    //Benutzergruppe erstellen
                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            if(params.containsKey("data")) {

                                UserGroupData userGroupData = gson.fromJson(params.get("data"), UserGroupData.class);
                                if(userGroupData != null) {

                                    UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                                    synchronized (userEditor) {

                                        if(userEditor.getUserGroupByHash(userGroupData.getHash()) == null && userEditor.getUserGroupByName(userGroupData.getName()) == null) {

                                            UserGroup userGroup = new UserGroup();
                                            userGroup.setHash(userGroupData.getHash());
                                            userGroup.setName(userGroupData.getName());
                                            userGroup.setDescripion(userGroupData.getDescripion());
                                            userGroup.getPermissions().addAll(userGroupData.getPermissions());

                                            if(userEditor.addUserGroup(userGroup)) {

                                                //erfolgreich
                                                successResponse.setSuccess(true);
                                            } else {

                                                //fehler
                                                successResponse.setSuccess(false);
                                                successResponse.setMessage("die Benutzergruppe konnte nicht erstellt werden");
                                            }
                                        } else {

                                            //benutzer existiert bereits
                                            successResponse.setSuccess(false);
                                            successResponse.setMessage("die Benutzergruppe existiert bereits");
                                        }
                                    }
                                } else {

                                    //ungültige Daten
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("ungültige Daten");
                                }
                            } else {

                                //fehlender Parameter Data
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"data\"");
                            }
                            return gson.toJson(successResponse);
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(successResponse);
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    return gson.toJson(successResponse);
                case "editeusergroup":

                    //Benutzergruppe bearbeiten
                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            if(params.containsKey("data")) {

                                UserGroupData userGroupData = gson.fromJson(params.get("data"), UserGroupData.class);
                                if(userGroupData != null) {

                                    UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                                    synchronized (userEditor) {

                                        if(userEditor.getUserGroupByHash(userGroupData.getHash()) != null) {

                                            UserGroup userGroup = userEditor.getUserGroupByHash(userGroupData.getHash());
                                            userGroup.setHash(userGroupData.getHash());
                                            userGroup.setName(userGroupData.getName());
                                            userGroup.setDescripion(userGroupData.getDescripion());
                                            userGroup.getPermissions().clear();
                                            userGroup.getPermissions().addAll(userGroupData.getPermissions());

                                            successResponse.setSuccess(true);
                                        } else {

                                            //benutzer existiert bereits
                                            successResponse.setSuccess(false);
                                            successResponse.setMessage("die Benutzergruppe existiert nicht");
                                        }
                                    }
                                } else {

                                    //ungültige Daten
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("ungültige Daten");
                                }
                            } else {

                                //fehlender Parameter Data
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"data\"");
                            }
                            return gson.toJson(successResponse);
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(successResponse);
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    return gson.toJson(successResponse);
                case "deleteusergroup":

                    //Benutzergruppe löschen
                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            if(params.containsKey("hash")) {

                                String hash = params.get("hash");
                                if(hash.length() > 10) {

                                    UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
                                    synchronized (userEditor) {

                                        UserGroup userGroup = userEditor.getUserGroupByHash(hash);
                                        if(userGroup != null) {

                                            if(!userGroup.isSystemGroup()) {

                                                if(userEditor.removeUserGroup(userGroup)) {

                                                    //erfolgreich
                                                    successResponse.setSuccess(true);
                                                } else {

                                                    //fehler beim löschen
                                                    successResponse.setSuccess(false);
                                                    successResponse.setMessage("");
                                                }
                                            } else {

                                                //Systemgruppe
                                                successResponse.setSuccess(false);
                                                successResponse.setMessage("Die Benutzergruppe kann nicht gelöscht werden");
                                            }
                                        } else {

                                            //ungültiger Benutzer
                                            successResponse.setSuccess(false);
                                            successResponse.setMessage("ungültige Benutzergruppe");
                                        }
                                    }
                                } else {

                                    //ungültiger Hash
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("ungültiger Hash");
                                }
                            } else {

                                //fehlender Parameter Hash
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"hash\"");
                            }
                            return gson.toJson(successResponse);
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(successResponse);
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    return gson.toJson(successResponse);
            }
        }
        return "";
    }
}
