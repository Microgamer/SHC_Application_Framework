package net.kleditzsch.shcCore.Room.Elements.Interface;

/**
 * Raum
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Room extends RoomElementGroup {

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

    /**
     * gibt das Icon zurück
     *
     * @return Icon
     */
    String getIcon();

    /**
     * setzt das Icon
     *
     * @param icon Icon
     */
    void setIcon(String icon);
}
