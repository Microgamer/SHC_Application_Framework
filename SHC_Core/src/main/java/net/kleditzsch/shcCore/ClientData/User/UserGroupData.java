package net.kleditzsch.shcCore.ClientData.User;

import net.kleditzsch.shcCore.Core.BasicElement;

import java.util.HashSet;
import java.util.Set;

/**
 * Benutzer Gruppe (Client)
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserGroupData extends BasicElement {

    /**
     * Beschreibung der Gruppe
     */
    private String descripion = "";

    /**
     * Systemgruppe? (kann nicht gelöscht werden)
     */
    private boolean systemGroup = false;

    /**
     * liste mit den Berechtigungen der Gruppe
     */
    private Set<String> permissions = new HashSet<>();

    public UserGroupData() {}

    /**
     * @param systemGroup Systemgruppe
     */
    public UserGroupData(boolean systemGroup) {

        this.systemGroup = systemGroup;
    }

    /**
     * gibt die Gruppenbeschreibung zurück
     *
     * @return Gruppenbeschreibung
     */
    public String getDescripion() {
        return descripion;
    }

    /**
     * setzt die Gruppenbeschreibung
     *
     * @param descripion Gruppenbeschreibung
     */
    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    /**
     * gibt an ob die Gruppe eine Systemgruppe ist
     *
     * @return
     */
    public boolean isSystemGroup() {
        return systemGroup;
    }

    /**
     * markiert die Gruppe als Systemgruppe
     *
     * @param systemGroup
     */
    public void setSystemGroup(boolean systemGroup) {
        this.systemGroup = systemGroup;
    }

    /**
     * gibt die Liste mit den Berechtigungen zurück
     *
     * @return
     */
    public Set<String> getPermissions() {
        return permissions;
    }

    /**
     * fügt eine Berechtigung hinzu
     *
     * @param permission Berechtigung
     */
    public void addPermission(String permission) {
        permissions.add(permission);
    }

    /**
     * löscht eine Berechtigung
     *
     * @param permission Berechtigung
     * @return
     */
    public boolean removePermission(String permission) {
        return permissions.remove(permission);
    }

    /**
     * prüft die Berechtigungen der Benutzergruppe
     *
     * @param permission Berechtigung
     * @return
     */
    public boolean checkPermission(String permission) {

        if(permissions.contains(permission)) {

            return true;
        }
        return false;
    }
}
