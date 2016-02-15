package net.kleditzsch.shcApplicationServer.HTTPInterface;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Map;

/**
 * Anfrage Handler
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface RequestHandler {

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson Gson Objekt
     * @return Json Antwort
     */
    String handleRequest(Map<String, String> params, Gson gson);
}
