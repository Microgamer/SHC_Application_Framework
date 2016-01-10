package net.kleditzsch.shcCore.Room.Elements.Elements.Groups;

import net.kleditzsch.shcCore.Command.Interface.SwitchCommand;
import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSwitchableGroup;
import net.kleditzsch.shcCore.Room.Elements.Interface.Switchable;

import java.time.LocalDateTime;

/**
 * Taster
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractButton extends AbstractSwitchableGroup {

    /**
     * Intervall in Millisekunden
     */
    protected long intervall = 500;

    /**
     * Ausschaltzeit
     */
    protected LocalDateTime switchBackTime;

    /**
     * Befehl zum Zurückschalten
     */
    protected int switchBackCommand = 0;

    /**
     * gibt den Intervall in ms zurück
     *
     * @return Intervall in ms
     */
    public long getIntervall() {
        return intervall;
    }

    /**
     * setzt den Intervall in ms
     *
     * @param intervall Intervall in ms
     */
    public void setIntervall(long intervall) {
        this.intervall = intervall;
    }

    /**
     * gibt die Ausschaltzeit zurück
     * (wenn kein Countdown aktiv dann null)
     *
     * @return Ausschaltzeit
     */
    public LocalDateTime getSwitchBackTime() {
        return switchBackTime;
    }

    /**
     * setzt die Ausschaltzeit
     *
     * @param switchBackTime Ausschaltzeit
     */
    public void setSwitchBackTime(LocalDateTime switchBackTime) {
        this.switchBackTime = switchBackTime;
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
        switchBackTime = LocalDateTime.now().plusNanos(intervall * 1000 * 1000);
        switchBackCommand = SWITCH_OFF;
        super.triggerOn();
    }

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void triggerOff() {}

    /**
     * prüft ob die Ausschaltzeit abgelaufen ist und löst den Rückschalte Befehl aus
     */
    public void scheduleCountdown() {

        LocalDateTime now = LocalDateTime.now();
        if(switchBackTime != null && (switchBackTime.isBefore(now) || switchBackTime.isEqual(now))) {

            switch(switchBackCommand) {

                case SWITCH_ON:

                    for (SwitchCommand switchCommand : commands) {

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
                    switchBackCommand = 0;
                    switchBackTime = null;
                    super.triggerOn();
                    break;
                case SWITCH_OFF:

                    for (SwitchCommand switchCommand : commands) {

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
                    switchBackCommand = 0;
                    switchBackTime = null;
                    super.triggerOff();
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
        return BUTTON;
    }
}
