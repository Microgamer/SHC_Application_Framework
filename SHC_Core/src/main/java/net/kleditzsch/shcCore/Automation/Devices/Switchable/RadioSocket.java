package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractDoubleSwitchable;

/**
 * Funksteckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RadioSocket extends AbstractDoubleSwitchable {

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
     * ID oder Systemcode beim senden verwenden
     */
    protected boolean useID = false;

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
     * gibt an ob die ID statt des Systemcodes verwendet werden soll
     *
     * @return ID wenn true
     */
    public boolean isUseID() {
        return useID;
    }

    /**
     * aktiviert die Verwendung von ID beim senden
     *
     * @param useID ID wenn true
     */
    public void setUseID(boolean useID) {
        this.useID = useID;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return RADIO_SOCKET;
    }
}
