package net.kleditzsch.shcCore.Room.Elements.Abstract;

import net.kleditzsch.shcCore.Room.Elements.Interface.Sensor;

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
    protected boolean dataRecordingEnabled;

    /**
     * Zeitpunkt letzter Kontakt zum Sensor
     */
    protected LocalDateTime lastContactTime;

    /**
     * Identifizierung für das Puschen von Sensorwerten
     */
    protected String identifier;

    /**
     * gibt an ob die Datenaufzeichnung aktiviert/deaktiviert ist
     *
     * @return true wenn aktiv
     */
    public boolean isDataRecordingEnabled() {
        return dataRecordingEnabled;
    }

    /**
     * aktiviert/deaktiviert die Datenaufzeichnung
     *
     * @param enabled aktiviert/deaktiviert
     */
    public void setDataRecordingEnabled(boolean enabled) {
        dataRecordingEnabled = enabled;
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

    /**
     * gibt die Identifizierung zurück
     *
     * @return Identifizierung
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * setzt die Identifizierung
     *
     * @param identifier Identifizierung
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
