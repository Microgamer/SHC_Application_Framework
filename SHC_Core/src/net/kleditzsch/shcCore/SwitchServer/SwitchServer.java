package net.kleditzsch.shcCore.SwitchServer;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SwitchServer {

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
    protected int device = 0;

    /**
     * aktiviert?
     */
    protected boolean enabled = true;

    /**
     * senden von 433MHz Befehlen aktiv?
     */
    protected boolean radioSocketsEnabled = true;

    /**
     * schreiben von GPIOs aktiv?
     */
    protected boolean writeGpioEnabled = true;

    /**
     * lesen von GPIOs aktiv
     */
    protected boolean readGpioEnabled = true;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isRadioSocketsEnabled() {
        return radioSocketsEnabled;
    }

    public void setRadioSocketsEnabled(boolean radioSocketsEnabled) {
        this.radioSocketsEnabled = radioSocketsEnabled;
    }

    public boolean isWriteGpioEnabled() {
        return writeGpioEnabled;
    }

    public void setWriteGpioEnabled(boolean writeGpioEnabled) {
        this.writeGpioEnabled = writeGpioEnabled;
    }

    public boolean isReadGpioEnabled() {
        return readGpioEnabled;
    }

    public void setReadGpioEnabled(boolean readGpioEnabled) {
        this.readGpioEnabled = readGpioEnabled;
    }
}
