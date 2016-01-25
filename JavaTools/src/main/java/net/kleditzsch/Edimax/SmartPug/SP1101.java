package net.kleditzsch.Edimax.SmartPug;

import java.io.*;
import java.net.*;

/**
 * Edimax SmartPlug SP-1101
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SP1101 {

    /**
     * Status an
     */
    public static final int STATE_ON = 1;

    /**
     * Status aus
     */
    public static final int STATE_OFF = 2;

    /**
     * IP Der Steckdose
     */
    private String ip;

    /**
     * Benutzername
     */
    private String user;

    /**
     * Passwort
     */
    private String password;

    /**
     * @param ip IP Adresse
     */
    public SP1101(String ip){

        this.ip = ip;
        this.user = "admin";
        this.password = "1234";
    }

    /**
     * @param ip IP Adresse
     * @param password Passwort
     */
    public SP1101(String ip, String password) {

        this.ip = ip;
        this.user = "admin";
        this.password = password;
    }

    /**
     * @param ip IP Adresse
     * @param user Benutzername
     * @param password Passwort
     */
    public SP1101(String ip, String user, String password) {

        this.ip = ip;
        this.user = user;
        this.password = password;
    }

    /**
     * gibt die IP Adresse zurück
     *
     * @return IP Adresse
     */
    public String getIp() {

        return ip;
    }

    /**
     * setzt die IP Adresse
     *
     * @param ip IP Adresse
     */
    public void setIp(String ip) {

        this.ip = ip;
    }

    /**
     * gibt den Benutzernamen zurück
     *
     * @return Benutzernamen
     */
    public String getUser() {

        return user;
    }

    /**
     * setzt den Benutzernamen
     *
     * @param user Benutzernamen
     */
    public void setUser(String user) {

        this.user = user;
    }

    /**
     * gibt das Passwort zurück
     *
     * @return Passwort
     */
    public String getPassword() {

        return password;
    }

    /**
     * setzt das Passwort
     *
     * @param password Passwort
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * prüft ob die Steckdose erreichbar ist
     *
     * @return true wenn erreichbar
     */
    public boolean isPresent() {

        try {

            InetAddress ip = InetAddress.getByName(getIp());
            if(ip.isReachable(1000)) {

                return true;
            }
            return false;
        } catch (IOException ex) {}
        return false;
    }

    /**
     * sendet einen Einschaltbefehl
     *
     * @return true bei Erfolg
     */
    public boolean switchOn() throws IOException {

        String request = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "                        <SMARTPLUG id=\"edimax\">\n" +
                "                            <CMD id=\"setup\">\n" +
                "                                <Device.System.Power.State>ON</Device.System.Power.State>\n" +
                "                            </CMD>\n" +
                "                        </SMARTPLUG>";

        String response = sendHttpCommand(request);
        if(response.contains("<CMD id=\"setup\">OK</CMD>")) {

            return true;
        }
        return false;
    }

    /**
     * sendet einen Ausschaltbefehl
     *
     * @return true bei Erfolg
     */
    public boolean switchOff() throws IOException {

        String request = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "                        <SMARTPLUG id=\"edimax\">\n" +
                "                            <CMD id=\"setup\">\n" +
                "                                <Device.System.Power.State>OFF</Device.System.Power.State>\n" +
                "                            </CMD>\n" +
                "                        </SMARTPLUG>";

        String response = sendHttpCommand(request);
        if(response.contains("<CMD id=\"setup\">OK</CMD>")) {

            return true;
        }
        return false;
    }

    /**
     * fragt den aktuellen Status der Steckodse ab
     *
     * @return STATE_ON oder STATE_OFF
     */
    public int readState() throws IOException {

        String request = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "                        <SMARTPLUG id=\"edimax\">\n" +
                "                            <CMD id=\"get\">\n" +
                "                                <Device.System.Power.State></Device.System.Power.State>\n" +
                "                            </CMD>\n" +
                "                        </SMARTPLUG>";

        String response = sendHttpCommand(request);
        if(response.contains("<Device.System.Power.State>ON</Device.System.Power.State>")) {

            return STATE_ON;
        }
        return STATE_OFF;
    }

    /**
     * sendet einen Befehl per HTTP an die Steckdose
     *
     * @param content XML ANfrage
     * @return XML Antwort
     */
    private String sendHttpCommand(String content) throws IOException {

        final String user = getUser();
        final String password = getPassword();

        //Authentifizierung
        Authenticator.setDefault(new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password.toCharArray());
            }
        });

        URL url = new URL("http://" + getIp() + ":10000/smartplug.cgi");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //Anfrage vorbereiten
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Java Tools");
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(content.toCharArray().length));
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setConnectTimeout(1000);
        connection.setReadTimeout(3000);

        //Daten senden
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(content);
        wr.flush ();
        wr.close ();

        //Antwort auslesen
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();
        while((line = rd.readLine()) != null) {
            response.append(line);
        }
        rd.close();
        return response.toString();
    }
}
