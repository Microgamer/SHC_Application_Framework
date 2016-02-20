package net.kleditzsch.shcCore.Room.Interface;

import java.util.Map;

/**
 * Raum Gruppen Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface RoomGroupElement extends ViewElement {

    /**
     * gibt an ob der Raum aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    boolean isEnabled();

    /**
     * aktiviert/deaktiviert den Raum
     *
     * @param enabled aktiviert/deaktiviert
     */
    void setEnabled(boolean enabled);

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param roomElementHash Raum Element
     * @return true bei Erfolg
     */
    boolean addRoomElement(String roomElementHash);

    /**
     * fügt dem Raum ein neues Element hinzu
     *
     * @param roomElementHash Raum Element
     * @param orderId Sortierungs ID
     * @return true bei Erfolg
     */
    boolean addRoomElement(String roomElementHash, int orderId);

    /**
     * entfernt ein Raum Element
     *
     * @param roomElementHash Raum Element
     * @return true bei Erfolg
     */
    boolean removeRoomElement(String roomElementHash);

    /**
     * setzt für ein Raum Element eine neue Sortierungs ID
     *
     * @param roomElementHash Raum Element
     * @param orderId Sortierungs ID
     * @return true bei Erfolg
     */
    boolean updateOrderId(String roomElementHash, int orderId);

    /**
     * prüft ob ein Raum Element im Raum vorhanden ist
     *
     * @param roomElementHash Raum Element
     * @return true wenn vorhanden
     */
    boolean containsRoomElement(String roomElementHash);

    /**
     * gibt eine Liste mit allen Raum Elementen und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    Map<Integer, String> getRoomElements();

    /**
     * gibt eine nach Sortierungs ID sortierte Liste der Raum Elemente und deren Sortierungen zurück
     *
     * @return Raum Elemente
     */
    Map<Integer, String> getRoomElementsOrderedByOrderId();
}
