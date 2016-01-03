package net.kleditzsch.shcCore.Room.Elemnets.Elements;

import net.kleditzsch.shcCore.Room.Elemnets.Abstract.AbstractSwitchable;

/**
 * Virtuelle Steckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractVirtualSocket extends AbstractSwitchable {
    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return VIRTUAL_SOCKET;
    }
}
