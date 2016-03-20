package net.kleditzsch.AVM.FritzBox.SmartHome.Elements;

/**
 * AVM Temperatur Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class TemperatureSensor {

    /**
     * Sensorwerte
     */
    protected double themperature = 0.0;
    protected double offset = 0;

    /**
     * gibt die Temperatur zurück
     *
     * @return Temperatur
     */
    public double getThemperature() {
        return themperature;
    }

    /**
     * setzt die Temperatur
     *
     * @param themperature Temperatur
     */
    public void setThemperature(double themperature) {
        this.themperature = themperature;
    }

    /**
     * gibt das Temperatur Offset zurück
     *
     * @return Temperatur Offset
     */
    public double getOffset() {
        return offset;
    }

    /**
     * setzt das Temperatur Offset
     *
     * @param offset Temperatur Offset
     */
    public void setOffset(double offset) {
        this.offset = offset;
    }
}
