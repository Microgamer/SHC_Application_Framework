package net.kleditzsch.shcCore.Room.Elements;

import net.kleditzsch.shcCore.Room.AbstractRoomElement;

/**
 * Separator
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SeparatorElement extends AbstractRoomElement {

    /**
     * Typ
     */
    protected int type = SEPARATOR;

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return type;
    }
}
