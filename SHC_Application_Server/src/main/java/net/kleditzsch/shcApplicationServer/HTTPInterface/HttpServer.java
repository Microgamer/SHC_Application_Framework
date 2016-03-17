package net.kleditzsch.shcApplicationServer.HTTPInterface;

import com.google.gson.Gson;
import fi.iki.elonen.NanoHTTPD;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.*;
import net.kleditzsch.shcApplicationServer.Settings.Settings;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * HTTP Server
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class HttpServer extends NanoHTTPD {

    private static Logger logger = LoggerUtil.getLogger(HttpServer.class);

    public HttpServer() throws IOException {

        //Port
        super(ShcApplicationServer.getInstance().getSettings().getIntegerSetting(Settings.SETTING_SERVER_PORT).getValue());

        //TODO HTTPs support
        //SSL
        //String password = (String) ShcApplicationServer.getInstance().getSettings().getSetting(Settings.SETTING_SERVER_CERTIFICATE_PASSWORD).getValue();
        //makeSecure(NanoHTTPD.makeSSLSocketFactory("/keystore.jks", password.toCharArray()), null);
    }

    @Override
    public Response serve(IHTTPSession session) {

        RequestHandler requestHandler = null;
        logger.info("HTTP Anfrage: " + session.getUri() + "?" + session.getQueryParameterString());
        switch (session.getUri()) {

            case "/handshake":

                requestHandler = new HandshakeRequestHandler();
                break;
            case "/login":

                requestHandler = new LoginRequestHandler();
                break;
            case "/useradmin":

                requestHandler = new UserAdministartionRequestHandler();
                break;
            case "/deviceadmin":

                requestHandler = new DeviceRequestHandler();
                break;
            case "/settings":

                requestHandler = new SettingsRequestHandler();
                break;
            case "/automationdevice":

                requestHandler = new AutomationDeviceHandler();
                break;
            default:

                //Unbekannte Anfrage
                logger.warning("Unbekannte Anfrage");
                return newFixedLengthResponse(Response.Status.BAD_REQUEST, "text/test", "Fehlerhafte Anfrage");
        }

        //Parameter
        Map<String, String> post = new HashMap<>();
        if(session.getMethod().equals(Method.POST)) {

            try {
                session.parseBody(post);
            } catch (IOException ioe) {

                logger.warning("POST Daten Lesen fehlgeschlagen -> " + ioe.getLocalizedMessage());
                return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, MIME_PLAINTEXT, "SERVER INTERNAL ERROR: IOException: " + ioe.getMessage());
            } catch (ResponseException re) {

                logger.warning("POST Daten Lesen fehlgeschlagen -> " + re.getLocalizedMessage());
                return newFixedLengthResponse(re.getStatus(), MIME_PLAINTEXT, re.getMessage());
            }
        }

        //Antwort verarbeiten
        Gson gson = ShcApplicationServer.getInstance().getGson();
        String response = requestHandler.handleRequest(session.getParms(), gson);
        if(response.equals("")) {

            logger.warning("Ungültige Anfrage");
            return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, MIME_PLAINTEXT, "SERVER INTERNAL ERROR: unknown request");
        }
        return newFixedLengthResponse(response);
    }
}
