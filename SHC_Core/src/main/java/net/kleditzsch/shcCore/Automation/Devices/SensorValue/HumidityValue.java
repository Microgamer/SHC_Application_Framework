package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Luftfeuchte
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class HumidityValue extends AbstractSensorValue {

    /**
     * Luftfeuchte (0 - 1023 Digits)
     */
    public int humidity = 0;

    /**
     * gibt die Luftfeuchte zurück
     *
     * @return Luftfeuchte
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * setzt die Luftfeuchte
     *
     * @param humidity Luftfeuchte
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**
     * fügt die Luftfeuchte hinzu
     *
     * @param humidity Luftfeuchte
     */
    public void pushHumidity(int humidity) {

        this.humidity = humidity;
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
        return format.format(this.humidity * 100 / 1023) + " %";
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return HUMIDITY;
    }
}
