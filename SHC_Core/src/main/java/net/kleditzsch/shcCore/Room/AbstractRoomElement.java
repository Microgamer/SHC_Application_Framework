package net.kleditzsch.shcCore.Room;

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
     * umgekehrte Logik
     */
    protected boolean inverse = false;

    /**
     * gibt an ob das Element in umgekehrter Logik schalten soll
     *
     * @return true wenn umgekehrte Logik
     */
    @Override
    public boolean isInverse() {
        return this.inverse;
    }

    /**
     * aktiviert/deaktiviert das umkehren der Logik
     *
     * @param inverse aktiviert/deaktiviert
     */
    @Override
    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }
}
