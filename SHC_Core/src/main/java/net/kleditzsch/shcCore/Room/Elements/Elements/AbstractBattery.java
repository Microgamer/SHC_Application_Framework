package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSensor;

import java.time.LocalDateTime;

/**
 * Akku
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractBattery extends AbstractSensor {

    /**
     * Sensorwerte
     */
    protected double batteryLevel;
    protected boolean isChargeing;

    /**
     * gibt den Ladezustand des Akkus zurück
     *
     * @return Ladezustand
     */
    public double getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * setzt den Ladezustand des Akkus
     *
     * @param batteryLevel Ladezustand
     */
    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    /**
     * gibt an ob der Auuk gerade geladen wird
     *
     * @return true wenn laden aktiv
     */
    public boolean isChargeing() {
        return isChargeing;
    }

    /**
     * aktiviert/deaktiviert das Laden
     *
     * @param chargeing aktiviert/deaktiviert
     */
    public void setChargeing(boolean chargeing) {
        isChargeing = chargeing;
    }

    /**
     * setzt die Sensorwerte
     *
     * @param batteryLevel Ladezustand
     * @param isChargeing wird der Akku gerade geladen?
     */
    public void pushValues(double batteryLevel, boolean isChargeing) {

        this.batteryLevel = batteryLevel;
        this.isChargeing = isChargeing;
        this.lastContactTime = LocalDateTime.now();
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return BATTERY;
    }
}
