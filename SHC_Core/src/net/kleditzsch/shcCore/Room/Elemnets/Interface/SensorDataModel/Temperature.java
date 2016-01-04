package net.kleditzsch.shcCore.Room.Elemnets.Interface.SensorDataModel;

/**
 * Schnittstelle Sensorwert Temperatur
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Temperature {

    /**
     * gibt die Temperatur zurück
     *
     * @return Temperatur
     */
    double getTemperature();

    /**
     * setzt die Temperatur
     *
     * @param temerature Termeratur
     */
    void setTemerature(double temerature);

    /**
     * gibt das Offset zurück
     *
     * @return Offset
     */
    double getTemperatureOffset();

    /**
     * setzt das Offset
     *
     * @param offset Offset
     */
    void setTemperatureOffset(double offset);
}
