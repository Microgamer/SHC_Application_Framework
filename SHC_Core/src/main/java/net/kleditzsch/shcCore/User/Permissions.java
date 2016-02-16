package net.kleditzsch.shcCore.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Schnittstelle welche die Berechtigungen definiert
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Permissions {

    /**
     * Benutzer Verwalten
     */
    String USER_ADMINISTRATION = "shc.permission.user.administration";

    /**
     * gibt eine Liste mit allen Berechtigungen zurück
     *
     * @return Liste mit Berechtigungen
     */
    static List<String> listPermissions() {

        List<String> permissions = new ArrayList<>();
        permissions.add(USER_ADMINISTRATION);
        return permissions;
    }
}
