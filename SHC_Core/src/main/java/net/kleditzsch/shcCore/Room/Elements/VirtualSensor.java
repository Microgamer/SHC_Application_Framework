package net.kleditzsch.shcCore.Room.Elements;

import net.kleditzsch.shcCore.Room.Abstract.AbstractRoomElement;

/**
 * Virtuelles Sensor Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualSensor extends AbstractRoomElement {

    /**
     * Hash des Virtuellen Sensorwertes
     */
    protected String virtualSensorValueHash = "";

    /**
     * gibt den Hash des Virtuellen Sensowertes zurück
     *
     * @return Hash
     */
    public String getVirtualSensorValueHash() {
        return virtualSensorValueHash;
    }

    /**
     * setzt den Hash des Virtuellen Sensowertes
     *
     * @param virtualSensorValueHash Hash
     */
    public void setVirtualSensorValueHash(String virtualSensorValueHash) {
        this.virtualSensorValueHash = virtualSensorValueHash;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return VIRTUAL_SENSOR;
    }
}
