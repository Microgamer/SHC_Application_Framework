package net.kleditzsch.shcApplicationServer.User;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Database.DatabaseEditor;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Benutzer Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserEditor implements DatabaseEditor {

    /**
     * Redis Keys für die Datenhaltung
     */
    private static final String KEY_USER_GROUPS = "shc:userGroups";
    private static final String KEY_USERS = "shc:users";

    /**
     * Liste mit allen Benutzers
     */
    private Map<String, User> users = new HashMap<>();

    /**
     * Liste mit allen Benutzergruppen
     */
    private Map<String, UserGroup> userGroups = new HashMap<>();

    /**
     * läd die Benutzer und Benutzergruppen aus der Datenbank
     */
    public void loadData() {

        Jedis db = ShcApplicationServer.getInstance().getRedis();
        Map<String, String> userGroupsList = db.hgetAll(KEY_USER_GROUPS);
        Map<String, String> usersList = db.hgetAll(KEY_USERS);

        Gson gson = ShcApplicationServer.getInstance().getGson();
        userGroups.clear();
        users.clear();

        //Benutzergruppen laden
        for (String hash : userGroupsList.keySet()) {

            String userGroupJson = userGroupsList.get(hash);
            UserGroup userGroup = gson.fromJson(userGroupJson, UserGroup.class);

            userGroups.put(userGroup.getHash(), userGroup);
        }

        //Benutzer Laden
        for (String hash : usersList.keySet()) {

            String userJson = usersList.get(hash);
            User user = gson.fromJson(userJson, User.class);

            users.put(user.getHash(), user);
        }

        //sofern noch keine Daten vorhanden ninitalisieren
        if(userGroups.size() == 0) {

            addSystemUsersAndGroups();
        }
    }

    /**
     * gibt die Liste mit allen Benutzern zurück
     *
     * @return Benutzer Hashes Liste
     */
    public Set<User> getUsers() {

        Set<User> userSet = new HashSet<>();
        userSet.addAll(users.values());
        return userSet;
    }

    /**
     * gibt einen bestimmten Benutzer zurück
     *
     * @param hash Identififzierung
     * @return Benutzer
     */
    public User getUser(String hash) {

        return users.get(hash);
    }

    /**
     * gibt sofern vorhanden das Benutzerobjekt zum Benutzernamen zurück
     *
     * @param username Benutzername
     * @return Benutzer
     */
    public User getUserByName(String username) {

        for (String hash : users.keySet()) {

            User user = users.get(hash);
            if(user.getName().equals(username)) {

                return user;
            }
        }
        return null;
    }

    /**
     * regestriert einen neuen Benutzer
     *
     * @param user Benutzer
     * @return true wenn erfolgreich eingefügt
     */
    public boolean addUser(User user) {

        if(!users.containsKey(user.getHash())) {

            users.put(user.getHash(), user);
            return true;
        }
        return false;
    }

    /**
     * entfernt einen Benutzer
     *
     * @param user Benutzer
     * @return true wenn erflogrech entfernt
     */
    public boolean removeUser(User user) {

        if(users.containsKey(user.getHash()) && !user.isOriginator()) {

            users.remove(user.getHash());
            return true;
        }
        return false;
    }

    /**
     * gibt eine Liste mit allen Benutzergruppen zurück
     *
     * @return Benutzergruppen
     */
    public Set<UserGroup> getUserGroups() {

        Set<UserGroup> userGroupSet = new HashSet<>();
        userGroupSet.addAll(userGroups.values());
        return userGroupSet;
    }

    /**
     * gibt eine bestimmte Benutzergruppe zurück
     *
     * @param hash Identififzierung
     * @return Benutzer Gruppe
     */
    public UserGroup getUserGroup(String hash) {

        return userGroups.get(hash);
    }

    /**
     * registriert eine neue Benutzergruppe
     *
     * @param userGroup Benutzergruppe
     * @return true wenn erfolgreich eingefügt
     */
    public boolean addUserGroup(UserGroup userGroup) {

        if(!userGroups.containsKey(userGroup.getHash())) {

            userGroups.put(userGroup.getHash(), userGroup);
            return true;
        }
        return false;
    }

    /**
     * entfernt eine Benutzergruppe
     *
     * @param userGroup
     * @return true wenn erflogrech entfernt
     */
    public boolean removeUserGroup(UserGroup userGroup) {

        if(userGroups.containsKey(userGroup.getHash()) && !userGroup.isSystemGroup()) {

            userGroups.remove(userGroup.getHash());
            return true;
        }
        return false;
    }

    /**
     * erstellt die System Benutzer und Benuzergruppen
     */
    private void addSystemUsersAndGroups() {

        UserGroup users = new UserGroup(true);
        users.setHash(UserGroup.createHash());
        users.setName("Benutzer");
        users.setDescripion("Diese Gruppe wird für alle angemeldeten Benutzer verwendet");
        addUserGroup(users);

        UserGroup admins = new UserGroup(true);
        admins.setHash(UserGroup.createHash());
        admins.setName("Administartoren");
        admins.setDescripion("Diese Gruppe wird für alle Administratoren verwendet");
        addUserGroup(admins);

        User admin = new User(true);
        admin.setHash(User.createHash());
        admin.setName("admin");
        admin.setPasswordHash(User.createHash().substring(0, 11));
        admin.getUserGroups().add(admins);
        addUser(admin);

        saveData();
    }

    /**
     * Speichert alle Benutzer und Benutzergruppen in der Datenbank
     */
    public void saveData() {

        Gson gson = ShcApplicationServer.getInstance().getGson();

        Pipeline pipeline = ShcApplicationServer.getInstance().getRedis().pipelined();
        pipeline.del(KEY_USER_GROUPS, KEY_USERS);

        //Benutzergruppen
        for (String userGroupHash : userGroups.keySet()) {

            UserGroup userGroup = userGroups.get(userGroupHash);
            pipeline.hset(KEY_USER_GROUPS, userGroup.getHash(), gson.toJson(userGroup));
        }

        //Benutzer
        for (String usersHash : users.keySet()) {

            User user = users.get(usersHash);
            pipeline.hset(KEY_USERS, user.getHash(), gson.toJson(user));
        }
        pipeline.sync();
    }
}
