package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Standorthöhe
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AltitudeValue extends AbstractSensorValue {

    /**
     * Standorthöhe
     */
    protected double altitude = 0.0;

    /**
     * Typ
     */
    protected int type = ALTITUDE;

    /**
     * gibt die Standorthöhe zurück
     *
     * @return Standorthöhe
     */
    double getAltitude() {
        return this.altitude;
    }

    /**
     * setzt die Standorthöhe
     *
     * @param altitude Standorthöhe
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /**
     * fügt die Standorthöhe hinzu
     *
     * @param altitude Standorthöhe
     */
    void pushAltitude(double altitude) {

        this.altitude = altitude;
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
        return format.format(this.altitude) + " m";
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
