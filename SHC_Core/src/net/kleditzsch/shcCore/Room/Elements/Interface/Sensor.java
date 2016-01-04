package net.kleditzsch.shcCore.Room.Elements.Interface;

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
    boolean isDataRecordingEnabled();

    /**
     * aktiviert/deaktiviert die Datenaufzeichnung
     *
     * @param enabled aktiviert/deaktiviert
     */
    void setDataRecordingEnabled(boolean enabled);

    /**
     * gibt die Zeit des Letzten Kontaktes zum Sensor zurück
     *
     * @return Zeit
     */
    LocalDateTime getLastContactTime();

    /**
     * setzt die Zeit des letzten Kontaktes zum Sensor auf die übergebene Zeit
     *
     * @param ldt Zeit
     */
    void setLastContactTime(LocalDateTime ldt);

    /**
     * setzt die Zeit des letzten Kontaktes zum Sensor auf die aktuelle Zeit
     */
    void setLastContactTimeNow();

    /**
     * gibt die Identifizierung zurück
     *
     * @return Identifizierung
     */
    String getIdentifier();

    /**
     * setzt die Identifizierung
     *
     * @param identifier Identifizierung
     */
    void setIdentifier(String identifier);
}
