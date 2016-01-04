package net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel;

/**
 * Schnittstelle Sensorwert Lichtstärke
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface LightIntensity {

    /**
     * gibt die Lichtstärke zurück
     *
     * @return Lichtstärke
     */
    double getLightIntensity();

    /**
     * setzt die Lichtstärke
     *
     * @param lightIntenisty Lichtstärke
     */
    void setLightIntenisty(double lightIntenisty);
}
