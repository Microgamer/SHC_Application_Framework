package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Luftdruck
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AirPressureValue extends AbstractSensorValue {

    /**
     * Luftdruck
     */
    protected double airPressure = 0.0;

    /**
     * gibt den Luftdruck zurück
     *
     * @return Luftdruck
     */
    double getAirPressure() {
        return this.airPressure;
    }

    /**
     * setzt den Luftdruck
     *
     * @param airPressure Luftdruck
     */
    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }

    /**
     * füght den Luftdruck hinzu
     *
     * @param airPressure Luftdruck
     */
    void pushAirPressure(double airPressure) {

        this.airPressure = airPressure;
        this.lastPushTime = LocalDateTime.now();
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {

        DecimalFormat format = new DecimalFormat("#,###,###,##0");
        return format.format(this.airPressure) + " hPa";
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return AIR_PRESSURE;
    }
}
