package net.kleditzsch.AVM.FritzBox.SmartHome;

import net.kleditzsch.AVM.FritzBox.SmartHome.Elements.EnergyMeter;
import net.kleditzsch.AVM.FritzBox.SmartHome.Elements.Switch;
import net.kleditzsch.AVM.FritzBox.SmartHome.Elements.TemperatureSensor;

/**
 * Fritz!Box SmartHome Geräte
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SmarthomeDevice {

    /**
     * Allgemeine Daten eines SmartHome Gerätes
     */
    protected String identifier = "";
    protected String id = "";
    protected int functionBitmask = 0;
    protected String firmwareVersion = "";
    protected String manufacturer = "";
    protected String productName = "";
    protected boolean present = false;
    protected String name = "";

    /**
     * Automatisierungsfunktionen
     */
    protected EnergyMeter energyMeter;
    protected TemperatureSensor temperatureSensor;
    protected Switch aSwitch;

    /**
     * gibt den Identifizierer zurück
     *
     * @return Identifizierer
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * setzt den Identifizierer
     *
     * @param identifier Identifizierer
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * gibt die ID zurück
     *
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * setzt die ID
     *
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * gibt die Funktions Bitmaske zurück
     *
     * @return Funktions Bitmaske
     */
    public int getFunctionBitmask() {
        return functionBitmask;
    }

    /**
     * setzt die Funktions Bitmaske
     *
     * @param functionBitmask Funktions Bitmaske
     */
    public void setFunctionBitmask(int functionBitmask) {
        this.functionBitmask = functionBitmask;
    }

    /**
     * gibt die Firmware Version zurück
     *
     * @return Firmware Version
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * setzt die Firmware Version
     *
     * @param firmwareVersion Firmware Version
     */
    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    /**
     * gibt den Hersteller der Gerätes zurück
     *
     * @return Hersteller der Gerätes
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * setzt den Hersteller der Gerätes
     *
     * @param manufacturer Hersteller der Gerätes
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * gibt den Produktnamen zurück
     *
     * @return Produktnamen
     */
    public String getProductName() {
        return productName;
    }

    /**
     * setzt den Produktnamen
     *
     * @param productName Produktnamen
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * gibt an ob das Gerät erreichbar ist
     *
     * @return erreichbar/nicht erreichbar
     */
    public boolean isPresent() {
        return present;
    }

    /**
     * setzt der Gerät als erreichbar/nicht erreichbar
     *
     * @param present erreichbar/nicht erreichbar
     */
    public void setPresent(boolean present) {
        this.present = present;
    }

    /**
     * gibt den Namen des Gerätes zurück
     *
     * @return Name des Gerätes
     */
    public String getName() {
        return name;
    }

    /**
     * setzt den Namen des Gerätes
     *
     * @param name Name des Gerätes
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gibt an ob das Gerät ein DECT Heizkörper Thermostat ist
     *
     * @return ja/nein
     */
    public boolean isCometDectRadiatorThermostat() {

        return (functionBitmask & 64) == 64;
    }

    /**
     * gibt an ob das Gerät ein Energiekostenmesser ist
     *
     * @return  ja/nein
     */
    public boolean isEnergyMeter() {

        return (functionBitmask & 128) == 128;
    }

    /**
     * gibt an ob das Gerät ein Temperatursensor ist
     *
     * @return  ja/nein
     */
    public boolean isTemperatureSensor() {

        return (functionBitmask & 256) == 256;
    }

    /**
     * gibt an ob das Gerät eine schaltbare Steckdose ist
     *
     * @return  ja/nein
     */
    public boolean isSwitchableSocket() {

        return (functionBitmask & 512) == 512;
    }

    /**
     * gibt an ob das Gerät ein DECT Repeater ist
     *
     * @return  ja/nein
     */
    public boolean isDectRepeater() {

        return (functionBitmask & 1024) == 1024;
    }

    /**
     * gibt den Energiemesser zurück
     *
     * @return Energiemesser
     */
    public EnergyMeter getEnergyMeter() {
        return energyMeter;
    }

    /**
     * setzt den Energiemesser
     *
     * @param energyMeter Energiemesser
     */
    public void setEnergyMeter(EnergyMeter energyMeter) {
        this.energyMeter = energyMeter;
    }

    /**
     * gibt den Temperatur Sensor zurück
     *
     * @return Temperatur Sensor
     */
    public TemperatureSensor getTemperatureSensor() {
        return temperatureSensor;
    }

    /**
     * setzt den Temperatur Sensor
     *
     * @param temperatureSensor Temperatur Sensor
     */
    public void setTemperatureSensor(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    /**
     * gibt den Schalter zurück
     *
     * @return Schalter
     */
    public Switch getSwitch() {
        return aSwitch;
    }

    /**
     * setzt den Schalter
     *
     * @param aSwitch Schalter
     */
    public void setSwitch(Switch aSwitch) {
        this.aSwitch = aSwitch;
    }
}
