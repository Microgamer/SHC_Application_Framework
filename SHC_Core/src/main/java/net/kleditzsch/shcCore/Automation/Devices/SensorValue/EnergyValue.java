package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Energieverbrauch
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class EnergyValue extends AbstractSensorValue {

    /**
     * Energieverbrauch in Wh
     */
    protected double energy = 0.0;

    /**
     * gibt den Energieverbrauch zurück
     *
     * @return Energieverbrauch
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * setzt den Energieverbrauch
     *
     * @param energy Energieverbrauch
     */
    public void setEnergy(double energy) {
        this.energy = energy;
    }

    /**
     * fügt den Energieverbrauch hinzu
     *
     * @param energy Energieverbrauch
     */
    public void pushEnergy(double energy) {

        this.energy = energy;
        this.lastPushTime = LocalDateTime.now();
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {

        DecimalFormat format = new DecimalFormat("#,###,###,##0.0");
        DecimalFormat format2 = new DecimalFormat("#,###,###,##0.00");
        if(this.energy > 1000.0) {

            return format2.format(this.energy / 1000) + " KWh";
        }
        return format.format(this.energy) + " Wh";
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return ENERGY;
    }
}
