package net.kleditzsch.shcCore.Room.Elements.Interface;

/**
 * Gruppe von schaltbaren Elementen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface SwitchableGroup extends Switchable {

    /**
     * fügt ein schaltbares Element hinzu
     *
     * @param switchable schaltbares Element
     * @param command Kommando
     * @return true bei erfolg
     */
    boolean addSwitchable(Switchable switchable, int command);

    /**
     * entfernt ein schaltbares Element
     *
     * @param switchable schaltbares Element
     * @return true bei erfolg
     */
    boolean removeSwitchable(Switchable switchable);

    /**
     * prüft ob ein schaltbares Element schon registriert ist
     *
     * @param switchable
     * @return
     */
    boolean containsSwitchable(Switchable switchable);
}
