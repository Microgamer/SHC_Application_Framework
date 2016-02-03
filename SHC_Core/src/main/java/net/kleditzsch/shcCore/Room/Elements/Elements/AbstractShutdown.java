package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSwitchable;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;

/**
 * Herunterfahren
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractShutdown extends AbstractSwitchable {

    /**
     * Schaltserver
     */
    protected SwitchServer switchServer;

    /**
     * gibt den Schaltserver zurück
     *
     * @return Schaltserver
     */
    public SwitchServer getSwitchServer() {
        return switchServer;
    }

    /**
     * setzt den Schaltserver
     *
     * @param switchServer Schaltserver
     */
    public void setSwitchServer(SwitchServer switchServer) {
        this.switchServer = switchServer;
    }

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
