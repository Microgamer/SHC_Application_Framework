package net.kleditzsch.shcCore.Room;

import net.kleditzsch.shcCore.Room.Abstract.AbstractViewElement;
import net.kleditzsch.shcCore.Room.Interface.ViewElement;
import net.kleditzsch.shcCore.Util.Comparator.ViewElementsOrderComparator;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Raum
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Room extends AbstractViewElement {

    /**
     * liste mit allen Elementen des Raumes
     */
    protected Map<Integer, ViewElement> viewElements = new HashMap<>();

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param viewElement Raum Element
     * @return true bei Erfolg
     */
    public boolean addViewElement(ViewElement viewElement) {

        if(!viewElements.containsValue(viewElement)) {

            //höchste Sortingungs ID ermitteln
            int max = 0;
            for (Integer orderId : viewElements.keySet()) {

                if(max < orderId) {

                    max = orderId;
                }
            }

            int orderId = max++;
            addRoomElement(viewElement, orderId);
            return true;
        }
        return false;
    }

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param viewElement Raum Element
     * @param orderId         Sortierungs ID
     * @return true bei Erfolg
     */
    public boolean addRoomElement(ViewElement viewElement, int orderId) {

        if(!viewElements.containsValue(viewElement)) {

            viewElements.put(orderId, viewElement);
            return true;
        }
        return false;
    }

    /**
     * entfernt ein Raum Element
     *
     * @param viewElement Raum Element
     * @return true bei Erfolg
     */
    public boolean removeRoomElement(ViewElement viewElement) {

        if(viewElements.containsValue(viewElement)) {

            for(Integer orderId : viewElements.keySet()) {

                ViewElement knownViewElement = viewElements.get(orderId);
                if(viewElement.getHash().equals(knownViewElement.getHash())) {

                    viewElements.remove(orderId);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * setzt für ein Raum Element eine neue Sortierungs ID
     *
     * @param viewElement Raum Element
     * @param orderId         Sortierungs ID
     * @return true bei Erfolg
     */
    public boolean updateOrderId(ViewElement viewElement, int orderId) {

        if(viewElements.containsValue(viewElement)) {

            for(Integer knownOrderId : viewElements.keySet()) {

                ViewElement knownRoomElement = viewElements.get(knownOrderId);
                if(viewElement.getHash().equals(knownRoomElement.getHash())) {

                    viewElements.remove(orderId);
                    viewElements.put(orderId, viewElement);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * prüft ob ein Raum Element im Raum vorhanden ist
     *
     * @param viewElement Raum Element
     * @return true wenn vorhanden
     */
    public boolean containsRoomElement(ViewElement viewElement) {

        return viewElements.containsValue(viewElement);
    }

    /**
     * gibt eine Liste mit allen Raum Elementen und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    public Map<Integer, ViewElement> getRoomElements() {
        return viewElements;
    }

    /**
     * gibt eine nach Sortierungs ID sortierte Liste der Raum Elemente und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    public Map<Integer, ViewElement> getRoomElementsOrderedByOrderId() {

        SortedSet<Map.Entry<Integer, ViewElement>> sortedSet = new TreeSet<>(new ViewElementsOrderComparator());
        sortedSet.addAll(viewElements.entrySet());

        Map<Integer, ViewElement> output = new HashMap<>();
        for(Map.Entry<Integer, ViewElement> entry : sortedSet) {

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
        return ROOM;
    }
}
