package net.kleditzsch.shcCore.ServerConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
     * @param clientHash Client Hash
     * @param userAgent Benutzerkennung
     * @return Handshake Json
     * @throws IOException
     */
    public String sendHandshake(String clientHash, String userAgent) throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("clientHash", clientHash);
        params.put("userAgent", userAgent);
        return sendHttpRequest("handshake", params);
    }

    /**
     * sendet eine Loginanfrage
     *
     * @return Callange
     * @throws IOException
     */
    public String getLoginChallange() throws IOException {

        Map<String, String> params = new HashMap<>();
        return sendHttpRequest("login", params);
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
        return sendHttpRequest("login", params);
    }

    protected String sendHttpRequest(String request, Map<String, String> getParams) throws IOException {

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
        InputStream in = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        //Antwort empfangen
        StringBuffer response = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {

            response.append(line);
        }
        bufferedReader.close();
        return response.toString();
    }
}
