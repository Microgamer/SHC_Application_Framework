package net.kleditzsch.AVM.FritzBox.SmartHome;

import net.kleditzsch.AVM.FritzBox.SmartHome.Exception.AuthException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

/**
 * Verwaltet die Sessions zur Fritz Box und sendet HTTP Anfragen
 */
public class FritzBoxHandler {

    private String sessionId;

    private LocalDateTime sessionIdTimeout;

    private String fritzBoxAddress = "fritz.box";

    private String username, password;

    /**
     * meldet einen Benutzer nur mit Passwort an der Fritz!Box an
     *
     * @param fritzBoxAddress
     * @param password
     */
    public void login(String fritzBoxAddress, String password) {

        this.login(fritzBoxAddress, "", password);
    }

    /**
     * meldet einen Benutzer mit Benutzername und Passwort an der Fritz!Box an
     *
     * @param fritzBoxAddress
     * @param username
     * @param password
     */
    public void login(String fritzBoxAddress, String username, String password) {

        this.fritzBoxAddress = fritzBoxAddress;
        this.username = username;
        this.password = password;

        String challenge = this.getChallenge();
        if(challenge != null) {

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
     * gibt die Session ID zurück
     * prüft vorher ob diese Abgelaufen ist und erneuert sie falls nötig
     *
     * @return
     */
    private String getSessionId() {

        if(sessionIdTimeout.isAfter(LocalDateTime.now())) {

            this.login(fritzBoxAddress, username, password);
        }
        return this.sessionId;
    }

    /**
     * sendet eine HTTP Anfrage an die Fritz!Box
     *
     * @param urlFragment
     * @return
     * @throws IOException
     */
    public String sendHttpRequest(String urlFragment) throws IOException {

        URL request = new URL("http://" + this.fritzBoxAddress + "/" + urlFragment + "&sid=" + getSessionId());
        this.sessionIdTimeout = LocalDateTime.now().plusSeconds(590);
        return httpGetRequest(request);
    }

    /**
     * fragt die aktuelle Challange ab
     *
     * @return Challenge
     */
    private String getChallenge() {

        try {

            URL url = new URL("http://" + this.fritzBoxAddress + "/login_sid.lua ");
            String response = this.httpGetRequest(url);
            return response.substring(response.indexOf("<Challenge>") + 11, response.indexOf("<Challenge>") + 19);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    /**
     * sendet die Login Anfrage an die Fritz!box
     *
     * @param challenge
     * @param username
     * @param password
     * @return
     */
    private String sendLogin(String challenge, String username, String password) {

        String challengeHash = challenge + "-" + password;
        try {

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

            return response.substring(response.indexOf("<SID>") + 5, response.indexOf("<SID>") + 21);

        } catch (UnsupportedEncodingException e) {

            //UTF-16LE nicht unterstützt
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {

            //MD5 nicht unterstützt
            e.printStackTrace();
        } catch (MalformedURLException e) {

            //Fehlerhafte URL
            e.printStackTrace();
        } catch (IOException e) {

            //Anfrage Fehlgeschlagen
            e.printStackTrace();
        }
        return "0000000000000000";
    }

    /**
     * sendet eine HTTP Anfrage ab
     *
     * @param url
     * @return
     * @throws IOException
     */
    private String httpGetRequest(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Java Tools");
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String line;
        StringBuffer response = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = in.readLine()) != null) {

            response.append(line);
        }
        return response.toString();
    }
}
