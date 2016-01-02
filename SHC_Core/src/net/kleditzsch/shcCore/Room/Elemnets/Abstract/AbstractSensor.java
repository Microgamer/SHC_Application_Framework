package net.kleditzsch.shcCore.Room.Elemnets.Abstract;

import net.kleditzsch.Util.DatabaseDateTimeUtil;
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
     * gibt an ob die Datenaufzeichnung aktiviert/deaktiviert ist
     *
     * @return true wenn aktiv
     */
    public boolean isDataRecordingEnabled() {

        if(data.containsKey("dataRecording") && ((String) data.get("dataRecording")).equals("true")) {

            return true;
        }
        return false;
    }

    /**
     * aktiviert/deaktiviert die Datenaufzeichnung
     *
     * @param enabled aktiviert/deaktiviert
     */
    public void setDateRecordingEnabled(boolean enabled) {

        if(enabled) {

            data.put("dataRecording", "true");
        } else {

            data.put("dataRecording", "false");
        }
    }

    /**
     * gibt die Zeit des Letzten Kontaktes zum Sensor zurück
     *
     * @return Zeit
     */
    public LocalDateTime getLastContactTime() {

        if(data.containsKey("dataRecording")) {

            return DatabaseDateTimeUtil.parseDateTimeFromDatabase((String) data.get("dataRecording"));
        }
        return null;
    }

    /**
     * setzt die Zeit des letzten Kontaktes zum Sensor auf die übergebene Zeit
     *
     * @param ldt Zeit
     */
    public void setLastContactTime(LocalDateTime ldt) {

        data.put("dataRecording", DatabaseDateTimeUtil.getDatabaseDateTimeStr(ldt));
    }

    /**
     * setzt die Zeit des letzten Kontaktes zum Sensor auf die aktuelle Zeit
     */
    public void setLastContactTimeNow() {

        data.put("dataRecording", DatabaseDateTimeUtil.getDatabaseDateTimeStr(LocalDateTime.now()));
    }
}
