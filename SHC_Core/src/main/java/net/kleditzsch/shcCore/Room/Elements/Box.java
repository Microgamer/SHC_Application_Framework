package net.kleditzsch.shcCore.Room.Elements;

import net.kleditzsch.shcCore.Room.Abstract.AbstractViewElement;
import net.kleditzsch.shcCore.Room.Interface.RoomElement;
import net.kleditzsch.shcCore.Util.Comparator.RoomElementsOrderComparator;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Raum Gruppen Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Box extends AbstractViewElement {

    /**
     * Liste der Elemente
     */
    protected Map<Integer, RoomElement> elements = new HashMap<>();

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param roomElement Raum Element
     * @return true bei Erfolg
     */
    public boolean addRoomElement(RoomElement roomElement) {

        if(!elements.containsValue(roomElement)) {

            //höchste Sortingungs ID ermitteln
            int max = 0;
            for (Integer orderId : elements.keySet()) {

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
     * @param orderId         Sortierungs ID
     * @return true bei Erfolg
     */
    public boolean addRoomElement(RoomElement roomElement, int orderId) {

        if(!elements.containsValue(roomElement)) {

            elements.put(orderId, roomElement);
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

        if(elements.containsValue(roomElement)) {

            for(Integer orderId : elements.keySet()) {

                RoomElement knownRoomElement = elements.get(orderId);
                if(roomElement.getHash().equals(knownRoomElement.getHash())) {

                    elements.remove(orderId);
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
     * @param orderId         Sortierungs ID
     * @return true bei Erfolg
     */
    public boolean updateOrderId(RoomElement roomElement, int orderId) {

        if(elements.containsValue(roomElement)) {

            for(Integer knownOrderId : elements.keySet()) {

                RoomElement knownRoomElement = elements.get(knownOrderId);
                if(roomElement.getHash().equals(knownRoomElement.getHash())) {

                    elements.remove(orderId);
                    elements.put(orderId, roomElement);
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

        return elements.containsValue(roomElement);
    }

    /**
     * gibt eine Liste mit allen Raum Elementen und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    public Map<Integer, RoomElement> getRoomElements() {
        return elements;
    }

    /**
     * gibt eine nach Sortierungs ID sortierte Liste der Raum Elemente und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    public Map<Integer, RoomElement> getRoomElementsOrderedByOrderId() {

        SortedSet<Map.Entry<Integer, RoomElement>> sortedSet = new TreeSet<>(new RoomElementsOrderComparator());
        sortedSet.addAll(elements.entrySet());

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
    @Override
    public int getType() {
        return BOX;
    }
}
