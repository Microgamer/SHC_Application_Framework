package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractSingleSwitchable;

/**
 * WAKE On Lan
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class WakeOnLan extends AbstractSingleSwitchable {

    /**
     * MAC Adresse
     */
    protected String mac;

    /**
     * IP Adresse
     */
    protected String ipAddress;

    /**
     * Typ
     */
    protected int type = WAKE_ON_LAN;

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
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return type;
    }
}
