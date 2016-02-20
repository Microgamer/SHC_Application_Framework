package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractVirtualSensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * Virtuelle Temperatur
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualTemperatureValue extends AbstractVirtualSensorValue {

    /**
     * Statistische Werte
     */
    protected double average = 0.0;
    protected double sum = 0.0;
    protected double min = -100000000;
    protected double max = -100000000;

    /**
     * ermittelt aus den Sensorwerten die Statistischen Werte
     *
     * @param sensorValues Sensorwerte
     */
    @Override
    public void processValues(Set<SensorValue> sensorValues) {

        int count = 0;
        for(SensorValue sensorValue : sensorValues) {

            if(sensorValue instanceof TemperatureValue) {

                TemperatureValue temperatureValue = (TemperatureValue) sensorValue;
                this.sum += temperatureValue.getTemperatureWithOffset();
                count++;

                if(this.min == -100000000 || temperatureValue.getTemperatureWithOffset() < this.min) {

                    this.min = temperatureValue.getTemperatureWithOffset();
                }
                if(this.max == -100000000 || temperatureValue.getTemperatureWithOffset() > this.max) {

                    this.max = temperatureValue.getTemperatureWithOffset();
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

        DecimalFormat format = new DecimalFormat("-#,###,###,##0.00");
        return format.format(this.average) + " °C";
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayMinValue() {

        DecimalFormat format = new DecimalFormat("-#,###,###,##0.00");
        return format.format(this.min) + " °C";
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayMaxValue() {

        DecimalFormat format = new DecimalFormat("-#,###,###,##0.00");
        return format.format(this.max) + " °C";
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplaySumValue() {

        DecimalFormat format = new DecimalFormat("-#,###,###,##0.00");
        return format.format(this.sum) + " °C";
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return VIRTUAL_TEMPERATURE;
    }
}
