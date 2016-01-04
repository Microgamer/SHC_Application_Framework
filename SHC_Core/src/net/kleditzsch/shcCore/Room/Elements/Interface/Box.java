package net.kleditzsch.shcCore.Room.Elements.Interface;

/**
 * Box
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Box extends RoomElement {

    /**
     * gibt die Sortierungs ID zurück
     *
     * @return Sortierungs ID
     */
    int getOrderId();

    /**
     * setzt die Sortierungs ID
     *
     * @param orderId Sortierungs ID
     */
    void setOrderId(int orderId);
}
