package net.kleditzsch.shcCore.Room.Elemnets.Abstract;

import net.kleditzsch.shcCore.Room.Elemnets.Interface.Sensor;

import java.time.LocalDateTime;

/**
 * Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractSensor extends AbstractRoomElement implements Sensor {

    /**
     * Datenaufzeichnung aktiviert?
     */
    protected boolean dataRecording;

    /**
     * Zeitpunkt letzter Kontakt zum Sensor
     */
    protected LocalDateTime lastContactTime;

    /**
     * gibt an ob die Datenaufzeichnung aktiviert/deaktiviert ist
     *
     * @return true wenn aktiv
     */
    public boolean isDataRecordingEnabled() {
        return dataRecording;
    }

    /**
     * aktiviert/deaktiviert die Datenaufzeichnung
     *
     * @param enabled aktiviert/deaktiviert
     */
    public void setDateRecordingEnabled(boolean enabled) {
        dataRecording = enabled;
    }

    /**
     * gibt die Zeit des Letzten Kontaktes zum Sensor zurück
     *
     * @return Zeit
     */
    public LocalDateTime getLastContactTime() {
        return lastContactTime;
    }

    /**
     * setzt die Zeit des letzten Kontaktes zum Sensor auf die übergebene Zeit
     *
     * @param ldt Zeit
     */
    public void setLastContactTime(LocalDateTime ldt) {
        lastContactTime = ldt;
    }

    /**
     * setzt die Zeit des letzten Kontaktes zum Sensor auf die aktuelle Zeit
     */
    public void setLastContactTimeNow() {

        lastContactTime = LocalDateTime.now();
    }
}
