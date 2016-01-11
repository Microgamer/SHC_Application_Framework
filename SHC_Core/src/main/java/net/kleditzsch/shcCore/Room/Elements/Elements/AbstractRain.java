package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.Moisture;

import java.time.LocalDateTime;

/**
 * Regensensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractRain extends AbstractSensor implements Moisture {

    /**
     * Feuchtigkeit
     */
    protected double moisture;

    /**
     * gibt die Feuchtigkeit zurück
     *
     * @return Feuchtigkeit
     */
    @Override
    public double getMoisture() {
        return this.moisture;
    }

    /**
     * setzt die Feuchtigkeit
     *
     * @param moisture Feuchtigkeit
     */
    @Override
    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }

    /**
     * setzt die Sensorwerte
     *
     * @param moisture Feuchtigkeit
     */
    public void pushValues(double moisture) {

        this.moisture = moisture;
        this.lastContactTime = LocalDateTime.now();
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return RAIN;
    }
}
