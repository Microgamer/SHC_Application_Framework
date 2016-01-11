package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.LightIntensity;

import java.time.LocalDateTime;

/**
 * Lichstärke Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractLdr extends AbstractSensor implements LightIntensity {

    /**
     * Lichtstärke
     */
    protected double lightIntensity;

    /**
     * gibt die Lichtstärke zurück
     *
     * @return Lichtstärke
     */
    @Override
    public double getLightIntensity() {
        return this.lightIntensity;
    }

    /**
     * setzt die Lichtstärke
     *
     * @param lightIntenisty Lichtstärke
     */
    @Override
    public void setLightIntenisty(double lightIntenisty) {
        this.lightIntensity = lightIntenisty;
    }

    /**
     * setzt die Sensorwerte
     *
     * @param lightIntensity Lichtstärke
     */
    public void pushValues(double lightIntensity) {

        this.lightIntensity = lightIntensity;
        this.lastContactTime = LocalDateTime.now();
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return LDR;
    }
}
