package net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel;

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

    /**
     * setzt den den aktuellen Energieverbrauch
     *
     * @param actualPower Energieverbrauch in W
     */
    void setActualPower(double actualPower);
}
