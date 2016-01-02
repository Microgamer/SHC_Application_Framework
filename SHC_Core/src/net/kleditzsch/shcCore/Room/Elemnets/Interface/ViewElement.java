package net.kleditzsch.shcCore.Room.Elemnets.Interface;

import net.kleditzsch.shcCore.Core.Element;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;

import java.util.Set;

/**
 * Anzeige Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface ViewElement extends Element, ViewElements {

    /**
     * fügt eine Benutzergruppe als erlaubte Benutzergruppe hinzu
     *
     * @param userGroup Benutzergruppe
     */
    void addAllowedUserGroup(UserGroup userGroup);

    /**
     * entfernt die Benutzergruppe
     *
     * @param userGroup Benutzergruppe
     * @return true wenn erfolgreich gelöscht
     */
    boolean removeAllowedUserGroup(UserGroup userGroup);

    /**
     * entfernt alle Benutzergruppen
     */
    void removeAllAllowedUserGroups();

    /**
     * gibt eine Liste mit allen erlaubten Benutzergruppen zurück
     *
     * @return erlaubte Benutzergruppen
     */
    Set<UserGroup> listAllowedUserGroups();

    /**
     * prüft ob der übergebene Benutzer für dieses Element berechtigt ist
     *
     * @param user Benutzer
     * @return true wenn Berechtigt
     */
    boolean isUserEntitled(User user );

    /**
     * gibt an ob der Raum aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    boolean isEnabled();

    /**
     * aktiviert/deaktiviert den Raum
     *
     * @param enabled aktiviert/deaktiviert
     */
    void setEnabled(boolean enabled);

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    int getType();
}
