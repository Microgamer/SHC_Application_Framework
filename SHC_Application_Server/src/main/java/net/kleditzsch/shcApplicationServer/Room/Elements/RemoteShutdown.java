package net.kleditzsch.shcApplicationServer.Room.Elements;

import net.kleditzsch.shcApplicationServer.CommandExecutor.CommandExecutor;
import net.kleditzsch.shcCore.Command.Commands.SwitchCommand;
import net.kleditzsch.shcCore.Room.Elements.Elements.AbstractRemoteShutdown;

/**
 * Herunterfahren eines Schaltservers
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RemoteShutdown extends AbstractRemoteShutdown {

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    @Override
    public void triggerOn() {
        super.triggerOn();

        if(isEnabled()) {

            CommandExecutor.getInstance().addSwtichCommand(new SwitchCommand(this, SWITCH_ON));
        }
    }
}
