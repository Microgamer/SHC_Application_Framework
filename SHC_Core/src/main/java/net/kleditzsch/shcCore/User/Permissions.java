package net.kleditzsch.shcCore.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Schnittstelle welche die Berechtigungen definiert
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Permissions {

    //Benutzer und Gruppen verwalten
    String USER_ADMINISTRATION = "shc.permission.user.administration";

    //Geräte verwalten
    String DEVICE_ADMINISTRATION = "shc.permission.device.administration";

    //Einstellungen verwalten
    String SETTINGS_ADMINISTRATION = "shc.permission.settings.administration";

    //Elements verwalten
    String ELEMENT_ADMINISTRATION = "shc.permission.elements.administration";

    //Räume verwalten
    String ROOM_ADMINISTRATION = "shc.permission.room.administration";

    /**
     * gibt eine Liste mit allen Berechtigungen zurück
     *
     * @return Liste mit Berechtigungen
     */
    static Set<String> listPermissions() {

        Set<String> permissions = new HashSet<>();
        permissions.add(USER_ADMINISTRATION);
        permissions.add(DEVICE_ADMINISTRATION);
        permissions.add(SETTINGS_ADMINISTRATION);
        permissions.add(ELEMENT_ADMINISTRATION);
        permissions.add(ROOM_ADMINISTRATION);
        return permissions;
    }
}
