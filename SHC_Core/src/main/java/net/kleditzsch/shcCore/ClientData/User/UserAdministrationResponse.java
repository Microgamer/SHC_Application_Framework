package net.kleditzsch.shcCore.ClientData.User;

import net.kleditzsch.shcCore.ClientData.AbstractResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Antwort
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserAdministrationResponse extends AbstractResponse {

    protected List<UserData> userDatas = new ArrayList();
    protected List<UserGroupData> groups = new ArrayList();
    protected List<String> permissions = new ArrayList();

    public List<UserData> getUserDataList() {
        return userDatas;
    }

    public List<UserGroupData> getGroupDataList() {
        return groups;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
