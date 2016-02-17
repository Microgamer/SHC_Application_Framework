package net.kleditzsch.shcDesktopClient.HttpInterface;

import com.google.gson.Gson;
import net.kleditzsch.shcCore.ClientData.Device.DeviceData;
import net.kleditzsch.shcCore.ClientData.Device.DeviceResponse;
import net.kleditzsch.shcCore.ClientData.Login.Handshake;
import net.kleditzsch.shcCore.ClientData.Login.LoginResponse;
import net.kleditzsch.shcCore.ClientData.HttpRequestUtil;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.ClientData.User.UserAdministrationResponse;
import net.kleditzsch.shcCore.ClientData.User.UserData;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;
import net.kleditzsch.shcCore.User.ChallangeResponseUtil;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.Data.Settings.Settings;
import net.kleditzsch.shcDesktopClient.View.MainViewLoader;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Objects;

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

    protected Gson gson;

    /**
     * @param address Server Adresse
     * @param port Server Port
     */
    public ConnectionManager(String address, int port) {

        requestUtil = new HttpRequestUtil(address, port);
        gson = ShcDesktopClient.getInstance().getGson();
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
     * @param clientHash ClientData Identifizierung
     * @param userAgent Clientkennung
     * @return true bei erfolg
     */
    public Handshake sendHandshake(String clientHash, String userAgent) throws IOException {

        String response = requestUtil.sendHandshake(clientHash, userAgent);
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
        return gson.fromJson(response, LoginResponse.class);
    }

    /**
     * prüft ob ein Autologin möglich ist und führt ihn aus
     *
     * @return true ber Erfolg
     */
    public boolean autoLogin() {

        Settings settings = ShcDesktopClient.getInstance().getSettings();
        String serverAddress = settings.getStringSetting(Settings.SETTING_SERVER_ADDRESS).getValue();
        String clientHash = settings.getStringSetting(Settings.SETTING_SERVER_CLIENT_HASH).getValue();
        String userName = settings.getStringSetting(Settings.SETTING_SERVER_USER).getValue();
        String userHash = settings.getStringSetting(Settings.SETTING_SERVER_IDENTIFIER).getValue();

        if(!Objects.equals(serverAddress, "") && !Objects.equals(clientHash, "") && !Objects.equals(userName, "") && !Objects.equals(userHash, "")) {

            try {

                String challange = this.getLoginChallange();
                String challengeResponse = ChallangeResponseUtil.computeChallangeResponse(challange, userName, userHash, clientHash);
                LoginResponse loginResponse = this.sendLogin(challengeResponse);
                if(loginResponse.isSuccess()) {

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
     * holt die Benutzer und Benutzergruppen vom Server
     *
     * @return UserAdminstrationResponse
     * @throws IOException
     */
    public UserAdministrationResponse getUsersAndGroups() throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.getUsersAndGroups(this.sessionId);
            updateLastContact();
            return gson.fromJson(response, UserAdministrationResponse.class);
        }
        return null;
    }

    /**
     * sendet eine Anfrage zum erstellen eines Benutzers an den Server
     *
     * @param userData Benutzerdaten
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public SuccessResponse addUser(UserData userData) throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.addUser(gson.toJson(userData), this.sessionId);
            updateLastContact();
            return gson.fromJson(response, SuccessResponse.class);
        }
        return null;
    }

    /**
     * sendet eine Anfrage zum bearbeiten eines Benutzers an den Server
     *
     * @param userData Benutzerdaten
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public SuccessResponse editUser(UserData userData) throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.editUser(gson.toJson(userData), this.sessionId);
            updateLastContact();
            return gson.fromJson(response, SuccessResponse.class);
        }
        return null;
    }

    /**
     * sendet eine Anfrage zum Benutzer löschen an den Server
     *
     * @param userData Benutzerdaten
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public SuccessResponse deleteUser(UserData userData) throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.deleteUser(userData, this.sessionId);
            updateLastContact();
            return gson.fromJson(response, SuccessResponse.class);
        }
        return null;
    }

    /**
     * sendet eine Anfrage zum erstellen einer Benutzergruppe an den Server
     *
     * @param userGroupData Benutzergruppe Daten
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public SuccessResponse addUserGroup(UserGroupData userGroupData) throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.addUserGroup(gson.toJson(userGroupData), this.sessionId);
            updateLastContact();
            return gson.fromJson(response, SuccessResponse.class);
        }
        return null;
    }

    /**
     * sendet eine Anfrage zum bearbeiten einer Benutzergruppe an den Server
     *
     * @param userGroupData Benutzergruppe Daten
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public SuccessResponse editUserGroup(UserGroupData userGroupData) throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.editUserGroup(gson.toJson(userGroupData), this.sessionId);
            updateLastContact();
            return gson.fromJson(response, SuccessResponse.class);
        }
        return null;
    }

    /**
     * sendet eine Anfrage zum löschen eines Benutzers an den Server
     *
     * @param userGroupData Benutzergruppe Daten
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public SuccessResponse deleteUserGroup(UserGroupData userGroupData) throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.deleteUserGroup(userGroupData, this.sessionId);
            updateLastContact();
            return gson.fromJson(response, SuccessResponse.class);
        }
        return null;
    }

    /**
     * sendet eine Anfrage zum auflisten der Geräte an den Server
     *
     * @return Geräteliste
     * @throws IOException
     */
    public DeviceResponse getDevices() throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.getDevices(this.sessionId);
            updateLastContact();
            return gson.fromJson(response, DeviceResponse.class);
        }
        return null;
    }

    /**
     * sendet eine Anfrage zum auflisten der Geräte an den Server
     *
     * @param  deviceData Gerätedaten
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public SuccessResponse allowDevice(DeviceData deviceData) throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.allowDevice(deviceData, this.sessionId);
            updateLastContact();
            return gson.fromJson(response, SuccessResponse.class);
        }
        return null;
    }

    /**
     * sendet eine Anfrage zum verweigern der Geräte an den Server
     *
     * @param  deviceData Gerätedaten
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public SuccessResponse denyDevice(DeviceData deviceData) throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.denyDevice(deviceData, this.sessionId);
            updateLastContact();
            return gson.fromJson(response, SuccessResponse.class);
        }
        return null;
    }

    /**
     * sendet eine Anfrage zum löschen der Geräte an den Server
     *
     * @param  deviceData Gerätedaten
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public SuccessResponse deleteDevice(DeviceData deviceData) throws IOException {

        if(!Objects.equals(this.sessionId, "")) {

            String response = this.requestUtil.deleteDevice(deviceData, this.sessionId);
            updateLastContact();
            return gson.fromJson(response, SuccessResponse.class);
        }
        return null;
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
     * setzt die Session ID auf den Status ungültig
     */
    public void setSessionidInvalid() {

        sessionId = "";
        lastContact = null;
        //TODO in Überwachungsthread auslagern
        MainViewLoader.loadLoginView();
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
