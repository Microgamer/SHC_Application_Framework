package net.kleditzsch.shcCore.Room.Elemnets.Interface;

import java.time.LocalDateTime;

/**
 * Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Sensor {

    /**
     * gibt an ob die Datenaufzeichnung aktiviert/deaktiviert ist
     *
     * @return true wenn aktiv
     */
    public boolean isDataRecordingEnabled();

    /**
     * aktiviert/deaktiviert die Datenaufzeichnung
     *
     * @param enabled aktiviert/deaktiviert
     */
    public void setDateRecordingEnabled(boolean enabled);

    /**
     * gibt die Zeit des Letzten Kontaktes zum Sensor zurück
     *
     * @return Zeit
     */
    public LocalDateTime getLastContactTime();

    /**
     * setzt die Zeit des letzten Kontaktes zum Sensor auf die übergebene Zeit
     *
     * @param ldt Zeit
     */
    public void setLastContactTime(LocalDateTime ldt);

    /**
     * setzt die Zeit des letzten Kontaktes zum Sensor auf die aktuelle Zeit
     */
    public void setLastContactTimeNow();
}
