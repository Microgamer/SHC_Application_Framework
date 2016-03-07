package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractVirtualSensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * Virtueller aktueller Verbrauch
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualActualPowerValue extends AbstractVirtualSensorValue {

    /**
     * Statistische Werte
     */
    protected double average = 0.0;
    protected double sum = 0.0;
    protected double min = -100000000;
    protected double max = -100000000;

    /**
     * Typ
     */
    protected int type = VIRTUAL_ACTUAL_POWER;

    /**
     * ermittelt aus den Sensorwerten die Statistischen Werte
     *
     * @param sensorValues Sensorwerte
     */
    @Override
    public void processValues(Set<SensorValue> sensorValues) {

        int count = 0;
        for(SensorValue sensorValue : sensorValues) {

            if(sensorValue instanceof ActualPowerValue) {

                ActualPowerValue actualPowerValue = (ActualPowerValue) sensorValue;
                this.sum += actualPowerValue.getActualPower();
                count++;

                if(this.min == -100000000 || actualPowerValue.getActualPower() < this.min) {

                    this.min = actualPowerValue.getActualPower();
                }
                if(this.max == -100000000 || actualPowerValue.getActualPower() > this.max) {

                    this.max = actualPowerValue.getActualPower();
                }
            }
        }

        this.average = this.sum / count;
    }

    /**
     * gibt den Mittelwert zurück
     *
     * @return Mittelwert
     */
    public double getAverage() {
        return average;
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
     * gibt den Minimalwert zurück
     *
     * @return Minimalwert
     */
    public double getMin() {
        return min;
    }

    /**
     * gibt den Maximalwert zurück
     *
     * @return Maximalwert
     */
    public double getMax() {
        return max;
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
        if(this.average > 1000.0) {

            return format2.format(this.average / 1000) + " KW";
        }
        return format.format(this.average) + " W";
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayMinValue() {

        DecimalFormat format = new DecimalFormat("#,###,###,##0.0");
        DecimalFormat format2 = new DecimalFormat("#,###,###,##0.00");
        if(this.min > 1000.0) {

            return format2.format(this.min / 1000) + " KWh";
        }
        return format.format(this.min) + " Wh";
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayMaxValue() {

        DecimalFormat format = new DecimalFormat("#,###,###,##0.0");
        DecimalFormat format2 = new DecimalFormat("#,###,###,##0.00");
        if(this.max > 1000.0) {

            return format2.format(this.max / 1000) + " KWh";
        }
        return format.format(this.max) + " Wh";
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

            return format2.format(this.sum / 1000) + " KWh";
        }
        return format.format(this.sum) + " Wh";
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
