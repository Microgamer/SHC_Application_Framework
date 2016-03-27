package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Icon;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.Icon.IconEditor;
import net.kleditzsch.shcCore.Icon.Icon;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Icon Downloader
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class GetHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(GetHandler.class);

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson   Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        logger.info("Icons Download Anfrage");
        if(params.containsKey("name") && params.containsKey("size")) {

            try {

                String name = params.get("name").toLowerCase();
                int size = Integer.parseInt(params.get("size"));

                IconEditor iconEditor = ShcApplicationServer.getInstance().getIconEditor();
                for(Icon icon : iconEditor.getIconList()) {

                    if(icon.getName().equals(name)) {

                        //Icon gefunden
                        if(icon.getAvailableSize().contains(size)) {

                            String requestedFile;
                            if(icon.isJarFile()) {

                                //im Jar Archiv
                                requestedFile = icon.getPath().toString() + "/" + size + ".png";
                            } else {

                                //im Dateisystem
                                requestedFile = icon.getPath().toAbsolutePath().toString() + "/" + size + ".png";
                            }
                            logger.fine("Icon Datei " + requestedFile + " gefunden");
                            return requestedFile;
                        } else {

                            logger.warning("Angeforderte Icon Größe existiert nicht");
                            return "";
                        }
                    }
                }
                //Icon nicht gefunden
                logger.warning("Angefordertes Icon existiert nicht");
                return "";
            } catch (NumberFormatException e) {

                logger.warning("Parameter \"size\" ungültig");
                return "";
            }
        }
        //Fehler fehlende Parameter
        logger.warning("Parameter \"name\" oder \"size\" fehlt");
        return "";
    }
}
