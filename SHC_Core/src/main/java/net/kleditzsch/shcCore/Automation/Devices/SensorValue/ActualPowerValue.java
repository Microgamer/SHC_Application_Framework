package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Momentaner Energieverbrauch
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ActualPowerValue extends AbstractSensorValue {

    /**
     * Momentaner Energieverbrauch
     */
    protected double actualPower = 0.0;

    /**
     * Typ
     */
    protected int type = ACTUAL_POWER;

    /**
     * gibt den aktuellen Energieverbrauch zurück
     *
     * @return Energieverbrauch in W
     */
    double getActualPower() {
        return this.actualPower;
    }

    /**
     * setzt den aktuellen Energieverbrauch
     *
     * @param actualPower aktuellen Energieverbrauch
     */
    public void setActualPower(double actualPower) {
        this.actualPower = actualPower;
    }

    /**
     * fügt einen aktuellen Energieverbrauch
     *
     * @param actualPower Energieverbrauch in W
     */
    void pushActualPower(double actualPower) {

        this.actualPower = actualPower;
        this.lastPushTime = LocalDateTime.now();
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {

        DecimalFormat format = new DecimalFormat("#,###,###,##0.0");
        DecimalFormat format2 = new DecimalFormat("#,###,###,##0.00");
        if(this.actualPower > 1000.0) {

            return format2.format(this.actualPower / 1000) + " KW";
        }
        return format.format(this.actualPower) + " W";
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
