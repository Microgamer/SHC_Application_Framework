package net.kleditzsch.shcCore.ClientData;

import net.kleditzsch.shcCore.ClientData.Device.DeviceData;
import net.kleditzsch.shcCore.ClientData.User.UserData;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Hilfklasse für HTTP Anfragen an den Server
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class HttpRequestUtil {

    /**
     * Server Adresse
     */
    protected String server;

    /**
     * Server Port
     */
    protected int port;

    /**
     * @param server Server Adresse
     * @param port Server Port
     */
    public HttpRequestUtil(String server, int port) {
        this.server = server;
        this.port = port;
    }

    /**
     * Handshake Anfrage senden
     *
     * @param clientHash ClientData Hash
     * @param userAgent Benutzerkennung
     * @return Handshake Json
     * @throws IOException
     */
    public String sendHandshake(String clientHash, String userAgent) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("clientHash", clientHash);
        params.put("userAgent", userAgent);
        return sendHttpRequest("handshake", params, null);
    }

    /**
     * sendet eine Loginanfrage
     *
     * @return Callange
     * @throws IOException
     */
    public String getLoginChallange() throws IOException {

        Map<String, String> params = new HashMap<>();
        return sendHttpRequest("login", params, null);
    }

    /**
     * sendet eine Loginanfrage
     *
     * @param challangeResponse ChallangeResponse
     * @return LoginResonse Json
     * @throws IOException
     */
    public String sendLoginRequest(String challangeResponse) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("challangeResponse", challangeResponse);
        return sendHttpRequest("login", params, null);
    }

    /**
     * holt die Benutzer und Gruppen von Server
     *
     * @param sid Sessnion ID
     * @return UserAdminstrationResponse
     */
    public String getUsersAndGroups(String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "listusers");
        return sendHttpRequest("useradmin", params, null);
    }

    /**
     * sendet eine Anfrage zum erstellen eines Benutzers an den Server
     *
     * @param userData Benutzerdaten
     * @param sid Sessnion ID
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public String addUser(String userData, String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "adduser");
        Map<String, String> postParams = new HashMap<>();
        postParams.put("data", userData);
        return sendHttpRequest("useradmin", params, postParams);
    }

    /**
     * sendet eine Anfrage zum bearbeiten eines Benutzers an den Server
     *
     * @param userData Benutzerdaten
     * @param sid Sessnion ID
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public String editUser(String userData, String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "edituser");
        Map<String, String> postParams = new HashMap<>();
        postParams.put("data", userData);
        return sendHttpRequest("useradmin", params, postParams);
    }

    /**
     * sendet eine Anfrage zum Benutzer löschen an den Server
     *
     * @param userData Benutzerdaten
     * @param sid Sessnion ID
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public String deleteUser(UserData userData, String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "deleteuser");
        params.put("hash", userData.getHash());
        return sendHttpRequest("useradmin", params, null);
    }

    /**
     * sendet eine Anfrage zum erstellen einer Benutzergruppe an den Server
     *
     * @param userGroupData Benutzergruppe Daten
     * @param sid Sessnion Id
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public String addUserGroup(String userGroupData, String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "addusergroup");
        Map<String, String> postParams = new HashMap<>();
        postParams.put("data", userGroupData);
        return sendHttpRequest("useradmin", params, postParams);
    }

    /**
     * sendet eine Anfrage zum bearbeiten einer Benutzergruppe an den Server
     *
     * @param userGroupData Benutzergruppe Daten
     * @param sid Sessnion Id
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public String editUserGroup(String userGroupData, String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "editeusergroup");
        Map<String, String> postParams = new HashMap<>();
        postParams.put("data", userGroupData);
        return sendHttpRequest("useradmin", params, postParams);
    }

    /**
     * sendet eine Anfrage zum löschen einer Benutzergruppe an den Server
     *
     * @param userGroupData Benutzergruppe Daten
     * @param sid Sessnion Id
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public String deleteUserGroup(UserGroupData userGroupData, String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "deleteusergroup");
        params.put("hash", userGroupData.getHash());
        return sendHttpRequest("useradmin", params, null);
    }

    /**
     * sendet eine Anfrage zum auflisten der Geräte an den Server
     *
     * @param sid Session ID
     * @return Geräteliste
     * @throws IOException
     */
    public String getDevices(String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "listdevices");
        return sendHttpRequest("deviceadmin", params, null);
    }

    /**
     * sendet eine Anfrage zum erlauben eines Gerätes an den Server
     *
     * @param deviceData Gerätedaten
     * @param sid Session ID
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public String allowDevice(DeviceData deviceData, String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "setdeviceallowed");
        params.put("hash", deviceData.getClientHash());
        return sendHttpRequest("deviceadmin", params, null);
    }

    /**
     * sendet eine Anfrage zum verweigern eines Gerätes an den Server
     *
     * @param deviceData Gerätedaten
     * @param sid Session ID
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public String denyDevice(DeviceData deviceData, String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "setdevicedenied");
        params.put("hash", deviceData.getClientHash());
        return sendHttpRequest("deviceadmin", params, null);
    }

    /**
     * sendet eine Anfrage zum löschen eines Gerätes an den Server
     *
     * @param deviceData Gerätedaten
     * @param sid Session ID
     * @return Erfolgsrückmeldung
     * @throws IOException
     */
    public String deleteDevice(DeviceData deviceData, String sid) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("action", "deletedevice");
        params.put("hash", deviceData.getClientHash());
        return sendHttpRequest("deviceadmin", params, null);
    }

    protected String sendHttpRequest(String request, Map<String, String> getParams, Map<String, String> postParams) throws IOException {

        /*
        // Jedes Zertifikat akzeptieren >>>
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };
        // Install the all-trusting trust manager
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        //<<<


        //HTTPs Anfrage
        URL url = new URL("https://" + server + ":" + port + "/handshake");
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        InputStream in = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        */

        //Vorbereiten
        String urlString = "http://" + server + ":" + port + "/" + request + "?";
        boolean first = true;
        for(String key : getParams.keySet()) {

            String value = getParams.get(key);
            urlString += (!first ? "&" : "") + URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
            first = false;
        }

        //TODO HTTPs Support
        //HTTP Anfrage
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(1000);
        urlConnection.setReadTimeout(3000);

        //POST Daten senden
        if(postParams != null) {

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : postParams.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("charset", "utf-8");
            urlConnection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(postDataBytes);
        }

        //Antwort empfangen
        InputStream in = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        StringBuffer response = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {

            response.append(line);
        }
        bufferedReader.close();
        return response.toString();
    }
}
