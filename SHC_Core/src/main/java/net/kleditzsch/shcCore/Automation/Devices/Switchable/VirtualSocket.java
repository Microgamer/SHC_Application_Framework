package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractDoubleSwitchable;

/**
 * Virtuelle Steckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualSocket extends AbstractDoubleSwitchable {

    /**
     * Typ
     */
    protected int type = VIRTUAL_SOCKET;

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
