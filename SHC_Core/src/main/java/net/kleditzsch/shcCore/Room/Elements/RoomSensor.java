package net.kleditzsch.shcCore.Room.Elements;

import net.kleditzsch.shcCore.Room.Abstract.AbstractRoomElement;

/**
 * Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RoomSensor extends AbstractRoomElement {

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return SENSOR;
    }
}
