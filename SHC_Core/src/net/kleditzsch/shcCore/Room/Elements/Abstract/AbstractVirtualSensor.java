package net.kleditzsch.shcCore.Room.Elements.Abstract;

import net.kleditzsch.shcCore.Room.Elements.Interface.Sensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.VirtualSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Standard Virtueller Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractVirtualSensor extends AbstractSensor implements VirtualSensor {

    /**
     * Liste mit allen Sensoren
     */
    protected List<Sensor> sensors = new ArrayList<>();

    /**
     * fügt einen neuen Sensor hinzu
     *
     * @param sensor Sensor
     * @return true bei Erfolg
     */
    @Override
    public boolean addSensor(Sensor sensor) {

        if(!(sensor instanceof VirtualSensor)) {

            this.sensors.add(sensor);
            return true;
        }
        return false;
    }

    /**
     * entfernt einen Sensor
     *
     * @param sensor Sensor
     * @return true bei Erfolg
     */
    @Override
    public boolean removeSensor(Sensor sensor) {

        if(containsSensor(sensor)) {

            this.sensors.remove(sensor);
            return true;
        }
        return false;
    }

    /**
     * prüft ob ein Sensor enthalten ist
     *
     * @param sensor Sensor
     * @return true bei Erfolg
     */
    @Override
    public boolean containsSensor(Sensor sensor) {
        return this.sensors.contains(sensor);
    }

    /**
     * gibt eine Liste mit allen bekannten Sensoren zurück
     *
     * @return Sensorliste
     */
    @Override
    public List<Sensor> getSensors() {
        return this.sensors;
    }
}
