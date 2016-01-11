package net.kleditzsch.shcCore.SwitchServer;

import net.kleditzsch.shcCore.Core.BasicElement;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;

/**
 * Schaltserver für Rasberry Pi
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RaspberryPiSwitchServer extends BasicElement implements SwitchServer {

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
     * Scripte aktiv?
     */
    protected boolean scriptEnabled = true;

    /**
     * Reboot aktiv?
     */
    protected boolean rebootEnabled = true;

    /**
     * Shutdown Aktiv?
     */
    protected boolean shutdownEnabled = true;

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
    public int getDeviceId() {
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

    /**
     * gibt an ob der Schaltserver Scripte ausführen kann
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isScriptEnabled() {
        return scriptEnabled;
    }

    /**
     * aktiviert/deaktiviert das Ausführen von Scripten
     *
     * @param scriptEnabled aktiviert/deaktiviert
     */
    public void setScriptEnabled(boolean scriptEnabled) {
        this.scriptEnabled = scriptEnabled;
    }

    /**
     * gibt an ob der Schaltserver neu gestartet werden kann
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isRebootEnabled() {
        return rebootEnabled;
    }

    /**
     * aktiviert/deaktiviert das Neustarten des Schaltservers
     *
     * @param rebootEnabled aktiviert/deaktiviert
     */
    public void setRebootEnabled(boolean rebootEnabled) {
        this.rebootEnabled = rebootEnabled;
    }

    /**
     * gibt an ob der Schaltserver Heruntergefahren werden kann
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isShutdownEnabled() {
        return shutdownEnabled;
    }

    /**
     * aktiviert/deaktiviert das Herunterfahren des Schaltservers
     *
     * @param shutdownEnabled aktiviert/deaktiviert
     */
    public void setShutdownEnabled(boolean shutdownEnabled) {
        this.shutdownEnabled = shutdownEnabled;
    }

    /**
     * gibt den Typ des Schaltservers an
     *
     * @return Typ
     */
    @Override
    public int getType() {
        return SWITCH_SERVER_RASPBERRY_PI;
    }
}
