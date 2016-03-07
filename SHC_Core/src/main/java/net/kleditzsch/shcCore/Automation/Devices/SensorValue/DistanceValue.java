package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Entfernung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DistanceValue extends AbstractSensorValue {

    /**
     * Entfernung (in mm)
     */
    protected double distance = 0.0;

    /**
     * Offset in mm
     */
    protected double offset = 0.0;

    /**
     * Typ
     */
    protected int type = DISTANCE;

    /**
     * gibt die Entfernung zurück
     *
     * @return Entfernung
     */
    public double getDistance() {
        return distance;
    }

    /**
     * gibt die Entfernung mit Offset zurück
     *
     * @return Entfernung
     */
    public double getDistanceWithOffset() {
        return distance + this.offset;
    }

    /**
     * setzt die Entfernung
     *
     * @param distance Entfernung
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * fügt die Entfernung hinzu
     *
     * @param distance Entfernung
     */
    public void pushDistance(double distance) {

        this.distance = distance;
        this.lastPushTime = LocalDateTime.now();
    }

    /**
     * setzt das Offset
     *
     * @return Offset
     */
    public double getOffset() {
        return offset;
    }

    /**
     * gibt das Offset zurück
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

        DecimalFormat format = new DecimalFormat("#,###,###,##0");
        DecimalFormat format1 = new DecimalFormat("#,###,###,##0.0");
        DecimalFormat format2 = new DecimalFormat("#,###,###,##0.00");
        if((this.distance + this.offset) > 1000.0) {

            return format2.format((this.distance + this.offset) / 1000) + " m";
        } else if((this.distance + this.offset) > 10.0) {

            return format1.format((this.distance + this.offset) / 10) + " cm";
        }
        return format.format((this.distance + this.offset)) + " mm";
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
