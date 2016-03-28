package net.kleditzsch.shcCore.Room.Interface;

import net.kleditzsch.shcCore.Core.Element;
import net.kleditzsch.shcCore.Room.ViewElements;
import net.kleditzsch.shcCore.User.User;

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
     * @param userGroupHash Benutzergruppe
     */
    void addAllowedUserGroup(String userGroupHash);

    /**
     * entfernt die Benutzergruppe
     *
     * @param userGroupHash Benutzergruppe
     * @return true wenn erfolgreich gelöscht
     */
    boolean removeAllowedUserGroup(String userGroupHash);

    /**
     * entfernt alle Benutzergruppen
     */
    void removeAllAllowedUserGroups();

    /**
     * gibt eine Liste mit allen erlaubten Benutzergruppen zurück
     *
     * @return erlaubte Benutzergruppen
     */
    Set<String> listAllowedUserGroups();

    /**
     * prüft ob der übergebene Benutzer für dieses Element berechtigt ist
     *
     * @param user Benutzer
     * @return true wenn Berechtigt
     */
    boolean isUserEntitled(User user);

    /**
     * gibt an ob das Element sichtbar ist
     *
     * @return Sichtbarkeit
     */
    boolean isVisible();

    /**
     * setzt die Sichtbarkeit des Elements
     *
     * @param visible Sichtbarkeit
     */
    void setVisible(boolean visible);

    /**
     * gibt das Icon zurück
     *
     * @return Icon
     */
    String getIcon();

    /**
     * setzt das Icon
     *
     * @param icon Icon
     */
    void setIcon(String icon);

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    int getType();
}
