package net.kleditzsch.shcCore.Automation.Interface.Sensor;

import net.kleditzsch.shcCore.Automation.Interface.AbstractAutomationDevice;

import java.time.LocalDateTime;

/**
 * Sensor Wert
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractSensorValue extends AbstractAutomationDevice implements SensorValue {

    /**
     * Identifizierer
     */
    protected String identifier = "";

    /**
     * Systemwert
     */
    protected boolean systemValue = false;

    /**
     * Zeitpunkt des letzten Werte Pushes
     */
    protected LocalDateTime lastPushTime;
    /**
     * gibt den Identifizierer zurück
     *
     * @return Identifizierer
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * setzt den Identifizierer
     *
     * @param identifier Identifizierer
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * gibt an ob der Sensorwert ein Systemwert ist (kann nicht gelöscht werden)
     *
     * @return true wenn Systemwert
     */
    @Override
    public boolean isSystemValue() {
        return this.systemValue;
    }

    /**
     * setzt den Sensorwert als Systemwert
     *
     * @param systemValue Systemwert
     */
    @Override
    public void setSystemValue(boolean systemValue) {
        this.systemValue = systemValue;
    }

    /**
     * gibt die Zeit des letzten Pushs zurück
     *
     * @return Zeit
     */
    @Override
    public LocalDateTime getLastPushTime() {
        return this.lastPushTime;
    }

    /**
     * setzt die Zeit des letzen Pushs
     *
     * @param lastPushTime Zeit
     */
    @Override
    public void setLastPushTime(LocalDateTime lastPushTime) {
        this.lastPushTime = lastPushTime;
    }
}
