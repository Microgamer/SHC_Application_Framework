package net.kleditzsch.AVM.FritzBox.SmartHome;

import net.kleditzsch.AVM.FritzBox.SmartHome.Exception.AuthException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

/**
 * Fritz Box Session Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class FritzBoxHandler {

    /**
     * Sesion ID
     */
    private String sessionId;

    /**
     * Session Timeout
     */
    private LocalDateTime sessionIdTimeout;

    /**
     * Fritz!Box Adresse
     */
    private String fritzBoxAddress = "fritz.box";

    /**
     * Fritz!Box Benutzerdaten
     */
    private String username, password;

    /**
     * meldet einen Benutzer nur mit Passwort an der Fritz!Box an
     *
     * @param fritzBoxAddress Fritz!Box Adresse
     * @param password Fritz!Box Passwort
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void login(String fritzBoxAddress, String password) throws IOException, NoSuchAlgorithmException {

        this.login(fritzBoxAddress, "", password);
    }

    /**
     * meldet einen Benutzer mit Benutzername und Passwort an der Fritz!Box an
     *
     * @param fritzBoxAddress Fritz!Box Adresse
     * @param username Fritz!Box Benutzername
     * @param password Fritz!Box Passwort
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void login(String fritzBoxAddress, String username, String password) throws IOException, NoSuchAlgorithmException, AuthException {

        this.fritzBoxAddress = fritzBoxAddress;
        this.username = username;
        this.password = password;

        String challenge = this.getChallenge();
        if(!challenge.equals("")) {

            String sid = sendLogin(challenge, username, password);
            if(!sid.equals("0000000000000000")) {

                this.sessionId = sid;
                this.sessionIdTimeout = LocalDateTime.now().plusSeconds(590);
            } else {

                //Benutzername oder Passwort falsch
                throw new AuthException("Benutzername oder Passwort falsch!");
            }
        } else {

            //Fehlerhafte Fritz!Box Adresse
            throw new AuthException("Fritz!Box Adresse falsch");
        }
    }

    /**
     * sendet eine HTTP Anfrage an die Fritz!Box
     *
     * @param urlFragment URL Fragment
     * @return Response
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String sendHttpRequest(String urlFragment) throws IOException, NoSuchAlgorithmException {

        URL request = new URL("http://" + this.fritzBoxAddress + "/" + urlFragment + "&sid=" + getSessionId());
        this.sessionIdTimeout = LocalDateTime.now().plusSeconds(590);
        return httpGetRequest(request);
    }

    /**
     * gibt die Session ID zurück
     * prüft vorher ob diese Abgelaufen ist und erneuert sie falls nötig
     *
     * @return Session ID
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected String getSessionId() throws IOException, NoSuchAlgorithmException {

        if(sessionIdTimeout.isAfter(LocalDateTime.now())) {

            this.login(fritzBoxAddress, username, password);
        }
        return this.sessionId;
    }

    /**
     * fragt die aktuelle Challange ab
     *
     * @return Challenge
     * @throws IOException
     */
    protected String getChallenge() throws IOException {

        URL url = new URL("http://" + this.fritzBoxAddress + "/login_sid.lua ");
        String response = this.httpGetRequest(url);
        if(response.length() > 30) {

            return response.substring(response.indexOf("<Challenge>") + 11, response.indexOf("<Challenge>") + 19);
        }
        return "";
    }

    /**
     * sendet die Login Anfrage an die Fritz!box
     *
     * @param challenge Challange
     * @param username Fritz!Box Benutzername
     * @param password Fritz!Box Passwort
     * @return Session ID
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected String sendLogin(String challenge, String username, String password) throws IOException, NoSuchAlgorithmException {

        String challengeHash = challenge + "-" + password;
        String challengeHashUtf16 = new String(challengeHash.getBytes("UTF-16LE"), "UTF-8");

        //MD5 Hashen
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(challengeHashUtf16.getBytes());

        final byte byteData[] = digest.digest();
        final BigInteger bigInt = new BigInteger(1, byteData);
        String md5Hash = bigInt.toString(16);
        String challengeResponse = challenge + "-" + md5Hash;

        //HTTP ANfrage schicken
        URL url = new URL("http://" + this.fritzBoxAddress + "/login_sid.lua?user=" + username + "&response=" + challengeResponse);
        String response = this.httpGetRequest(url);

        if(response.length() > 30) {

            return response.substring(response.indexOf("<SID>") + 5, response.indexOf("<SID>") + 21);
        }
        return "0000000000000000";
    }

    /**
     * sendet eine HTTP Anfrage ab
     *
     * @param url URL
     * @return Response
     * @throws IOException
     */
    protected String httpGetRequest(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Java Tools");
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String line;
        StringBuilder response = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = in.readLine()) != null) {

            response.append(line);
        }
        return response.toString();
    }
}
