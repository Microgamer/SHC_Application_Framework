package net.kleditzsch.shcApplicationServer.Room.Elements;

import net.kleditzsch.shcApplicationServer.CommandExecutor.CommandExecutor;
import net.kleditzsch.shcCore.Command.Commands.SwitchCommand;
import net.kleditzsch.shcCore.Room.Elements.Elements.AbstractOutput;

/**
 * Ausgang
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Output extends AbstractOutput {

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

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void triggerOff() {
        super.triggerOff();

        if(isEnabled()) {

            CommandExecutor.getInstance().addSwtichCommand(new SwitchCommand(this, SWITCH_OFF));
        }
    }
}
