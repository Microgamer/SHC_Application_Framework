package net.kleditzsch.shcApplicationServer.Session;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Database.DatabaseEditor;
import net.kleditzsch.shcCore.Core.BasicElement;
import net.kleditzsch.shcCore.User.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Sitzungs Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SessionEditor implements DatabaseEditor {

    /**
     * Sitzung
     *
     * @author Oliver Kleditzsch
     * @copyright Copyright (c) 2016, Oliver Kleditzsch
     * @license http://opensource.org/licenses/gpl-license.php GNU Public License
     */
    private class Session {

        /**
         * Session ID
         */
        private String sessionId;

        /**
         * Session Timeout
         */
        private LocalDateTime sessionTimeout;

        /**
         * Angemeldeter Benutzer
         */
        private String userHash;

        /**
         * Session Daten
         */
        private Map<String, String> sessionData = new HashMap<>();

        /**
         * @param sessionId ID der Sitzung
         * @param userHash Benutzer der Sitzung
         */
        public Session(String sessionId, String userHash) {
            this.sessionId = sessionId;
            this.userHash = userHash;
            updateExpire();
        }

        /**
         * gibt die Session ID zurück
         *
         * @return Session ID
         */
        public String getSessionId() {
            return sessionId;
        }

        /**
         * gibt den Benutzer Hash des mit der Session verbundenen Benutzers zurück
         *
         * @return Benutzer Hash
         */
        public String getUser() {
            return userHash;
        }

        /**
         * gibt die Sessiondaten zurück
         *
         * @return Sessiondaten
         */
        public Map<String, String> getSessionData() {
            return sessionData;
        }

        /**
         * verlängert die Sitzungsdauer wieder um 600 Sekunden
         *
         * @return true wenn erfolgreich (false wenn Session schon abgelaufe ist)
         */
        public boolean updateExpire() {

            if(!isExpired()) {

                sessionTimeout = LocalDateTime.now().plusSeconds(600);
                return true;
            }
            return false;
        }

        /**
         * prüft ob die Session abgelaufen ist
         *
         * @return true wenn abgelaufen
         */
        public boolean isExpired() {

            if(sessionTimeout != null && (sessionTimeout.isBefore(LocalDateTime.now()) || sessionTimeout.isEqual(LocalDateTime.now()))) {

                return true;
            }
            return false;
        }
    }

    /**
     * Redis Keys für die Datenhaltung
     */
    private static final String KEY_SESSIONS = "shc:sessions";

    /**
     * Liste aller Sitzungen
     */
    private Map<String, Session> sessions = new HashMap<>();

    /**
     * lädt die Sitzungen aus der Datenbank
     */
    @Override
    public void loadData() {

        Jedis db = ShcApplicationServer.getInstance().getRedis();
        Map<String, String> sessions = db.hgetAll(KEY_SESSIONS);

        Gson gson = ShcApplicationServer.getInstance().getGson();

        this.sessions.clear();

        //Sitzungen laden
        for(String key : sessions.keySet()) {

            String sessionJson = sessions.get(key);
            Session session = gson.fromJson(sessionJson, Session.class);
            this.sessions.put(session.getSessionId(), session);
        }
    }

    /**
     * löscht abgelaufene Sitzungen
     */
    public void cleanOldSessions() {

        for(String sid : sessions.keySet()) {

            Session session = sessions.get(sid);
            if(session.isExpired()) {

                sessions.remove(sid);
            }
        }
    }

    /**
     * erstellt eine neue Benutzersitzung
     *
     * @param username Benutzername
     * @param password Passwort
     * @return Sitzungs ID
     */
    public String login(String username, String password) {

        User user = ShcApplicationServer.getInstance().getUserEditor().getUserByName(username);
        if(user != null && user.checkPassword(password)) {

            //neue Session erstellen
            String sessionId = BasicElement.createHash();
            Session session = new Session(sessionId, user.getPasswordHash());
            sessions.put(session.getSessionId(), session);
            return session.getSessionId();
        }
        return null;
    }

    /**
     * beendet eine Sitzung
     *
     * @param sessionId Sitzungs ID
     * @return true wenn erfolgreich
     */
    public boolean logout(String sessionId) {

        return removeSession(sessionId);
    }

    /**
     * gibt eine Sitzung zurück
     * (Das Session Objekt sollte nicht dauerhaft zwischengespeichert werden)
     *
     * @param sessionId Sitzungs ID
     * @return Sitzung
     */
    public Session getSessionById(String sessionId) {

        Session session = sessions.get(sessionId);
        if(session != null) {

            if(!session.updateExpire()) {

                return session;
            } else {

                removeSession(session.getSessionId());
            }
        }
        return null;
    }

    /**
     * beendet eine Sitzung
     *
     * @param sessionId Sitzungs ID
     * @return true wenn erfolgreich
     */
    protected boolean removeSession(String sessionId) {

        if(sessions.containsKey(sessionId)) {

            sessions.remove(sessionId);
            return true;
        }
        return false;
    }

    /**
     * schreibt die Sitzungen in die Datenbank
     */
    @Override
    public void saveData() {

        Pipeline pipeline = ShcApplicationServer.getInstance().getRedis().pipelined();
        Gson gson = ShcApplicationServer.getInstance().getGson();
        pipeline.del(KEY_SESSIONS);

        for(String sid : sessions.keySet()) {

            Session session = sessions.get(sid);
            String sessionJson = gson.toJson(session);

            pipeline.hset(KEY_SESSIONS, session.getSessionId(), sessionJson);
        }

        pipeline.sync();
    }
}
