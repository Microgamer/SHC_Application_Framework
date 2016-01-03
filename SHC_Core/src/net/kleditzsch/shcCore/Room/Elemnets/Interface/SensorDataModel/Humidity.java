package net.kleditzsch.shcCore.Room.Elemnets.Interface.SensorDataModel;

/**
 * Schnittstelle Sensorwert Luftfeuchtigkeit
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Humidity {

    /**
     * gibt die Luftfeuchtigkeit zurück
     *
     * @return Luftfeuchtigkeit
     */
    double getHumidity();
}
