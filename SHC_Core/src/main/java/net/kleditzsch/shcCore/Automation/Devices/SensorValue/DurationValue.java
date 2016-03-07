package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.Util.TimeUtil;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.time.LocalDateTime;

/**
 * Laufzeit
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DurationValue extends AbstractSensorValue {

    /**
     * Laufzeit in Sekunden
     */
    protected long duration = 0;

    /**
     * Typ
     */
    protected int type = DURATION;

    /**
     * gibt die Laufzeit zurück
     *
     * @return Laufzeit
     */
    public long getDuration() {
        return duration;
    }

    /**
     * setzt die Laufzeit
     *
     * @param duration Laufzeit
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * fügt die Laufzeit hinzu
     *
     * @param duration Laufzeit
     */
    public void pushDuration(long duration) {
        this.duration += duration;
        this.lastPushTime = LocalDateTime.now();
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {
        return TimeUtil.formatSeconds(this.duration);
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return type;
    }
}
