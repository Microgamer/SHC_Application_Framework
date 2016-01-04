package net.kleditzsch.shcCore.Room.Elemnets.Elements;

import net.kleditzsch.shcCore.Room.Elemnets.Abstract.AbstractSwitchable;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Edimax Steckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractEdimaxSocket extends AbstractSwitchable {

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
     * setzt die Sensorwerte
     *
     * @param actualPower Momentanverbrauch
     * @param energy Energieverbrauch
     */
    public void pushValues(double actualPower, double energy) {

        this.actualPower = actualPower;
        this.energy = energy;
        this.lastContactTime = LocalDateTime.now();
    }

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    @Override
    public void triggerOn() {

        lastToggleTime = LocalDateTime.now();
        state = ON;
    }

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void triggerOff() {

        computeOperatingTime();
        lastToggleTime = LocalDateTime.now();
        state = OFF;
    }

    /**
     * berechnet beim ausschalten die Betriebszeit und den Energieverbrauch
     */
    @Override
    protected void computeOperatingTime() {

        if(lastToggleTime != null) {

            //Betriebszeit ermitteln
            Duration duration = Duration.between(lastToggleTime, LocalDateTime.now());
            long operatingSeconds = duration.toMillis() / 1000;

            //gesampte Betriebszeit aktualisieren
            this.operatingSeconds += operatingSeconds;

            if(socketType == TYPE_SP_1101W) {

                //Energieverbrauch errechnen
                double energy = actualPower * (((double) operatingSeconds) / 3600d);
                this.energy += energy;
            }
        }
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return EDIMAX_SOCKET;
    }
}
