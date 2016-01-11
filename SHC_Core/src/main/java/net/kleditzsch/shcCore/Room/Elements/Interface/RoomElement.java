package net.kleditzsch.shcCore.Room.Elements.Interface;

/**
 * Element eines Raumes
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface RoomElement extends ViewElement {

    /**
     * gibt an ob das Element sichtbar ist
     *
     * @return Sichtbarkeit
     */
    boolean isVisible();

    /**
     * setzt die Sichtbarkeit des Elements
     *
     * @param visible Sichtbarkeit
     */
    void setVisible(boolean visible);

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
