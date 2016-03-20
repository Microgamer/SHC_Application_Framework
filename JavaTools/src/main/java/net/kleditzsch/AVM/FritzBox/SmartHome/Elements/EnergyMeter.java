package net.kleditzsch.AVM.FritzBox.SmartHome.Elements;

/**
 * AVM Energiemesser
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class EnergyMeter {

    /**
     * Energiedaten
     */
    protected long power = 0;
    protected long energy = 0;

    /**
     * gibt den aktuellen Verbrauch zurück
     *
     * @return aktueller Verbrauch (in 0,01W)
     */
    public long getPower() {
        return power;
    }

    /**
     * setzt den aktuellen Verbrauch
     *
     * @param power aktueller Verbrauch (in 0,01W)
     */
    public void setPower(long power) {
        this.power = power;
    }

    /**
     * gibt den Energieverbrauch zurück
     *
     * @return Energieverbrauch (in Wh)
     */
    public long getEnergy() {
        return energy;
    }

    /**
     * setzt den Energieverbrauch
     *
     * @param energy Energieverbrauch (in Wh)
     */
    public void setEnergy(long energy) {
        this.energy = energy;
    }
}
