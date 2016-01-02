package net.kleditzsch.shcCore.Room.Elemnets.Abstract;

import net.kleditzsch.shcCore.Room.Elemnets.Interface.RoomElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Element eines Raumes
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractRoomElement extends AbstractViewElement implements RoomElement {

    /**
     * Sichtbarkeit
     */
    protected boolean visible = true;

    /**
     * Icon
     */
    protected String icon;

    /**
     * gibt an ob das Element sichtbar ist
     *
     * @return Sichtbarkeit
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * setzt die Sichtbarkeit des Elements
     *
     * @param visible Sichtbarkeit
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * gibt das Icon zurück
     *
     * @return Icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * setzt das Icon
     *
     * @param icon Icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
