package net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel;

/**
 * Schnittstelle Sensorwert Entfernung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Distance {

    /**
     * gibt die Entfernung zurück
     *
     * @return Entfernung
     */
    double getDistance();

    /**
     * setzt die Entfernung
     *
     * @param distance Entfernung
     */
    void setDistance(double distance);

    /**
     * gibt das Offset zurück
     *
     * @return Offset
     */
    double getDistanceOffset();

    /**
     * setzt das Offset
     *
     * @param offset Offset
     */
    void setDistanceOffset(double offset);
}
