package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Wassermenge
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class WaterAmountValue extends AbstractSensorValue {

    /**
     * Wassermenge (in Liter)
     */
    protected double waterAmount = 0.0;

    /**
     * Typ
     */
    protected int type = WATER_AMOUNT;

    /**
     * gibt die Wassermenge zurück
     *
     * @return Wassermenge
     */
    public double getWaterAmount() {
        return waterAmount;
    }

    /**
     * setzt die Wassermenge
     *
     * @param waterAmount Wassermenge
     */
    public void setWaterAmount(double waterAmount) {
        this.waterAmount = waterAmount;
    }

    /**
     * addiert eine Wassermenge hinzu
     *
     * @param waterAmount Wassermenge
     */
    public void pushWaterAmount(double waterAmount) {

        this.waterAmount += waterAmount;
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
        if(this.waterAmount > 1000.0) {

            return format2.format(this.waterAmount / 1000) + " m³";
        }
        return format.format(this.waterAmount) + " l";
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
