package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractVirtualSensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.Sensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.LightIntensity;

/**
 * Virtueller Lichtstärke Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractVirtualLightIntensity extends AbstractVirtualSensor {

    /**
     * Statistische Werte
     */
    protected double min, avg, max, sum;

    /**
     * fügt einen neuen Sensor hinzu
     *
     * @param sensor Sensor
     * @return true bei Erfolg
     */
    @Override
    public boolean addSensor(Sensor sensor) {

        if(sensor instanceof LightIntensity) {

            return super.addSensor(sensor);
        }
        return false;
    }

    /**
     * berechnet die Statistischen Werte aus den einzelnen Sensorwerten
     */
    @Override
    public void processSensorData() {

        this.min = 0;
        this.max = 0;
        this.avg = 0;
        this.sum = 0;
        int count = 0;
        boolean firstLoop = true;

        for(Sensor object : sensors) {

            LightIntensity sensor = (LightIntensity) object;
            double value = sensor.getLightIntensity();

            //Minimalwert
            if(value < this.min || firstLoop) {

                this.min = value;
            }

            //Mittelwert + Summe
            this.sum += value;
            count++;

            //Maximalwert
            if(value > this.max || firstLoop) {

                this.max = value;
            }
            firstLoop = false;
        }

        //Mittelwert
        this.avg = (this.sum / count);
    }

    /**
     * gibt den Minimalwert
     *
     * @return Minimalwert
     */
    public double getMinLightIntensity() {
        return min;
    }

    /**
     * gibt den Mittelwert zurück
     *
     * @return Mittelwert
     */
    public double getAvgLightIntensity() {
        return avg;
    }

    /**
     * gibt den Maximalwert zurück
     *
     * @return Maximalwert
     */
    public double getMaxLightIntensity() {
        return max;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return VIRTUAL_LIGHT_INTENSITY;
    }
}