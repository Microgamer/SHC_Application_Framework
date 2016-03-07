package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Feuchtigkeit
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class MoistureValue extends AbstractSensorValue {

    /**
     * Feuchtigkeit
     */
    protected int moisture = 0;

    /**
     * Typ
     */
    protected int type = MOISTURE;

    /**
     * gibt die Feuchtigkeit zurück
     *
     * @return Feuchtigkeit
     */
    public int getMoisture() {
        return moisture;
    }

    /**
     * setzt die Feuchtigkeit
     *
     * @param moisture Feuchtigkeit
     */
    public void setMoisture(int moisture) {
        this.moisture = moisture;
    }

    /**
     * fügt die Feuchtigkeit hinzu
     *
     * @param moisture Feuchtigkeit
     */
    public void pushMoisture(int moisture) {

        this.moisture = moisture;
        this.lastPushTime = LocalDateTime.now();
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {

        DecimalFormat format = new DecimalFormat("#,###,###,##0.0");
        return format.format(this.moisture * 100 / 1023) + " %";
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
