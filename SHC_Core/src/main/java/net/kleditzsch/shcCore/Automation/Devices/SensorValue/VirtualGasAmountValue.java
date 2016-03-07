package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractVirtualSensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * Virtueller Gaszähler
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualGasAmountValue extends AbstractVirtualSensorValue {

    /**
     * Statistische Werte
     */
    protected double sum = 0.0;

    /**
     * Typ
     */
    protected int type = VIRTUAL_GAS_AMOUNT;

    /**
     * ermittelt aus den Sensorwerten die Statistischen Werte
     *
     * @param sensorValues Sensorwerte
     */
    @Override
    public void processValues(Set<SensorValue> sensorValues) {

        for(SensorValue sensorValue : sensorValues) {

            if(sensorValue instanceof GasAmountValue) {

                GasAmountValue gasAmountValue = (GasAmountValue) sensorValue;
                this.sum += gasAmountValue.getGasAmount();
            }
        }
    }

    /**
     * gibt die Summe zurück
     *
     * @return Summe
     */
    public double getSum() {
        return sum;
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {

        return null;
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayMinValue() {
        return null;
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayMaxValue() {
        return null;
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplaySumValue() {

        DecimalFormat format = new DecimalFormat("#,###,###,##0.0");
        DecimalFormat format2 = new DecimalFormat("#,###,###,##0.00");
        if(this.sum > 1000.0) {

            return format2.format(this.sum / 1000) + " m³";
        }
        return format.format(this.sum) + " l";
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
