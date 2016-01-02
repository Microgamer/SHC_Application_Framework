package net.kleditzsch.shcCore.Room;

import net.kleditzsch.shcCore.Room.Elemnets.Abstract.AbstractViewElement;
import net.kleditzsch.shcCore.Room.Elemnets.Interface.RoomElement;
import net.kleditzsch.shcCore.Room.Elemnets.Interface.RoomElementGroup;
import net.kleditzsch.shcCore.Util.Comparator.RoomElementsNameComparator;
import net.kleditzsch.shcCore.Util.Comparator.RoomElementsOrderComparator;
import net.kleditzsch.shcCore.Util.Constant;

import java.util.*;

/**
 * Raum
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Room extends AbstractViewElement implements net.kleditzsch.shcCore.Room.Elemnets.Interface.Room, RoomElementGroup {

    /**
     * Sortierung
     */
    private int orderId;

    /**
     * Elemente des Raumes
     */
    private Map<Integer, RoomElement> roomElements = new HashMap<>();

    /**
     * gibt die Sortierungs ID zurück
     *
     * @return Sortierungs ID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * setzt die Sortierungs ID
     *
     * @param orderId Sortierungs ID
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param roomElement Raum Element
     * @return true bei Erfolg
     */
    public boolean addRoomElement(RoomElement roomElement) {

        if(!roomElements.containsValue(roomElement)) {

            //höchste Sortingungs ID ermitteln
            int max = 0;
            for (Integer orderId : roomElements.keySet()) {

                if(max < orderId) {

                    max = orderId;
                }
            }

            int orderId = max++;
            addRoomElement(roomElement, orderId);
            return true;
        }
        return false;
    }

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param roomElement Raum Element
     * @param orderId Sortierungs ID
     * @return true bei Erfolg
     */
    public boolean addRoomElement(RoomElement roomElement, int orderId) {

        if(!roomElements.containsValue(roomElement)) {

            roomElements.put(orderId, roomElement);
            return true;
        }
        return false;
    }

    /**
     * entfernt ein Raum Element
     *
     * @param roomElement Raum Element
     * @return true bei Erfolg
     */
    public boolean removeRoomElement(RoomElement roomElement) {

        if(roomElements.containsValue(roomElement)) {

            for(Integer orderId : roomElements.keySet()) {

                RoomElement knownRoomElement = roomElements.get(orderId);
                if(knownRoomElement == roomElement) {

                    roomElements.remove(orderId);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * setzt für ein Raum Element eine neue Sortierungs ID
     *
     * @param roomElement Raum Element
     * @param orderId Sortierungs ID
     * @return true bei Erfolg
     */
    public boolean updateOrderId(RoomElement roomElement, int orderId) {

        if(roomElements.containsValue(roomElement)) {

            for(Integer knownOrderId : roomElements.keySet()) {

                RoomElement knownRoomElement = roomElements.get(knownOrderId);
                if(knownRoomElement == roomElement) {

                    roomElements.remove(orderId);
                    roomElements.put(orderId, roomElement);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * prüft ob ein Raum Element im Raum vorhanden ist
     *
     * @param roomElement Raum Element
     * @return true wenn vorhanden
     */
    public boolean containsRoomElement(RoomElement roomElement) {

        return roomElements.containsValue(roomElement);
    }

    /**
     * gibt eine Liste mit allen Raum Elementen und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    public Map<Integer, RoomElement> getRoomElements() {

        return roomElements;
    }

    /**
     * gibt eine nach Namen sortierte Liste der Raum Elemente und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    public Map<Integer, RoomElement> getRoomElementsOrderedByName() {

        SortedSet<Map.Entry<Integer, RoomElement>> sortedSet = new TreeSet<>(new RoomElementsNameComparator());
        sortedSet.addAll(roomElements.entrySet());

        Map<Integer, RoomElement> output = new HashMap<>();
        for(Map.Entry<Integer, RoomElement> entry : sortedSet) {

            output.put(entry.getKey(), entry.getValue());
        }
        return output;
    }

    /**
     * gibt eine nach Sortierungs ID sortierte Liste der Raum Elemente und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    public Map<Integer, RoomElement> getRoomElementsOrderedByOrderId() {

        SortedSet<Map.Entry<Integer, RoomElement>> sortedSet = new TreeSet<>(new RoomElementsOrderComparator());
        sortedSet.addAll(roomElements.entrySet());

        Map<Integer, RoomElement> output = new HashMap<>();
        for(Map.Entry<Integer, RoomElement> entry : sortedSet) {

            output.put(entry.getKey(), entry.getValue());
        }
        return output;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    public int getType() {
        return ROOM;
    }
}
