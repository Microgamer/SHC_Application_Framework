package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.Distance;

import java.time.LocalDateTime;

/**
 * Abstandssensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractHcSr04 extends AbstractSensor implements Distance {

    /**
     * Entfernung
     */
    protected double distance;

    /**
     * Entfernungsoffset
     */
    protected double distanceOffset;

    /**
     * gibt die Entfernung zurück
     *
     * @return Entfernung
     */
    @Override
    public double getDistance() {
        return this.distance;
    }

    /**
     * setzt die Entfernung
     *
     * @param distance Entfernung
     */
    @Override
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * gibt das Offset zurück
     *
     * @return Offset
     */
    @Override
    public double getDistanceOffset() {
        return this.distanceOffset;
    }

    /**
     * setzt das Offset
     *
     * @param offset Offset
     */
    @Override
    public void setDistanceOffset(double offset) {
        this.distanceOffset = offset;
    }

    /**
     * setzt die Sensorwerte
     *
     * @param distance Abstand
     */
    public void pushValues(double distance) {

        this.distance = distance;
        this.lastContactTime = LocalDateTime.now();
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return HC_SR04;
    }
}
