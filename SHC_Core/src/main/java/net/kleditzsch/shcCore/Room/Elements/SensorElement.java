package net.kleditzsch.shcCore.Room.Elements;

import net.kleditzsch.shcCore.Room.AbstractRoomElement;

/**
 * Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SensorElement extends AbstractRoomElement {

    /**
     * Hashes der Sensorwerte
     */
    protected String sensorValue1Hash = "";
    protected String sensorValue2Hash = "";
    protected String sensorValue3Hash = "";

    /**
     * Typ Name
     */
    protected String typeName = "";

    /**
     * Typ
     */
    protected int type = SENSOR;

    /**
     * gibt den Hash des 1. Sensorwertes zurück
     *
     * @return Hash
     */
    public String getSensorValue1Hash() {
        return sensorValue1Hash;
    }

    /**
     * setzt den Hash des 1. Sensorwertes
     *
     * @param sensorValue1Hash Hash
     */
    public void setSensorValue1Hash(String sensorValue1Hash) {
        this.sensorValue1Hash = sensorValue1Hash;
    }

    /**
     * gibt den Hash des 2. Sensorwertes zurück
     *
     * @return Hash
     */
    public String getSensorValue2Hash() {
        return sensorValue2Hash;
    }

    /**
     * setzt den Hash des 2. Sensorwertes
     *
     * @param sensorValue2Hash Hash
     */
    public void setSensorValue2Hash(String sensorValue2Hash) {
        this.sensorValue2Hash = sensorValue2Hash;
    }

    /**
     * gibt den Hash des 3. Sensorwertes zurück
     *
     * @return Hash
     */
    public String getSensorValue3Hash() {
        return sensorValue3Hash;
    }

    /**
     * setzt den Hash des 3. Sensorwertes
     *
     * @param sensorValue3Hash Hash
     */
    public void setSensorValue3Hash(String sensorValue3Hash) {
        this.sensorValue3Hash = sensorValue3Hash;
    }

    /**
     * gibt den Typ Namen zurück
     *
     * @return Typ Name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * setzt den Typ Namen
     *
     * @param typeName Typ Name
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
