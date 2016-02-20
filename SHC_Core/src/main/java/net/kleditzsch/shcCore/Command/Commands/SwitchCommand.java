package net.kleditzsch.shcCore.Command.Commands;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.Switchable;
import net.kleditzsch.shcCore.Util.Constant;

/**
 * Schaltbefehl
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SwitchCommand implements net.kleditzsch.shcCore.Command.Interface.SwitchCommand {

    /**
     * schaltbares Element
     */
    protected Switchable switchable;

    /**
     * Befehl
     */
    protected int command;

    public SwitchCommand() {}

    /**
     * @param switchable schaltbares Element
     * @param command Befehl
     */
    public SwitchCommand(Switchable switchable, int command) {

        this.switchable = switchable;
        this.command = command;
    }

    /**
     * gibt das schaltbare Element zurück
     *
     * @return schaltbares Element
     */
    public Switchable getSwitchable() {
        return switchable;
    }

    /**
     * setzt das schaltbare Element
     *
     * @param switchable schaltbares Element
     */
    public void setSwitchable(Switchable switchable) {
        this.switchable = switchable;
    }

    /**
     * gibt den Befehl zurück
     *
     * @return Befehl
     */
    public int getCommand() {
        return command;
    }

    /**
     * setzt den Befehl
     *
     * @param command Befehl
     */
    public void setCommand(int command) {
        this.command = command;
    }

    /**
     * führt einen Schaltbefehl aus
     */
    public void execute() {

        switch(command) {

            case Constant.SWITCH_ON:

                //switchable.triggerOn();
                break;
            case Constant.SWITCH_OFF:

                //switchable.triggerOff();
                break;
            case Constant.SWITCH_TOGGLE:

                //switchable.triggerToggle();
                break;
        }
    }
}
