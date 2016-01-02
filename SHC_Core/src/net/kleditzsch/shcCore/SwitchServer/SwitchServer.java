package net.kleditzsch.shcCore.SwitchServer;

import net.kleditzsch.shcCore.Core.BasicElement;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SwitchServer extends BasicElement implements net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer {

    /**
     * IP Adresse
     */
    protected String ipAddress;

    /**
     * Port
     */
    protected int port;

    /**
     * Timeout in ms
     */
    protected int timeout = 500;

    /**
     * Gerät
     */
    protected int deviceId = 0;

    /**
     * aktiviert?
     */
    protected boolean enabled = true;

    /**
     * senden von 433MHz Befehlen aktiv?
     */
    protected boolean send433MHzEnabled = true;

    /**
     * schreiben von GPIOs aktiv?
     */
    protected boolean writeGpioEnabled = true;

    /**
     * lesen von GPIOs aktiv
     */
    protected boolean readGpioEnabled = true;

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
     * gibt den Port zurück
     *
     * @return Port
     */
    public int getPort() {
        return port;
    }

    /**
     * setzt den Port
     *
     * @param port Port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * gibt den Timeout in ms zurück
     *
     * @return Timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * setzt den Timeout in ms
     *
     * @param timeout Timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * gibt die Geräte ID zurück
     *
     * @return Geräte ID
     */
    public int getDevice() {
        return deviceId;
    }

    /**
     * setzt die Geräte ID
     *
     * @param deviceId Geräte ID
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * gibt an ob der Schaltserver aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * aktiviert/deaktiviert den Schaltserver
     *
     * @param enabled aktiviert/deaktiviert
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * gibt an ob der Schaltserver 433MHz Befehle senden kann
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isSend433MHzEnabled() {
        return send433MHzEnabled;
    }

    /**
     * aktiviert/deaktiviert das Senden der 433MHz Befehler
     *
     * @param send433MHzEnabled aktiviert/deaktiviert
     */
    public void setSend433MHzEnabled(boolean send433MHzEnabled) {
        this.send433MHzEnabled = send433MHzEnabled;
    }

    /**
     * gibt an ob der Schaltserver GPIOs schreiben kann
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isWriteGpioEnabled() {
        return writeGpioEnabled;
    }

    /**
     * aktiviert/deaktiviert das Schreiben von GPIOs
     *
     * @param writeGpioEnabled aktiviert/deaktiviert
     */
    public void setWriteGpioEnabled(boolean writeGpioEnabled) {
        this.writeGpioEnabled = writeGpioEnabled;
    }

    /**
     * gibt an ob der Schaltserver GPIOs lesen kann
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isReadGpioEnabled() {
        return readGpioEnabled;
    }

    /**
     * aktiviert/deaktiviert das Lesen von GPIOs
     *
     * @param readGpioEnabled aktiviert/deaktiviert
     */
    public void setReadGpioEnabled(boolean readGpioEnabled) {
        this.readGpioEnabled = readGpioEnabled;
    }
}
