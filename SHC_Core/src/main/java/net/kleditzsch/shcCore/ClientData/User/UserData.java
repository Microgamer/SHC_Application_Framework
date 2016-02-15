package net.kleditzsch.shcCore.ClientData.User;

import net.kleditzsch.shcCore.Core.BasicElement;

import java.util.HashSet;
import java.util.Set;

/**
 * Benutzer (Client)
 *
 * @author     Oliver Kleditzsch
 * @copyright  Copyright (c) , Oliver Kleditzsch
 * @license    http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserData extends BasicElement {

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
    private Set<String> userGroups = new HashSet<>();

    public UserData() {}

    /**
     * gibt an ob der Benutzer ein Gründer ist
     *
     * @return
     */
    public boolean isOriginator() {
        return originator;
    }

    /**
     * markiert den benutzer als Hauptbenutzer
     *
     * @param originator Haubtbenutzer
     */
    public void setOriginator(boolean originator) {
        this.originator = originator;
    }

    /**
     * gibt eine Liste mit allen Benutzergruppen zurück zu denen der Benutzer gehört
     *
     * @return Liste mit Benutzergruppen
     */
    public Set<String> getUserGroups() {
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
}