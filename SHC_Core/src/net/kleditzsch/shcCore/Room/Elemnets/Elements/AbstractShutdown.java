package net.kleditzsch.shcCore.Room.Elemnets.Elements;

import net.kleditzsch.shcCore.Room.Elemnets.Abstract.AbstractSwitchable;

/**
 * Herunterfahren
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractShutdown extends AbstractSwitchable {

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void triggerOff() {}

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return SHUTDOWN;
    }
}
