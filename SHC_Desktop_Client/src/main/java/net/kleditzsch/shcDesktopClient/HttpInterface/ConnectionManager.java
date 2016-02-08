package net.kleditzsch.shcDesktopClient.HttpInterface;

import com.google.gson.Gson;
import net.kleditzsch.shcCore.ServerConnection.HttpInterfaceData.Handshake;
import net.kleditzsch.shcCore.ServerConnection.HttpInterfaceData.LoginResponse;
import net.kleditzsch.shcCore.ServerConnection.HttpRequestUtil;
import net.kleditzsch.shcCore.User.ChallangeResponseUtil;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.Data.Settings.Settings;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

/**
 * Verbindungsmanager
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ConnectionManager {

    /**
     * Hilfsklasse für HTTP ANfragen an den Server
     */
    protected HttpRequestUtil requestUtil;

    /**
     * Session ID
     */
    protected String sessionId = "";

    /**
     * letzter Kontakt zum Server
     */
    protected LocalDateTime lastContact;

    /**
     * @param address Server Adresse
     * @param port Server Port
     */
    public ConnectionManager(String address, int port) {

        requestUtil = new HttpRequestUtil(address, port);
    }

    /**
     * gibt an ob die Verbindung zum Server aktiv ist
     *
     * @return true wenn aktiv
     */
    public boolean isConnected() {

        if(sessionId != "" && lastContact != null && lastContact.isAfter(LocalDateTime.now().minusMinutes(9))) {

            return true;
        }
        return false;
    }

    /**
     * sendet eine Verbindungsanfrage an den Server
     *
     * @param clientHash Client Identifizierung
     * @param userAgent Clientkennung
     * @return true bei erfolg
     */
    public Handshake sendHandshake(String clientHash, String userAgent) throws IOException {

        String response = requestUtil.sendHandshake(clientHash, userAgent);
        Gson gson = ShcDesktopClient.getInstance().getGson();
        return gson.fromJson(response, Handshake.class);
    }

    /**
     * foredert die Challange an
     *
     * @return Challange
     * @throws IOException
     */
    public String getLoginChallange() throws IOException {

        return requestUtil.getLoginChallange();
    }

    /**
     * sendet die ChallangeResponse
     *
     * @param challangeResponse ChallangeResponse
     * @return Login Antwort
     * @throws IOException
     */
    public LoginResponse sendLogin(String challangeResponse) throws IOException {

        String response = requestUtil.sendLoginRequest(challangeResponse);
        Gson gson = ShcDesktopClient.getInstance().getGson();
        return gson.fromJson(response, LoginResponse.class);
    }

    /**
     * prüft ob ein Autologin möglich ist und führt ihn aus
     *
     * @return true ber Erfolg
     */
    public boolean autoLogin() {

        Settings settings = ShcDesktopClient.getInstance().getSettings();
        String serverAddress = (String) settings.getSetting(Settings.SETTING_SERVER_ADDRESS).getValue();
        int serverPort = Double.valueOf((double) settings.getSetting(Settings.SETTING_SERVER_PORT).getValue()).intValue();
        String clientHash = (String) settings.getSetting(Settings.SETTING_SERVER_CLIENT_HASH).getValue();
        String userName = (String) settings.getSetting(Settings.SETTING_SERVER_USER).getValue();
        String userHash = (String) settings.getSetting(Settings.SETTING_SERVER_IDENTIFIER).getValue();

        if(serverAddress != "" && clientHash != "" && userName != "" && userHash != "") {

            try {

                String challange = this.getLoginChallange();
                String challengeResponse = ChallangeResponseUtil.computeChallangeResponse(challange, userName, userHash, clientHash);
                LoginResponse loginResponse = this.sendLogin(challengeResponse);
                if(loginResponse.isSuccess() == true) {

                    this.setSessionId(loginResponse.getSessionId());
                    this.updateLastContact();
                    return true;
                }
            } catch (IOException | NoSuchAlgorithmException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * gibt die Session ID zurück
     *
     * @return Session ID
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * setzt die Session ID
     *
     * @param sessionId Session ID
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * aktualisiert die Zeit der letzten Verbindung zum Server
     */
    public void updateLastContact() {

        this.lastContact = LocalDateTime.now();
    }

    /**
     * trennt die Verbindung
     */
    public void disconnect() {

        sessionId = "";
        lastContact = null;
    }
}
