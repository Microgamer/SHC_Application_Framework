package net.kleditzsch.shcApplicationServer.HTTPInterface;

import com.google.gson.Gson;
import fi.iki.elonen.NanoHTTPD;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.HandshakeRequest;
import net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.LoginRequest;
import net.kleditzsch.shcApplicationServer.Settings.Settings;

import java.io.IOException;

/**
 * HTTP Server
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class HttpServer extends NanoHTTPD {

    public HttpServer() throws IOException {

        //Port
        super(Double.valueOf((double) ShcApplicationServer.getInstance().getSettings().getSetting(Settings.SETTING_SERVER_PORT).getValue()).intValue());

        //TODO HTTPs support
        //SSL
        //String password = (String) ShcApplicationServer.getInstance().getSettings().getSetting(Settings.SETTING_SERVER_CERTIFICATE_PASSWORD).getValue();
        //makeSecure(NanoHTTPD.makeSSLSocketFactory("/keystore.jks", password.toCharArray()), null);
    }

    @Override
    public Response serve(IHTTPSession session) {

        RequestHandler requestHandler = null;
        switch (session.getUri()) {

            case "/handshake":

                requestHandler = new HandshakeRequest();
                break;
            case "/login":

                requestHandler = new LoginRequest();
                break;
            default:

                //Unbekannte Anfrage
                return newFixedLengthResponse(Response.Status.BAD_REQUEST, "text/test", "Fehlerhafte Anfrage");
        }
        Gson gson = ShcApplicationServer.getInstance().getGson();
        String response = requestHandler.handleRequest(session.getParms(), gson);
        return newFixedLengthResponse(response);
    }
}
