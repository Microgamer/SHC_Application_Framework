package net.kleditzsch.shcCore.Room.Elemnets.Interface.SensorDataModel;

/**
 * Schnittstelle Sensorwert Feuchtigkeit
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Moisture {

    /**
     * gibt die Feuchtigkeit zurück
     *
     * @return Feuchtigkeit
     */
    double getMoisture();

    /**
     * setzt die Feuchtigkeit
     *
     * @param moisture Feuchtigkeit
     */
    void  setMoisture(double moisture);
}
