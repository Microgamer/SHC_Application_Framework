package net.kleditzsch.shcApplicationServer.Room.Elements;

import net.kleditzsch.shcApplicationServer.CommandExecutor.CommandExecutor;
import net.kleditzsch.shcCore.Command.Commands.SwitchCommand;
import net.kleditzsch.shcCore.Room.Elements.Elements.AbstractWakeOnLan;

import java.io.IOException;
import java.net.InetAddress;

/**
 * WakeOnLan
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class WakeOnLan extends AbstractWakeOnLan {

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
     * liest den Status des Elements
     * (die Ausführung kann bis zu 500ms dauern, diese Methode sollte Asynchron verwendet werden)
     *
     * @return Status
     */
    public int readState() {

        try {

            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            if(inetAddress.isReachable(500)) {

                state = ONLINE;
            } else {

                state = OFFLINE;
            }

        } catch (IOException e) {

            state = OFFLINE;
        }
        return state;
    }
}
