package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractDoubleSwitchable;

/**
 * AVM Steckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AvmSocket extends AbstractDoubleSwitchable {

    /**
     * Identifizierung
     */
    protected String identifier;

    /**
     * Sensorwerte der Steckdose
     */
    protected String tempSensorHash = "";
    protected String powerSensorHash = "";
    protected String energySensorHash = "";

    /**
     * Typ
     */
    protected int type = AVM_SOCKET;

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
        this.identifier = identifier.replace(" ", "");
    }

    /**
     * gibt den Hash des Temperatur Sensorwertes zurück
     *
     * @return Hash vom Sensorwert
     */
    public String getTempSensorHash() {
        return tempSensorHash;
    }

    /**
     * setzt den Hash des Temperatur Sensorwertes
     *
     * @param tempSensorHash Hash vom Sensorwert
     */
    public void setTempSensorHash(String tempSensorHash) {
        this.tempSensorHash = tempSensorHash;
    }

    /**
     * gibt den Hash des Verbrauchs Sensorwertes zurück
     *
     * @return Hash vom Sensorwert
     */
    public String getPowerSensorHash() {
        return powerSensorHash;
    }

    /**
     * setzt den Hash des Verbrauchs Sensorwertes
     *
     * @param powerSensorHash Hash vom Sensorwert
     */
    public void setPowerSensorHash(String powerSensorHash) {
        this.powerSensorHash = powerSensorHash;
    }

    /**
     * gibt den Hash des Energie Sensorwertes zurück
     *
     * @return Hash vom Sensorwert
     */
    public String getEnergySensorHash() {
        return energySensorHash;
    }

    /**
     * setzt den Hash des Energie Sensorwertes
     *
     * @param energySensorHash Hash vom Sensorwert
     */
    public void setEnergySensorHash(String energySensorHash) {
        this.energySensorHash = energySensorHash;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return type;
    }
}
