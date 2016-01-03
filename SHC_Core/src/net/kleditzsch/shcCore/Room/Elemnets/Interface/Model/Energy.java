package net.kleditzsch.shcCore.Room.Elemnets.Interface.Model;

/**
 * Schnittstelle Sensorwert Energieverbrauch
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Energy {

    /**
     * gibt den Energieverbrauch zurück
     *
     * @return Energieverbrauch Wh
     */
    double getEnergy();
}
