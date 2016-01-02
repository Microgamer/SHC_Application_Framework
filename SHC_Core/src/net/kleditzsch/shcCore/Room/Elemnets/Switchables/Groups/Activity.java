package net.kleditzsch.shcCore.Room.Elemnets.Switchables.Groups;

import net.kleditzsch.shcCore.Command.Commands.SwitchCommand;
import net.kleditzsch.shcCore.Room.Elemnets.Abstract.AbstractSwitchableGroup;
import net.kleditzsch.shcCore.Room.Elemnets.Interface.Switchable;

/**
 * Aktivität
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Activity extends AbstractSwitchableGroup {

    /**
     * gibt den aktuellen Status zurück
     *
     * @return Status
     */
    @Override
    public int getState() {

        for(SwitchCommand switchCommand : commands) {

            Switchable switchable = switchCommand.getSwitchable();
            int command = switchCommand.getCommand();

            if((command == SWITCH_ON && switchable.getState() == LOW) || (command == SWITCH_OFF && switchable.getState() == HIGH)) {

                state = LOW;
                return LOW;
            }
        }
        state = HIGH;
        return HIGH;
    }

    /**
     * fügt ein schaltbares Element hinzu
     *
     * @param switchable schaltbares Element
     * @param command    Kommando
     * @return true bei erfolg
     */
    @Override
    public boolean addSwitchable(Switchable switchable, int command) {

        //
        if(switchable instanceof Activity) {

            return false;
        }
        return super.addSwitchable(switchable, command);
    }

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    @Override
    public void triggerOn() {

        for(SwitchCommand switchCommand : commands) {

            Switchable switchable = switchCommand.getSwitchable();
            int command = switchCommand.getCommand();

            switch (command) {

                case SWITCH_ON:

                    switchable.triggerOn();
                    break;
                case SWITCH_OFF:

                    switchable.triggerOff();
                    break;
            }
        }
    }

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void triggerOff() {

        for(SwitchCommand switchCommand : commands) {

            Switchable switchable = switchCommand.getSwitchable();
            int command = switchCommand.getCommand();

            switch (command) {

                case SWITCH_ON:

                    switchable.triggerOff();
                    break;
                case SWITCH_OFF:

                    switchable.triggerOn();
                    break;
            }
        }
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return ACTIVITY;
    }
}
