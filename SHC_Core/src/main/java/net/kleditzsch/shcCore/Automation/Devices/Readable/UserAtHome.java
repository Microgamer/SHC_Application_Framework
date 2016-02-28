package net.kleditzsch.shcCore.Automation.Devices.Readable;

import net.kleditzsch.shcCore.Automation.Interface.Readable.AbstractReadable;

import java.time.LocalDateTime;

/**
 * Benutzer zu Hause
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserAtHome extends AbstractReadable {

    /**
     * IP Adresse
     */
    protected String ipAddress;

    /**
     * Timeout (Zeit nach der der Benutzer als Offline markiert wird
     */
    protected int timeout;

    /**
     * Zeitstempel letzter Komtakt
     */
    protected LocalDateTime lastContact;

    /**
     * gibt an ob Ping verwendet werden soll oder ob der Status von Außen kommt
     */
    protected boolean useExternalData = false;

    protected int type = USER_AT_HOME;

    /**
     * gibt die IP Adresse des Benuzuers zurück
     *
     * @return IP Adresse
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * setzt die IP Adresse des Benutzers
     *
     * @param ipAddress IP Adresse
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * gibt den Timeout zurück
     *
     * @return Timeout in Sekunden
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * setzt den Timeout
     *
     * @param timeout Timeout in Sekunden
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * gibt den Zeitstempel des letzten Kontaktes zurück
     *
     * @return letzter Kontakt
     */
    public LocalDateTime getLastContact() {
        return lastContact;
    }

    /**
     * setzt den Zeitstempel des letzten Kontaktes
     *
     * @param lastContact letzter Kontakt
     */
    public void setLastContact(LocalDateTime lastContact) {
        this.lastContact = lastContact;
    }

    /**
     * gibt an ob Ping verwendet werden soll oder bo der Status von Außen kommt
     *
     * @return true die Daten von außen kommen sollen
     */
    public boolean isUseExternalData() {
        return useExternalData;
    }

    /**
     * aktiviert/deaktiviert die interne erkennung ob ein Benutzer zu Hause ist
     *
     * @param useExternalData true die Daten von außen kommen sollen
     */
    public void setUseExternalData(boolean useExternalData) {
        this.useExternalData = useExternalData;
    }

    /**
     * aktualisiert den Zeitstempel des letzten Kontaktes
     */
    public void updateLastContact() {

        this.lastContact = LocalDateTime.now();
    }

    /**
     * prüft ob ein Benutzer Online oder Offline ist
     */
    public void scheduleOnlineOffline() {

        //Timeout prüfen
        LocalDateTime timeout = lastContact.plusSeconds(this.timeout);
        LocalDateTime now = LocalDateTime.now();
        if(timeout.isBefore(now) || timeout.isEqual(now)) {

            this.state = OFFLINE;
        } else {

            this.state = ONLINE;
        }
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return type;
    }
}
