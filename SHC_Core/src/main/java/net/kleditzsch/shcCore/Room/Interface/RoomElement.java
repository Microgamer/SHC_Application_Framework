package net.kleditzsch.shcCore.Room.Interface;

/**
 * Raum Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface RoomElement extends ViewElement {

    /**
     * gibt an ob das Element in umgekehrter Logik schalten soll
     *
     * @return true wenn umgekehrte Logik
     */
    boolean isInverse();

    /**
     * aktiviert/deaktiviert das umkehren der Logik
     *
     * @param inverse aktiviert/deaktiviert
     */
    void setInverse(boolean inverse);
}
