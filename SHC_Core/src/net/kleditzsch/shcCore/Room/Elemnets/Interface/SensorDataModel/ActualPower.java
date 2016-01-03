package net.kleditzsch.shcCore.Room.Elemnets.Interface.SensorDataModel;

/**
 * Schnittstelle Sensorwert Momentanverbrauch
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface ActualPower {

    /**
     * gibt den aktuellen Energieverbrauch zurück
     *
     * @return Energieverbrauch in W
     */
    double getActualPower();
}
