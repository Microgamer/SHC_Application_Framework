package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Gasmenge
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class GasAmountValue extends AbstractSensorValue {

    /**
     * Gasmenge (in Liter)
     */
    protected double gasAmount = 0.0;

    /**
     * gibt die Gasmenge zurück
     *
     * @return Gasmenge
     */
    public double getGasAmount() {
        return gasAmount;
    }

    /**
     * setzt die Gasmenge
     *
     * @param gasAmount Gasmenge
     */
    public void setGasAmount(double gasAmount) {
        this.gasAmount = gasAmount;
    }

    /**
     * fügt eine Gasmenge hinzu
     *
     * @param gasAmount Gasmenge
     */
    public void pushGasAmount(double gasAmount) {

        this.gasAmount += gasAmount;
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
        if(this.gasAmount > 1000.0) {

            return format2.format(this.gasAmount / 1000) + " m³";
        }
        return format.format(this.gasAmount) + " l";
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return GAS_AMOUNT;
    }
}
