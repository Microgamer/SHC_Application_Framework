package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Temperatur
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class TemperatureValue extends AbstractSensorValue {

    /**
     * Temperatur
     */
    protected double temperature = 0.0;

    /**
     * Offset
     */
    protected double offset = 0.0;

    /**
     * Typ
     */
    protected int type = TEMPERATURE;

    /**
     * gibt die Temperatur zurück
     *
     * @return Temperatur
     */
    public double getTemperature() {
        return this.temperature;
    }

    /**
     * gibt die Temperatur mit Offset zurück
     *
     * @return Temperatur mit Offset
     */
    public double getTemperatureWithOffset() {
        return this.temperature + this.offset;
    }

    /**
     * setzt die Temperatur
     *
     * @param temperature Temperatur
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * fügt eine neue Temperatur ein
     *
     * @param temperature Temperatur
     */
    public void pushTemperature(double temperature) {

        this.temperature = temperature;
        this.lastPushTime = LocalDateTime.now();
    }

    /**
     * gibt das Offset zurück
     *
     * @return Offset
     */
    public double getOffset() {
        return this.offset;
    }

    /**
     * setzt das Offset
     *
     * @param offset Offset
     */
    public void setOffset(double offset) {
        this.offset = offset;
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {

        DecimalFormat format = new DecimalFormat("-#,###,###,##0.00");
        return format.format(this.temperature + this.offset) + " °C";
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
