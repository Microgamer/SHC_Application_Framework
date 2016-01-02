package net.kleditzsch.shcCore.Room.Elemnets.Switchables;

import net.kleditzsch.shcCore.Room.Elemnets.Abstract.AbstractSwitchable;
import net.kleditzsch.shcCore.Room.ViewElements;

/**
 * 433MHz Funksteckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractRadioSocket extends AbstractSwitchable {

    /**
     * 433MHz Protokoll
     */
    protected String protocol;

    /**
     * Systemcode
     */
    protected String systemCode;

    /**
     * Gerätecode
     */
    protected String deviceCode;

    /**
     * sende Weiderholungen
     */
    protected int continues;

    /**
     * gibt das 433MHz Protokoll zurück
     *
     * @return 433MHz Potokoll
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * setzt das 433MHz Potokoll
     *
     * @param protocol 433MHz Potokoll
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * gibt den Systemcode zurück
     *
     * @return Systemcode
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * setzt den Systemcode
     *
     * @param systemCode Systemcode
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    /**
     * gibt den Gerätecode zurück
     *
     * @return Gerätecode
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * setzt den Gerätecode
     *
     * @param deviceCode Gerätecode
     */
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    /**
     * gibt die Anzahl der Sendewiederholungen zurück
     *
     * @return Anzahl der Sendewiederholungen
     */
    public int getContinues() {
        return continues;
    }

    /**
     * setzt die Anzahl der Sendewiederholungen
     *
     * @param continues Anzahl der Sendewiederholungen
     */
    public void setContinues(int continues) {
        this.continues = continues;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    public int getType() {
        return ViewElements.RADIO_SOCKET;
    }
}
