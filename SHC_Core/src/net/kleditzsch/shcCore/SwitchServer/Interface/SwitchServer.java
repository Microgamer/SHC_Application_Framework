package net.kleditzsch.shcCore.SwitchServer.Interface;

import net.kleditzsch.shcCore.Core.Element;

/**
 * Schaltserver
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface SwitchServer extends Element {

    /**
     * gibt die IP Adresse zurück
     *
     * @return IP Adresse
     */
    String getIpAddress();

    /**
     * setzt die IP Adresse
     *
     * @param ipAddress IP Adresse
     */
    void setIpAddress(String ipAddress);

    /**
     * gibt den Port zurück
     *
     * @return Port
     */
    int getPort();

    /**
     * setzt den Port
     *
     * @param port Port
     */
    void setPort(int port);

    /**
     * gibt den Timeout in ms zurück
     *
     * @return Timeout
     */
    int getTimeout();

    /**
     * setzt den Timeout in ms
     *
     * @param timeout Timeout
     */
    void setTimeout(int timeout);

    /**
     * gibt die Geräte ID zurück
     *
     * @return Geräte ID
     */
    int getDevice();

    /**
     * setzt die Geräte ID
     *
     * @param deviceId Geräte ID
     */
    void setDeviceId(int deviceId);

    /**
     * gibt an ob der Schaltserver aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    boolean isEnabled();

    /**
     * aktiviert/deaktiviert den Schaltserver
     *
     * @param enabled aktiviert/deaktiviert
     */
    void setEnabled(boolean enabled);

    /**
     * gibt an ob der Schaltserver 433MHz Befehle senden kann
     *
     * @return aktiviert/deaktiviert
     */
    boolean isSend433MHzEnabled();

    /**
     * aktiviert/deaktiviert das Senden der 433MHz Befehler
     *
     * @param send433MHzEnabled aktiviert/deaktiviert
     */
    void setSend433MHzEnabled(boolean send433MHzEnabled);

    /**
     * gibt an ob der Schaltserver GPIOs schreiben kann
     *
     * @return aktiviert/deaktiviert
     */
    boolean isWriteGpioEnabled();

    /**
     * aktiviert/deaktiviert das Schreiben von GPIOs
     *
     * @param writeGpioEnabled aktiviert/deaktiviert
     */
    void setWriteGpioEnabled(boolean writeGpioEnabled);

    /**
     * gibt an ob der Schaltserver GPIOs lesen kann
     *
     * @return aktiviert/deaktiviert
     */
    boolean isReadGpioEnabled();

    /**
     * aktiviert/deaktiviert das Lesen von GPIOs
     *
     * @param readGpioEnabled aktiviert/deaktiviert
     */
    void setReadGpioEnabled(boolean readGpioEnabled);
}
