package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractVirtualSensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * Virtueller Energiesensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualEnergyValue extends AbstractVirtualSensorValue {

    /**
     * Statistische Werte
     */
    protected double average = 0.0;
    protected double sum = 0.0;

    /**
     * Typ
     */
    protected int type = VIRTUAL_ENERGY;

    /**
     * ermittelt aus den Sensorwerten die Statistischen Werte
     *
     * @param sensorValues Sensorwerte
     */
    @Override
    public void processValues(Set<SensorValue> sensorValues) {

        int count = 0;
        for(SensorValue sensorValue : sensorValues) {

            if(sensorValue instanceof EnergyValue) {

                EnergyValue energyValue = (EnergyValue) sensorValue;
                this.sum += energyValue.getEnergy();
                count++;
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
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {

        DecimalFormat format = new DecimalFormat("#,###,###,##0.0");
        DecimalFormat format2 = new DecimalFormat("#,###,###,##0.00");
        if(this.average > 1000.0) {

            return format2.format(this.average / 1000) + " KWh";
        }
        return format.format(this.average) + " Wh";
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
