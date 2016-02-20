package net.kleditzsch.shcCore.Room.Abstract;

import net.kleditzsch.shcCore.Core.BasicElement;
import net.kleditzsch.shcCore.Room.Interface.ViewElement;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;

import java.util.HashSet;
import java.util.Set;

/**
 * Anzeige Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractViewElement extends BasicElement implements ViewElement {

    /**
     * erlaubte Benutzer
     */
    protected final Set<String> allowedUserGroups = new HashSet<>();

    /**
     * Sichtbarkeit
     */
    protected boolean visible = true;

    /**
     * Icon
     */
    protected String icon = "";

    /**
     * fügt eine Benutzergruppe als erlaubte Benutzergruppe hinzu
     *
     * @param userGroupHash Benutzergruppe
     */
    @Override
    public void addAllowedUserGroup(String userGroupHash) {

        allowedUserGroups.add(userGroupHash);
    }

    /**
     * entfernt die Benutzergruppe
     *
     * @param userGroupHash Benutzergruppe
     * @return true wenn erfolgreich gelöscht
     */
    @Override
    public boolean removeAllowedUserGroup(String userGroupHash) {

        if(allowedUserGroups.contains(userGroupHash)) {

            allowedUserGroups.remove(userGroupHash);
            return true;
        }
        return false;
    }

    /**
     * entfernt alle Benutzergruppen
     */
    @Override
    public void removeAllAllowedUserGroups() {

        allowedUserGroups.clear();
    }

    /**
     * gibt eine Liste mit allen erlaubten Benutzergruppen zurück
     *
     * @return erlaubte Benutzergruppen
     */
    @Override
    public Set<String> listAllowedUserGroups() {
        return allowedUserGroups;
    }

    /**
     * prüft ob der übergebene Benutzer für dieses Element berechtigt ist
     *
     * @param user Benutzer
     * @return true wenn Berechtigt
     */
    @Override
    public boolean isUserEntitled(User user) {

        Set<UserGroup> userGroups = user.getUserGroups();

        for(UserGroup userGroup : userGroups) {

            if(allowedUserGroups.contains(userGroup.getHash())) {

                return true;
            }
        }
        return false;
    }

    /**
     * gibt an ob das Element sichtbar ist
     *
     * @return Sichtbarkeit
     */
    @Override
    public boolean isVisible() {
        return visible;
    }

    /**
     * setzt die Sichtbarkeit des Elements
     *
     * @param visible Sichtbarkeit
     */
    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * gibt das Icon zurück
     *
     * @return Icon
     */
    @Override
    public String getIcon() {
        return icon;
    }

    /**
     * setzt das Icon
     *
     * @param icon Icon
     */
    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
