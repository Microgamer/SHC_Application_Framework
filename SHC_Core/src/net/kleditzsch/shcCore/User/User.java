package net.kleditzsch.shcCore.User;

import net.kleditzsch.shcCore.Core.BasicElement;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashSet;
import java.util.Set;

/**
 * Benutzer
 *
 * @author     Oliver Kleditzsch
 * @copyright  Copyright (c) , Oliver Kleditzsch
 * @license    http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class User extends BasicElement {

    /**
     * Passwort Hash
     */
    private String passwordHash = "";

    /**
     * Gründer? (kann nicht gelöscht werden und hat immer alle Berechtigungen)
     */
    private boolean originator = false;

    /**
     * Benutzergruppen zu denen der Benutzer gehört
     */
    private Set<UserGroup> userGroups = new HashSet<>();

    /**
     * gibt an ob der Benutzer ein Gründer ist
     *
     * @return
     */
    public boolean isOriginator() {
        return originator;
    }

    /**
     * setzt den Benutzer als Gründer
     *
     * @param originator Gründer
     */
    public void setOriginator(boolean originator) {
        this.originator = originator;
    }

    /**
     * gibt eine Liste mit allen Benutzergruppen zurück zu denen der Benutzer gehört
     *
     * @return Liste mit Benutzergruppen
     */
    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    /**
     * gibt den Passwort Hash zurück
     *
     * @return Passwort Hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * setzt den Passwort Hash
     *
     * @param passwordHash Passwort Hash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * prüft ob das angegebene Passwort mit dem gespeicherten Passwort Hash übereinstimmt
     *
     * @param password Passwort
     * @return
     */
    public boolean checkPassword(String password) {

        if(BCrypt.checkpw(password, passwordHash)) {

            return true;
        }
        return false;
    }

    /**
     * prüft die Berechtigungen des Benutzers
     *
     * @param permission Berechtigung
     * @return
     */
    public boolean checkPermission(String permission) {

        //der Gründer hat immer alle Berechtigungen
        if(isOriginator()) {

            return true;
        }

        //Gruppenrechte prüfen
        for(UserGroup userGroup : userGroups) {

            if(userGroup.checkPermission(permission)) {

                return true;
            }
        }

        return false;
    }
}
