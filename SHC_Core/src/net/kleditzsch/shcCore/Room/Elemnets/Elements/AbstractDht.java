package net.kleditzsch.shcCore.Room.Elemnets.Elements;

import net.kleditzsch.shcCore.Room.Elemnets.Abstract.AbstractSensor;
import net.kleditzsch.shcCore.Room.Elemnets.Interface.SensorDataModel.Humidity;
import net.kleditzsch.shcCore.Room.Elemnets.Interface.SensorDataModel.Temperature;

import java.time.LocalDateTime;

/**
 * DHT
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractDht extends AbstractSensor implements Temperature, Humidity {

    /**
     * Sensorwerte
     */
    protected double temerature;
    protected double humidity;

    /**
     * Temperatur Offset
     */
    protected double temperatureOffset = 0.0;

    /**
     * gibt die Temperatur zurück
     *
     * @return Temperatur
     */
    @Override
    public double getTemperature() {
        return temerature;
    }

    /**
     * setzt die Temperatur
     *
     * @param temerature Termeratur
     */
    public void setTemerature(double temerature) {
        this.temerature = temerature;
    }

    /**
     * gibt das Offset zurück
     *
     * @return Offset
     */
    public double getTemperatureOffset() {
        return this.temperatureOffset;
    }

    /**
     * setzt das Offset
     *
     * @param offset Offset
     */
    public void setTemperatureOffset(double offset) {
        this.temperatureOffset = offset;
    }

    /**
     * gibt die Luftfeuchtigkeit zurück
     *
     * @return Luftfeuchtigkeit
     */
    @Override
    public double getHumidity() {
        return this.humidity;
    }

    /**
     * setzt die Luftfeuchtigkeit
     *
     * @param humidity Luftfeuchtigkeit
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     * setzt die Sensorwerte
     *
     * @param temerature Temperatur
     * @param humidity Luftfeuchtigkeit
     */
    public void pushValues(double temerature, double humidity) {

        this.temerature = temerature;
        this.humidity = humidity;
        this.lastContactTime = LocalDateTime.now();
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return DHT;
    }
}
