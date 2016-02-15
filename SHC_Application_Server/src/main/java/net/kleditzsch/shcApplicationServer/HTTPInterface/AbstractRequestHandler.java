package net.kleditzsch.shcApplicationServer.HTTPInterface;

import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Session.SessionEditor;
import net.kleditzsch.shcCore.User.User;

import java.util.Map;

/**
 * Basis Request Handler
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractRequestHandler implements RequestHandler {

    /**
     * prueft ob die Session ID gültig ist
     *
     * @param params Parameter
     * @return Benutzer der mit der Session verknüpft ist
     */
    protected User checkSession(Map<String, String> params) {

        if(params.containsKey("sid")) {

            SessionEditor.Session session = ShcApplicationServer.getInstance().getSessionEditor().getSessionById(params.get("sid"));
            if(session != null) {

                return ShcApplicationServer.getInstance().getUserEditor().getUserByHash(session.getUser());
            }
        }
        return null;
    }

    /**
     * prüft ob der Benutzer ein bestimmtes Recht beseitzt
     *
     * @param user Benutzer
     * @param permission Berechtigung
     * @return true wenn Recht vorhanden
     */
    protected  boolean checkUserPermission(User user, String permission) {

        if(user != null && user.checkPermission(permission)) {

            return true;
        }
        return false;
    }
}
