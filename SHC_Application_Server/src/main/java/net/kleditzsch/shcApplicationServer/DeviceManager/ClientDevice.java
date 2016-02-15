package net.kleditzsch.shcApplicationServer.DeviceManager;

/**
 * ClientData Gerät
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ClientDevice {

    /**
     * ClientData Hash
     */
    protected String clientHash;

    /**
     * Gerätekennung
     */
    protected String userAgend;

    /**
     * Zutritt erlaubt
     */
    protected boolean allowed = false;

    /**
     * gibt den ClientData Hash zurück
     *
     * @return ClientData Hash
     */
    public String getClientHash() {
        return clientHash;
    }

    /**
     * setzt den ClientData Hasn
     *
     * @param clientHash ClientData Hash
     */
    public void setClientHash(String clientHash) {
        this.clientHash = clientHash;
    }

    /**
     * gibt die Gerätekennung zurück
     *
     * @return Gerätekennung
     */
    public String getUserAgend() {
        return userAgend;
    }

    /**
     * setzt die Gerätekennung
     *
     * @param userAgend Gerätekennung
     */
    public void setUserAgend(String userAgend) {
        this.userAgend = userAgend;
    }

    /**
     * gibt an ob für das Gerät der Zutritt erlaubt ist
     *
     * @return true wenn erlaubt
     */
    public boolean isAllowed() {
        return allowed;
    }

    /**
     * setzt die Zutrittsbeschränkung für das Gerät
     *
     * @param allowed true wenn erlaubt
     */
    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
}
