package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractVirtualSensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.Sensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.Amount;

/**
 * Virtueller Mengenmesser
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractVirtualAmount extends AbstractVirtualSensor {

    /**
     * Statistische Werte
     */
    protected double sum;

    /**
     * fügt einen neuen Sensor hinzu
     *
     * @param sensor Sensor
     * @return true bei Erfolg
     */
    @Override
    public boolean addSensor(Sensor sensor) {

        if(sensor instanceof Amount) {

            return super.addSensor(sensor);
        }
        return false;
    }

    /**
     * berechnet die Statistischen Werte aus den einzelnen Sensorwerten
     */
    @Override
    public void processSensorData() {

        this.sum = 0;

        for(Sensor object : sensors) {

            Amount sensor = (Amount) object;
            double value = sensor.getAmount();

            //Summe
            this.sum += value;
        }
    }

    /**
     * gibt die Summer zurück
     *
     * @return Summe
     */
    public double getSumAmount() {
        return sum;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return VIRTUAL_AMOUNT;
    }
}
