package net.kleditzsch.shcCore.User;

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
    String USER_ADD = "permission.user.add";
    String USER_EDIT = "permission.user.edit";
    String USER_DELETE = "permission.user.delete";

    /**
     * Benutzergruppen verwalten
     */
    String USERGROUP_ADD = "permission.usergroup.add";
    String USERGROUP_EDIT = "permission.usergroup.edit";
    String USERGROUP_DELETE = "permission.usergroup.delete";
}
