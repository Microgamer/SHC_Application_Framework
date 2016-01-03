package net.kleditzsch.shcCore.Room.Elemnets.Elements;

import net.kleditzsch.shcCore.Room.Elemnets.Abstract.AbstractSwitchable;
import net.kleditzsch.shcCore.Room.Elemnets.Interface.SensorDataModel.Temperature;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * AVM Steckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AbstractAvmSocket extends AbstractSwitchable implements Temperature {

    /**
     * Identifizierung der Steckdose (AIN oder MAC)
     */
    protected String identifier;

    /**
     * Temperatur
     */
    protected double temperature;

    /**
     * gibt die Identifizierung der Steckdose zurück
     *
     * @return Identifizierung
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * setzt die Identifizierung der Steckdose
     *
     * @param identifier Identifizierung
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * gibt die Temperatur zurück
     *
     * @return Temperatur
     */
    @Override
    public double getTemperature() {
        return temperature;
    }

    /**
     * setzt die Temperatur
     *
     * @param temperature Temperatur
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    @Override
    public void triggerOn() {

        lastToggleTime = LocalDateTime.now();
        state = ON;
    }

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void triggerOff() {

        computeOperatingTime();
        lastToggleTime = LocalDateTime.now();
        state = OFF;
    }

    /**
     * berechnet beim ausschalten die Betriebszeit und den Energieverbrauch
     */
    @Override
    protected void computeOperatingTime() {

        if(lastToggleTime != null) {

            //Betriebszeit ermitteln
            Duration duration = Duration.between(lastToggleTime, LocalDateTime.now());
            long operatingSeconds = duration.toMillis() / 1000;

            //gesampte Betriebszeit aktualisieren
            this.operatingSeconds += operatingSeconds;
        }
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return AVM_SOCKET;
    }
}