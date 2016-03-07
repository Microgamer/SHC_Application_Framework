package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Lichtstärke
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LightIntensityValue extends AbstractSensorValue {

    /**
     * Lichtstärke (0 - 1023 Digits)
     */
    protected int lightIntensity = 0;

    /**
     * Typ
     */
    protected int type = LIGHT_INTENSITY;

    /**
     * gibt die Lichtstärke zurück
     *
     * @return Lichtstärke
     */
    public int getLightIntensity() {
        return lightIntensity;
    }

    /**
     * setzt die Lichtstärke
     *
     * @param lightIntensity Lichtstärke
     */
    public void setLightIntensity(int lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    /**
     * fügt die Lichtstärke hinzu
     *
     * @param lightIntensity Lichtstärke
     */
    public void pushLightIntensity(int lightIntensity) {

        this.lightIntensity = lightIntensity;
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
        return format.format(this.lightIntensity * 100 / 1023) + " %";
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
