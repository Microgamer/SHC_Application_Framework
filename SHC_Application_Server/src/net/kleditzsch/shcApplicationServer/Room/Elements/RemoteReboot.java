package net.kleditzsch.shcApplicationServer.Room.Elements;

import net.kleditzsch.shcCore.Room.Elemnets.Elements.AbstractRemoteReboot;

/**
 * Neustart eines Schaltservers
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RemoteReboot extends AbstractRemoteReboot {

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    @Override
    public void triggerOn() {

        super.triggerOn();
        //TODO implementieren
    }

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void triggerOff() {}
}
