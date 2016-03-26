package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Login;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.DeviceManager.ClientDevice;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.Session.SessionEditor;
import net.kleditzsch.shcApplicationServer.Settings.Settings;
import net.kleditzsch.shcCore.ClientData.Login.LoginResponse;
import net.kleditzsch.shcCore.Icon.Icon;
import net.kleditzsch.shcCore.Settings.BooleanSetting;
import net.kleditzsch.shcCore.Settings.Interface.Setting;
import net.kleditzsch.shcCore.User.ChallangeResponseUtil;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Behandelt eine Login Anfrage
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LoginHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(LoginHandler.class);

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson   Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        SessionEditor sessionEditor = ShcApplicationServer.getInstance().getSessionEditor();
        String json;

        String challangeResponse = params.get("challangeResponse");
        LoginResponse loginResponse = new LoginResponse();

        //Challange Response prüfen
        logger.info("Login angefragt");
        Set<String> challenges = ShcApplicationServer.getInstance().getSessionEditor().getChallenges();
        for(String challange : challenges) {

            if(challangeResponse.startsWith(challange)) {

                String userResponse = challangeResponse.replace(challange + "-", "");
                String userName = userResponse.substring(0, userResponse.length() - 41);

                //Challange Response prüfen
                User user = ShcApplicationServer.getInstance().getUserEditor().getUserByName(userName);
                if(user != null) {

                    String passwordHash = user.getPasswordHash();
                    Map<String, ClientDevice> devices = ShcApplicationServer.getInstance().getDeviceManager().getDevices();
                    synchronized (ShcApplicationServer.getInstance().getSessionEditor()) {

                        for(String deviceHash : devices.keySet()) {

                            try {

                                String testResponse = ChallangeResponseUtil.computeChallangeResponse(challange, userName, passwordHash, deviceHash);
                                if(testResponse.equals(challangeResponse)) {

                                    //Gerät gefunden
                                    ClientDevice device = devices.get(deviceHash);
                                    if(device != null) {

                                        if(device.isAllowed()) {

                                            //zutritt für dieses Gerät erlaubt
                                            String sessionId = sessionEditor.login(user);
                                            sessionEditor.getChallenges().remove(challange);

                                            //Update letzrer Login
                                            device.setLastLogin(LocalDateTime.now());

                                            //Berechtigungen für den Benutzer mit senden
                                            for(String permission : Permissions.listPermissions()) {

                                                if(user.checkPermission(permission)) {

                                                    loginResponse.getPermissions().add(permission);
                                                }
                                            }

                                            //FritzBox aktivierung mit senden
                                            BooleanSetting fritzBoxActive = ShcApplicationServer.getInstance().getSettings().getBooleanSetting(Settings.SETTING_FRITZBOX_ACTIVE);
                                            loginResponse.setFritzBoxActive(fritzBoxActive.getValue());

                                            //Liste der Icons mit senden
                                            List<Icon> icons = ShcApplicationServer.getInstance().getIconEditor().getIconList();
                                            loginResponse.getIcons().addAll(icons);

                                            loginResponse.setSuccess(true);
                                            loginResponse.setSessionId(sessionId);
                                            logger.info("Login erfolgreich");
                                            json = gson.toJson(loginResponse);
                                            logger.fine(json);
                                            return json;
                                        } else {

                                            //Zutritt für dieses Gerät verweigert
                                            loginResponse.setSuccess(false);
                                            loginResponse.setMessage("Zutritt verweigert");
                                            sessionEditor.getChallenges().remove(challange);
                                            logger.warning(loginResponse.getMessage());
                                            json = gson.toJson(loginResponse);
                                            logger.fine(json);
                                            return json;
                                        }
                                    }
                                    loginResponse.setSuccess(false);
                                    loginResponse.setMessage("ungültiges Gerät");
                                    sessionEditor.getChallenges().remove(challange);
                                    logger.warning(loginResponse.getMessage());
                                    json = gson.toJson(loginResponse);
                                    logger.fine(json);
                                    return json;
                                }
                            } catch (NoSuchAlgorithmException e) {

                                //Server Fehler
                                loginResponse.setSuccess(false);
                                loginResponse.setMessage("Server Fehler");
                                sessionEditor.getChallenges().remove(challange);
                                logger.warning(loginResponse.getMessage());
                                json = gson.toJson(loginResponse);
                                logger.fine(json);
                                return json;
                            }
                        }
                    }

                    //Fehler Gerät nicht gefunden
                    loginResponse.setSuccess(false);
                    loginResponse.setMessage("Login ungültig");
                    sessionEditor.getChallenges().remove(challange);
                    logger.warning(loginResponse.getMessage());
                    json = gson.toJson(loginResponse);
                    logger.fine(json);
                    return json;
                } else {

                    //Fehler Unbekannter Benutzer
                    loginResponse.setSuccess(false);
                    loginResponse.setMessage("Unbekannter Benutzer");
                    sessionEditor.getChallenges().remove(challange);
                    logger.warning(loginResponse.getMessage());
                    json = gson.toJson(loginResponse);
                    logger.fine(json);
                    return json;
                }
            }
        }
        //Fehler Unbekannter Benutzer
        loginResponse.setSuccess(false);
        loginResponse.setMessage("ungültige Challange");
        logger.warning(loginResponse.getMessage());
        json = gson.toJson(loginResponse);
        logger.fine(json);
        return json;
    }
}
