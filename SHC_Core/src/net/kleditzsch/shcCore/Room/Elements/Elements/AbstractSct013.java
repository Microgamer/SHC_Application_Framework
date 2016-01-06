package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.ActualPower;

import java.time.LocalDateTime;

/**
 * Energiesensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractSct013 extends AbstractSensor implements ActualPower {

    /**
     * Aktueller Energieverbrauch
     */
    protected double actualPower;

    /**
     * gibt den aktuellen Energieverbrauch zurück
     *
     * @return Energieverbrauch in W
     */
    @Override
    public double getActualPower() {
        return this.actualPower;
    }

    /**
     * setzt den den aktuellen Energieverbrauch
     *
     * @param actualPower Energieverbrauch in W
     */
    @Override
    public void setActualPower(double actualPower) {
        this.actualPower = actualPower;
    }

    public void pushValues(double actualPower) {

        this.actualPower = actualPower;
        this.lastContactTime = LocalDateTime.now();
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return SCT_013;
    }
}
