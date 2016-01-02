package net.kleditzsch.shcCore.Room.Elemnets.Abstract;

import net.kleditzsch.shcCore.Core.BasicElement;
import net.kleditzsch.shcCore.Room.Elemnets.Interface.ViewElement;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;

import java.util.HashSet;
import java.util.Set;

/**
 * Standard Anzeige Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractViewElement extends BasicElement implements ViewElement {

    /**
     * Liste mit den Benutzergruppen
     * (alle Benutzer die einer dieser Gruppen angehören können das Element angezeigt bekommen)
     */
    protected Set<UserGroup> allowedUserGroups = new HashSet<>();

    /**
     * aktiviert?
     */
    protected boolean enabled = true;

    /**
     * fügt eine Benutzergruppe als erlaubte Benutzergruppe hinzu
     *
     * @param userGroup Benutzergruppe
     */
    public void addAllowedUserGroup(UserGroup userGroup) {

        allowedUserGroups.add(userGroup);
    }

    /**
     * entfernt die Benutzergruppe
     *
     * @param userGroup Benutzergruppe
     * @return true wenn erfolgreich gelöscht
     */
    public boolean removeAllowedUserGroup(UserGroup userGroup) {

        if(allowedUserGroups.contains(userGroup)) {

            allowedUserGroups.remove(userGroup);
            return true;
        }
        return false;
    }

    /**
     * entfernt alle Benutzergruppen
     */
    public void removeAllAllowedUserGroups() {

        allowedUserGroups.clear();
    }

    /**
     * gibt eine Liste mit allen erlaubten Benutzergruppen zurück
     *
     * @return erlaubte Benutzergruppen
     */
    public Set<UserGroup> listAllowedUserGroups() {

        Set<UserGroup> userGroupSet = new HashSet<>();
        userGroupSet.addAll(allowedUserGroups);
        return userGroupSet;
    }

    /**
     * prüft ob der übergebene Benutzer für dieses Element berechtigt ist
     *
     * @param user Benutzer
     * @return true wenn Berechtigt
     */
    public boolean isUserEntitled(User user ) {

        Set<UserGroup> userGroups = user.getUserGroups();

        for(UserGroup userGroup : userGroups) {

            if(allowedUserGroups.contains(userGroup)) {

                return true;
            }
        }
        return false;
    }

    /**
     * gibt an ob der Raum aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * aktiviert/deaktiviert den Raum
     *
     * @param enabled aktiviert/deaktiviert
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
