package net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel;

/**
 * Schnittstelle Sensorwert Standorthöhe
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Altitude {

    /**
     * gibt die Standorthöhe zurück
     *
     * @return Standorthöhe
     */
    double getAltitude();

    /**
     * setzt die Standorthöhe
     *
     * @param altitude Standorthöhe
     */
    void setAltitude(double altitude);
}
