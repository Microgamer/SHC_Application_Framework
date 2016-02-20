package net.kleditzsch.shcCore.Room.Interface;

/**
 * RaumElement
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface RoomElement extends ViewElement {

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
}
