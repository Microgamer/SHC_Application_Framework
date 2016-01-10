package net.kleditzsch.shcCore.Room.Elements.Elements.Groups;

import net.kleditzsch.shcCore.Command.Interface.SwitchCommand;
import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSwitchableGroup;
import net.kleditzsch.shcCore.Room.Elements.Interface.Switchable;

/**
 * Aktivität
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractActivity extends AbstractSwitchableGroup {

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
        super.triggerOn();
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
        super.triggerOff();
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
