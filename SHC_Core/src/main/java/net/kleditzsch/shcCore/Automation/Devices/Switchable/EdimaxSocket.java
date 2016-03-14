package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractDoubleSwitchable;

/**
 * Edimax Steckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class EdimaxSocket extends AbstractDoubleSwitchable {

    /**
     * Typen
     */
    public static final int TYPE_SP_1101W = 1;
    public static final int TYPE_SP_2101W = 2;

    /**
     * IP Adresse
     */
    protected String ipAddress;

    /**
     * Benutzername
     */
    protected String username;

    /**
     * Passwort
     */
    protected String password;

    /**
     * Typ
     */
    protected int socketType = TYPE_SP_1101W;

    /**
     * Sensorwerte der Steckdose
     */
    protected String powerSensorHash = "";
    protected String energySensorHash = "";

    /**
     * Typ
     */
    protected int type = EDIMAX_SOCKET;

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
     * gibt den Benutzernamen zurück
     *
     * @return Benutzernamen
     */
    public String getUsername() {
        return username;
    }

    /**
     * setzt den Benutzernamen
     *
     * @param username Benutzernamen
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gibt das Passwort zurück
     *
     * @return Passwort
     */
    public String getPassword() {
        return password;
    }

    /**
     * setzt das Passwort
     *
     * @param password Passwort
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gibt den Steckdosentyp zurück
     *
     * @return Steckdosentyp
     */
    public int getSocketType() {
        return socketType;
    }

    /**
     * setzt den Steckdosentyp
     *
     * @param socketType Steckdosentyp
     */
    public void setSocketType(int socketType) {
        this.socketType = socketType;
    }

    /**
     * gibt den Hash des Verbrauchs Sensorwertes zurück
     *
     * @return Hash vom Sensorwert
     */
    public String getPowerSensorHash() {
        return powerSensorHash;
    }

    /**
     * setzt den Hash des Verbrauchs Sensorwertes
     *
     * @param powerSensorHash Hash vom Sensorwert
     */
    public void setPowerSensorHash(String powerSensorHash) {
        this.powerSensorHash = powerSensorHash;
    }

    /**
     * gibt den Hash des Energie Sensorwertes zurück
     *
     * @return Hash vom Sensorwert
     */
    public String getEnergySensorHash() {
        return energySensorHash;
    }

    /**
     * setzt den Hash des Energie Sensorwertes
     *
     * @param energySensorHash Hash vom Sensorwert
     */
    public void setEnergySensorHash(String energySensorHash) {
        this.energySensorHash = energySensorHash;
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
