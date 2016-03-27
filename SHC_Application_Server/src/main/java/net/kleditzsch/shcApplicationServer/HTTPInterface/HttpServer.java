package net.kleditzsch.shcApplicationServer.HTTPInterface;

import com.google.gson.Gson;
import fi.iki.elonen.NanoHTTPD;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Icon.GetHandler;
import net.kleditzsch.shcApplicationServer.Settings.Settings;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
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

        logger.info("HTTP Anfrage: " + session.getUri() + "?" + session.getQueryParameterString());

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
        Map<String, String> params = session.getParms();

        //Anfrage bearbeiten
        if(params.containsKey("action")) {

            //Versuch den Handler zu laden
            String uri = session.getUri().replace("/", "");
            String packageStr = "net.kleditzsch.shcApplicationServer.HTTPInterface.Handler." + uri.substring(0, 1).toUpperCase() + uri.substring(1).toLowerCase() + ".";
            String action = params.get("action");
            action = action.substring(0, 1).toUpperCase() + action.substring(1).toLowerCase();
            packageStr += action + "Handler";

            try {

                Class clazz = Class.forName(packageStr);
                RequestHandler handler = (RequestHandler) clazz.newInstance();

                //Antwort verarbeiten
                Gson gson = ShcApplicationServer.getInstance().getGson();
                String response = handler.handleRequest(params, gson);

                //Dateidownload
                if(handler instanceof GetHandler) {

                    if(!response.equals("")) {

                        try {
                            FileInputStream fis;
                            if (response.startsWith("/")) {

                                //Datei im Dateisystem
                                fis = new FileInputStream(response);
                            } else {

                                //im Jar Archiv
                                fis = new FileInputStream(getClass().getClassLoader().getResource(response).toExternalForm());
                            }

                            if(response.endsWith(".png")) {

                                return newChunkedResponse(Response.Status.OK, "image/png", fis);
                            } else {

                                return newChunkedResponse(Response.Status.OK, "image/jpeg", fis);
                            }
                        } catch(IOException e) {

                            logger.log(Level.SEVERE, "Fehler beim einlesen der Icon Datei", e);
                            return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/text", "Fehlerhafte Anfrage");
                        }
                    } else {

                        //Fehlerhafte Anfrage
                        return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/text", "Fehlerhafte Anfrage");
                    }
                }
                return newFixedLengthResponse(response);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {

                logger.log(Level.SEVERE, "Fehlerhafte Anfrage", e);
            }
        }
        //Fehler Response
        logger.warning("Unbekannte Anfrage");
        return newFixedLengthResponse(Response.Status.BAD_REQUEST, "text/text", "Fehlerhafte Anfrage");
    }
}
