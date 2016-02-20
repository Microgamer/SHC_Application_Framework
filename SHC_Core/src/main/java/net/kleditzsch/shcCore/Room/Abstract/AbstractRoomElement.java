package net.kleditzsch.shcCore.Room.Abstract;

import net.kleditzsch.shcCore.Room.Interface.RoomElement;

/**
 * Raum Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractRoomElement extends AbstractViewElement implements RoomElement {

    /**
     * aktiviert/deaktiviert
     */
    protected boolean enabled = true;

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
}
