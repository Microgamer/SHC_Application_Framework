package net.kleditzsch.shcCore.Room.Elemnets.Interface;

/**
 * Element welchese einen Status speichert
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface StateElement extends RoomElement {

    /**
     * gibt den aktuellen Status zurück
     *
     * @return Status
     */
    int getState();

    /**
     * setzt den aktuellen Status
     *
     * @param state Status
     */
    void setState(int state);
}
