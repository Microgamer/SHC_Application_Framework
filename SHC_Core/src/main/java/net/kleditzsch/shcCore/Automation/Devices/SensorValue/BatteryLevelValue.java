package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.time.LocalDateTime;

/**
 * Batterie Ladestand
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class BatteryLevelValue extends AbstractSensorValue {

    /**
     * Ladezustand in %
     */
    protected int batteryLevel = 0;

    /**
     * gibt den Ladezustand zurück
     *
     * @return Ladezustand
     */
    public int getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * setzt den Ladezustand
     *
     * @param batteryLevel Ladezustand
     */
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    /**
     * fügt den Ladezustand hinzu
     *
     * @param batteryLevel Ladezustand
     */
    public void pushBatteryLevel(int batteryLevel) {

        this.batteryLevel = batteryLevel;
        this.lastPushTime = LocalDateTime.now();
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {
        return this.batteryLevel + "%";
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return BATTERIE_LEVEL;
    }
}
