package net.kleditzsch.Edimax.SmartPug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Edimax SmartPlug SP-1101
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

    public SP1101(String ip){

        this.ip = ip;
        this.user = "admin";
        this.password = "1234";
    }

    public SP1101(String ip, String password) {

        this.ip = ip;
        this.user = "admin";
        this.password = password;
    }

    public SP1101(String ip, String user, String password) {

        this.ip = ip;
        this.user = user;
        this.password = password;
    }

    public String getIp() {

        return ip;
    }

    public void setIp(String ip) {

        this.ip = ip;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user) {

        this.user = user;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * prüft ob die Steckdose erreichbar ist
     *
     * @return
     */
    public boolean isPresent() {

        return false;
    }

    /**
     * sendet einen Einschaltbefehl
     */
    public void switchOn() {

        String request = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "                        <SMARTPLUG id=\"edimax\">\n" +
                "                            <CMD id=\"setup\">\n" +
                "                                <Device.System.Power.State>ON</Device.System.Power.State>\n" +
                "                            </CMD>\n" +
                "                        </SMARTPLUG>";

        try {
            String response = sendHttpCommand(request);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sendet einen Ausschaltbefehl
     */
    public void switchOff() {


    }

    /**
     * fragt den aktuellen Status der Steckodse ab
     */
    public boolean readState() {

        return false;
    }

    /**
     * sendet einen Befehl per HTTP an die Steckdose
     *
     * @param content
     * @return
     */
    private String sendHttpCommand(String content) throws IOException {

        URL url = new URL("http://" + getUser() + ":" + getPassword() + "@" + getIp() + ":10000/smartplug.cgi");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //Anfrage vorbereiten
        connection.setDoOutput(true);
        connection.setRequestProperty("User-Agent", "Java Tools");
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setReadTimeout(500);

        //Daten senden
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(content.getBytes());
        //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        //bufferedWriter.write(content);

        //Antwort auslesen
        String line;
        StringBuffer response = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = in.readLine()) != null) {

            response.append(line);
        }
        return response.toString();
    }
}
