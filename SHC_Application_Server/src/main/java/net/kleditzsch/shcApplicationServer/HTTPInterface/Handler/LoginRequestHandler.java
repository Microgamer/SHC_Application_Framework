package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.DeviceManager.ClientDevice;
import net.kleditzsch.shcApplicationServer.HTTPInterface.RequestHandler;
import net.kleditzsch.shcApplicationServer.Session.SessionEditor;
import net.kleditzsch.shcCore.Core.BasicElement;
import net.kleditzsch.shcCore.ClientData.Login.LoginResponse;
import net.kleditzsch.shcCore.User.ChallangeResponseUtil;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * Login Anfrage
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LoginRequestHandler implements RequestHandler {

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        String challangeResponse = params.get("challangeResponse");
        SessionEditor sessionEditor = ShcApplicationServer.getInstance().getSessionEditor();
        if(challangeResponse == null) {

            //Chalange erzeugen
            String challange = BasicElement.createHash().substring(0, 15);
            ShcApplicationServer.getInstance().getSessionEditor().getChallenges().add(challange);
            return challange;
        } else {

            LoginResponse loginResponse = new LoginResponse();

            //Challange Response prüfen
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
                        synchronized (devices) {

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

                                                //Berechtigungen für den benutzer mit senden
                                                for(String permission : Permissions.listPermissions()) {

                                                    if(user.checkPermission(permission)) {

                                                        loginResponse.getPermissions().add(permission);
                                                    }
                                                }

                                                loginResponse.setSuccess(true);
                                                loginResponse.setSessionId(sessionId);
                                                return gson.toJson(loginResponse);
                                            } else {

                                                //Zutritt für dieses Gerät verweigert
                                                loginResponse.setSuccess(false);
                                                loginResponse.setMessage("Zutritt verweigert");
                                                sessionEditor.getChallenges().remove(challange);
                                                return gson.toJson(loginResponse);
                                            }
                                        }
                                        loginResponse.setSuccess(false);
                                        loginResponse.setMessage("ungültiges Gerät");
                                        sessionEditor.getChallenges().remove(challange);
                                        return gson.toJson(loginResponse);
                                    }
                                } catch (NoSuchAlgorithmException e) {

                                    //Server Fehler
                                    loginResponse.setSuccess(false);
                                    loginResponse.setMessage("Server Fehler");
                                    sessionEditor.getChallenges().remove(challange);
                                    return gson.toJson(loginResponse);
                                }
                            }
                        }

                        //Fehler Gerät nicht gefunden
                        loginResponse.setSuccess(false);
                        loginResponse.setMessage("Login ungültig");
                        sessionEditor.getChallenges().remove(challange);
                        return gson.toJson(loginResponse);
                    } else {

                        //Fehler Unbekannter Benutzer
                        loginResponse.setSuccess(false);
                        loginResponse.setMessage("Unbekannter Benutzer");
                        sessionEditor.getChallenges().remove(challange);
                        return gson.toJson(loginResponse);
                    }
                }
            }
            //Fehler Unbekannter Benutzer
            loginResponse.setSuccess(false);
            loginResponse.setMessage("ungültige Challange");
            return gson.toJson(loginResponse);
        }
    }
}
