package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractVirtualSensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * Virtuelle Lichtstärke
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualLightIntensityValue extends AbstractVirtualSensorValue {

    /**
     * Statistische Werte
     */
    protected double average = 0.0;

    /**
     * Typ
     */
    protected int type = VIRTUAL_LIGHT_INTENSITY;

    /**
     * ermittelt aus den Sensorwerten die Statistischen Werte
     *
     * @param sensorValues Sensorwerte
     */
    @Override
    public void processValues(Set<SensorValue> sensorValues) {

        int count = 0;
        double sum = 0;
        for(SensorValue sensorValue : sensorValues) {

            if(sensorValue instanceof LightIntensityValue) {

                LightIntensityValue lightIntensityValue = (LightIntensityValue) sensorValue;
                sum += lightIntensityValue.getLightIntensity();
                count++;
            }
        }

        this.average = sum / count;
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
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {

        DecimalFormat format = new DecimalFormat("#,###,###,##0.0");
        return format.format(this.average * 100 / 1023) + " %";
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
        return null;
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
