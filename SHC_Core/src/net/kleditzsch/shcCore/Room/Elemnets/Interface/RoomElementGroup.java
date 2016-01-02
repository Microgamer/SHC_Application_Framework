package net.kleditzsch.shcCore.Room.Elemnets.Interface;

import java.util.Map;

/**
 * Gruppe von Raum Elementen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface RoomElementGroup extends ViewElement {

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param roomElement Raum Element
     * @return true bei Erfolg
     */
    public boolean addRoomElement(RoomElement roomElement);

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param roomElement Raum Element
     * @param orderId Sortierungs ID
     * @return true bei Erfolg
     */
    public boolean addRoomElement(RoomElement roomElement, int orderId);

    /**
     * entfernt ein Raum Element
     *
     * @param roomElement Raum Element
     * @return true bei Erfolg
     */
    public boolean removeRoomElement(RoomElement roomElement);

    /**
     * setzt für ein Raum Element eine neue Sortierungs ID
     *
     * @param roomElement Raum Element
     * @param orderId Sortierungs ID
     * @return true bei Erfolg
     */
    public boolean updateOrderId(RoomElement roomElement, int orderId);

    /**
     * prüft ob ein Raum Element im Raum vorhanden ist
     *
     * @param roomElement Raum Element
     * @return true wenn vorhanden
     */
    public boolean containsRoomElement(RoomElement roomElement);

    /**
     * gibt eine Liste mit allen Raum Elementen und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    public Map<Integer, RoomElement> getRoomElements();

    /**
     * gibt eine nach Namen sortierte Liste der Raum Elemente und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    public Map<Integer, RoomElement> getRoomElementsOrderedByName();

    /**
     * gibt eine nach Sortierungs ID sortierte Liste der Raum Elemente und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    public Map<Integer, RoomElement> getRoomElementsOrderedByOrderId();
}
