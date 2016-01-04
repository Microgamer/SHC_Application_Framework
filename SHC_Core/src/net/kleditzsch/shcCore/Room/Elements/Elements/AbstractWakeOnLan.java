package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSwitchable;

/**
 * WakeOnLan
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractWakeOnLan extends AbstractSwitchable {

    protected String mac;

    protected String ipAddress;

    /**
     * gibt die MAC Adresse zurück
     *
     * @return MAC Adresse
     */
    public String getMac() {
        return mac;
    }

    /**
     * setzt die MAC Adresse
     *
     * @param mac MAC Adresse
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * gibt die IP Adresse zurück
     *
     * @return IP Adresse
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * setzt die IP Adresse
     *
     * @param ipAddress IP Adresse
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void triggerOff() {}

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return WAKE_ON_LAN;
    }
}
