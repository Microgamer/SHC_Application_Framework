package net.kleditzsch.shcCore.Room.Elements.Interface;

import java.util.List;

/**
 * Schnittstelle Virtueller Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface VirtualSensor extends Sensor {

    /**
     * fügt einen neuen Sensor hinzu
     *
     * @param sensor Sensor
     * @return true bei Erfolg
     */
    boolean addSensor(Sensor sensor);

    /**
     * entfernt einen Sensor
     *
     * @param sensor Sensor
     * @return true bei Erfolg
     */
    boolean removeSensor(Sensor sensor);

    /**
     * prüft ob ein Sensor enthalten ist
     *
     * @param sensor Sensor
     * @return true bei Erfolg
     */
    boolean containsSensor(Sensor sensor);

    /**
     * gibt eine Liste mit allen bekannten Sensoren zurück
     *
     * @return Sensorliste
     */
    List<Sensor> getSensors();

    /**
     * berechnet die Statistischen Werte aus den einzelnen Sensorwerten
     */
    void processSensorData();
}
