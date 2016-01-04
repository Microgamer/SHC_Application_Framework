package net.kleditzsch.shcCore.Room.Elemnets.Elements;

import net.kleditzsch.shcCore.Room.Elemnets.Abstract.AbstractSensor;
import net.kleditzsch.shcCore.Room.Elemnets.Interface.SensorDataModel.Temperature;

import java.time.LocalDateTime;

/**
 * DS18X20
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractDS18X20 extends AbstractSensor implements Temperature{

    /**
     * Sensorwerte
     */
    protected double temerature;

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
     * setzt die Sensorwerte
     *
     * @param temerature Temperatur
     */
    public void pushValues(double temerature) {

        this.temerature = temerature;
        this.lastContactTime = LocalDateTime.now();
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return DS18X20;
    }
}
