package net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel;

/**
 * Schnittstelle Sensorwert Lúftdruck
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface AirPressure {

    /**
     * gibt den Luftdruck zurück
     *
     * @return Luftdruck
     */
    double getAirPressure();

    /**
     * setzt den Luftdruck
     *
     * @param airPressure Luftdruck
     */
    void setAirPressure(double airPressure);
}
