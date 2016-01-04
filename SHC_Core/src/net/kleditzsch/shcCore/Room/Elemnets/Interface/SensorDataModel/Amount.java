package net.kleditzsch.shcCore.Room.Elemnets.Interface.SensorDataModel;

/**
 * Schnittstelle Sensorwert Menge
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Amount {

    /**
     * gibt die Menge zurück
     *
     * @return Menge
     */
    double getAmount();

    /**
     * setzt die Mange
     *
     * @param amount Menge
     */
    void setAmount(double amount);
}
