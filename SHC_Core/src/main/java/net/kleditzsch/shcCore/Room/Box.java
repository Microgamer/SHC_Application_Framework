package net.kleditzsch.shcCore.Room;

import net.kleditzsch.shcCore.Room.Abstract.AbstractViewElement;
import net.kleditzsch.shcCore.Room.Interface.RoomGroupElement;
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
public class Box extends AbstractViewElement implements RoomGroupElement {

    /**
     * aktiviert/deaktiviert
     */
    protected boolean enabled = true;

    /**
     * Liste der Elemente
     */
    protected Map<Integer, String> elements = new HashMap<>();

    /**
     * gibt an ob der Raum aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * aktiviert/deaktiviert den Raum
     *
     * @param enabled aktiviert/deaktiviert
     */
    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param roomElementHash Raum Element
     * @return true bei Erfolg
     */
    @Override
    public boolean addRoomElement(String roomElementHash) {

        if(!elements.containsValue(roomElementHash)) {

            //höchste Sortingungs ID ermitteln
            int max = 0;
            for (Integer orderId : elements.keySet()) {

                if(max < orderId) {

                    max = orderId;
                }
            }

            int orderId = max++;
            addRoomElement(roomElementHash, orderId);
            return true;
        }
        return false;
    }

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param roomElementHash Raum Element
     * @param orderId         Sortierungs ID
     * @return true bei Erfolg
     */
    @Override
    public boolean addRoomElement(String roomElementHash, int orderId) {

        if(!elements.containsValue(roomElementHash)) {

            elements.put(orderId, roomElementHash);
            return true;
        }
        return false;
    }

    /**
     * entfernt ein Raum Element
     *
     * @param roomElementHash Raum Element
     * @return true bei Erfolg
     */
    @Override
    public boolean removeRoomElement(String roomElementHash) {

        if(elements.containsValue(roomElementHash)) {

            for(Integer orderId : elements.keySet()) {

                String knownRoomElement = elements.get(orderId);
                if(knownRoomElement.equals(roomElementHash)) {

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
     * @param roomElementHash Raum Element
     * @param orderId         Sortierungs ID
     * @return true bei Erfolg
     */
    @Override
    public boolean updateOrderId(String roomElementHash, int orderId) {

        if(elements.containsValue(roomElementHash)) {

            for(Integer knownOrderId : elements.keySet()) {

                String knownRoomElement = elements.get(knownOrderId);
                if(knownRoomElement.equals(roomElementHash)) {

                    elements.remove(orderId);
                    elements.put(orderId, roomElementHash);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * prüft ob ein Raum Element im Raum vorhanden ist
     *
     * @param roomElementHash Raum Element
     * @return true wenn vorhanden
     */
    @Override
    public boolean containsRoomElement(String roomElementHash) {

        return elements.containsValue(roomElementHash);
    }

    /**
     * gibt eine Liste mit allen Raum Elementen und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    @Override
    public Map<Integer, String> getRoomElements() {
        return elements;
    }

    /**
     * gibt eine nach Sortierungs ID sortierte Liste der Raum Elemente und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    @Override
    public Map<Integer, String> getRoomElementsOrderedByOrderId() {

        SortedSet<Map.Entry<Integer, String>> sortedSet = new TreeSet<>(new RoomElementsOrderComparator());
        sortedSet.addAll(elements.entrySet());

        Map<Integer, String> output = new HashMap<>();
        for(Map.Entry<Integer, String> entry : sortedSet) {

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
