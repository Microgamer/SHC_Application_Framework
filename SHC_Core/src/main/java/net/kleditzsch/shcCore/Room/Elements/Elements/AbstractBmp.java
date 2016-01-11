package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.AirPressure;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.Altitude;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.Temperature;

import java.time.LocalDateTime;

/**
 * BMP Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractBmp extends AbstractSensor implements Temperature, AirPressure, Altitude {

    /**
     * Sensorwerte
     */
    protected double airPressure;
    protected double altitude;
    protected double temerature;

    /**
     * Temperatur Offset
     */
    protected double temperatureOffset = 0.0;

    /**
     * gibt den Luftdruck zurück
     *
     * @return Luftdruck
     */
    @Override
    public double getAirPressure() {
        return airPressure;
    }

    /**
     * setzt den Luftdruck
     *
     * @param airPressure Luftdruck
     */
    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }

    /**
     * gibt die Standorthöhe zurück
     *
     * @return Standorthöhe
     */
    @Override
    public double getAltitude() {
        return altitude;
    }

    /**
     * setzt die Standorthöhe
     *
     * @param altitude Standorthöhe
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

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
     * @param airPressure Luftdruck
     * @param altitude Standorthöhe
     * @param temerature Temperatur
     */
    public void pushValues(double airPressure, double altitude, double temerature) {

        this.airPressure = airPressure;
        this.altitude = altitude;
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
        return BMP;
    }
}
