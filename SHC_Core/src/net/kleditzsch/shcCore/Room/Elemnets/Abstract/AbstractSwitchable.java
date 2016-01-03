package net.kleditzsch.shcCore.Room.Elemnets.Abstract;

import net.kleditzsch.shcCore.Room.Elemnets.Interface.Switchable;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * schaltbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractSwitchable extends AbstractStateElement implements Switchable {

    /**
     * gibt an ob der Status angezeigt werden soll
     */
    protected boolean showStateEnabled = true;

    /**
     * gibt den Zeitpunkt des letzen Schaltens zurück
     */
    protected LocalDateTime lastToggleTime;

    /**
     * Betriebsstunden in Sekunden
     */
    protected long operatingSeconds = 0;

    /**
     * Energieverbrauch in Wh
     */
    protected double energy;

    /**
     * aktueller Energieverbrauch in W
     */
    protected double actualPower = 0;

    /**
     * Datenaufzeichnung aktiviert?
     */
    protected boolean dataRecording;

    /**
     * Zeitpunkt letzter Kontakt zum Sensor
     */
    protected LocalDateTime lastContactTime;

    /**
     * Button Text
     */
    protected String buttonTextOn;
    protected String buttonTextOff;

    /**
     * gibt an ob die Anzeige des Status der schaltbaren Elemente aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isShowStateEnabled() {
        return showStateEnabled;
    }

    /**
     * aktiviert/deaktiviert die Anzeige des Status der schaltbaren Elemente
     *
     * @param showStateEnabled aktiviert/deaktiviert
     */
    public void setShowStateEnabled(boolean showStateEnabled) {
        this.showStateEnabled = showStateEnabled;
    }

    /**
     * gibt die Zeit des letzten Schaltvorgans zurück
     *
     * @return Zeit
     */
    public LocalDateTime getLastToggleTime() {
        return lastToggleTime;
    }

    /**
     * setzt die Zeit des letzen Schaltvorganges
     *
     * @param lastToggleTime Zeit
     */
    public void setLastToggleTime(LocalDateTime lastToggleTime) {
        this.lastToggleTime = lastToggleTime;
    }

    /**
     * gibt die Betriebszeit in Sekunden zurück
     *
     * @return Betriebszeit
     */
    public long getOperatingSeconds() {
        return operatingSeconds;
    }

    /**
     * setzt die Betriebszeit in Sekunden
     *
     * @param operatingSeconds Betriebszeit
     */
    public void setOperatingSeconds(long operatingSeconds) {
        this.operatingSeconds = operatingSeconds;
    }

    /**
     * gibt den aktuellen Energieverbrauch zurück
     *
     * @return Energieverbrauch in W
     */
    public double getActualPower() {
        return actualPower;
    }

    /**
     * setzt den aktuellen Energieverbrauch
     *
     * @param actualPower Energieverbrauch in W
     */
    public void setActualPower(double actualPower) {
        this.actualPower = actualPower;
    }

    /**
     * gibt den Energieverbrauch zurück
     *
     * @return Energieverbrauch Wh
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * setzt den Energieverbrauch
     *
     * @param energy Energieverbrauch in Wh
     */
    public void setEnergy(double energy) {
        this.energy = energy;
    }

    /**
     * gibt an ob die Datenaufzeichnung aktiviert/deaktiviert ist
     *
     * @return true wenn aktiv
     */
    public boolean isDataRecordingEnabled() {
        return dataRecording;
    }

    /**
     * aktiviert/deaktiviert die Datenaufzeichnung
     *
     * @param enabled aktiviert/deaktiviert
     */
    public void setDateRecordingEnabled(boolean enabled) {
        dataRecording = enabled;
    }

    /**
     * gibt die Zeit des Letzten Kontaktes zum Sensor zurück
     *
     * @return Zeit
     */
    public LocalDateTime getLastContactTime() {
        return lastContactTime;
    }

    /**
     * setzt die Zeit des letzten Kontaktes zum Sensor auf die übergebene Zeit
     *
     * @param ldt Zeit
     */
    public void setLastContactTime(LocalDateTime ldt) {
        lastContactTime = ldt;
    }

    /**
     * setzt die Zeit des letzten Kontaktes zum Sensor auf die aktuelle Zeit
     */
    public void setLastContactTimeNow() {

        lastContactTime = LocalDateTime.now();
    }

    /**
     * setzt den Text der Buttons des schaltbaren Elements
     *
     * @param buttonOn  Text Button "an"
     * @param buttonOff Text Button "aus"
     */
    public void setButtonText(String buttonOn, String buttonOff) {

        buttonTextOn = buttonOn;
        buttonTextOff = buttonOff;
    }

    /**
     * gibt den Text des "an" Button zurück
     *
     * @return Text
     */
    public String getOnButtonText() {

        return buttonTextOn;
    }

    /**
     * gibt den Text des "aus" Button zurück
     *
     * @return Text
     */
    public String getOffButtonText() {

        return buttonTextOff;
    }

    /**
     * schaltet zwichen den Zuständen um
     */
    public void triggerToggle() {

        if(state == ON) {

            triggerOff();
        } else {

            triggerOn();
        }
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
    protected void computeOperatingTime() {

        if(lastToggleTime != null) {

            //Betriebszeit ermitteln
            Duration duration = Duration.between(lastToggleTime, LocalDateTime.now());
            long operatingSeconds = duration.toMillis() / 1000;

            //gesampte Betriebszeit aktualisieren
            this.operatingSeconds += operatingSeconds;

            //Energieverbrauch errechnen
            double energy = actualPower * (((double) operatingSeconds) / 3600d);
            this.energy += energy;
        }
    }
}
