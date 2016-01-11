package net.kleditzsch.shcCore.Room.Elements.Abstract;

import net.kleditzsch.shcCore.Command.Interface.SwitchCommand;
import net.kleditzsch.shcCore.Room.Elements.Interface.Switchable;
import net.kleditzsch.shcCore.Room.Elements.Interface.SwitchableGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Gruppe von schaltbaren Elementen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractSwitchableGroup extends AbstractSwitchable implements SwitchableGroup {

    /**
     * liste aller Befehle
     */
    protected List<SwitchCommand> commands = new ArrayList<>();

    /**
     * fügt ein schaltbares Element hinzu
     *
     * @param switchable schaltbares Element
     * @param command    Kommando
     * @return true bei erfolg
     */
    @Override
    public boolean addSwitchable(Switchable switchable, int command) {

        if(!containsSwitchable(switchable) && !(switchable instanceof SwitchableGroup)) {

            commands.add(new net.kleditzsch.shcCore.Command.Commands.SwitchCommand(switchable, command));
            return true;
        }
        return false;
    }

    /**
     * entfernt ein schaltbares Element
     *
     * @param switchable schaltbares Element
     * @return true bei erfolg
     */
    @Override
    public boolean removeSwitchable(Switchable switchable) {

        int index = -1;
        for(SwitchCommand switchCommand : commands) {

            if(switchCommand.getSwitchable() == switchable) {

                index = commands.indexOf(switchCommand);
            }
        }

        if(index >= 0) {

            commands.remove(index);
            return true;
        }
        return false;
    }

    /**
     * prüft ob ein schaltbares Element schon registriert ist
     *
     * @param switchable
     * @return
     */
    @Override
    public boolean containsSwitchable(Switchable switchable) {

        for(SwitchCommand switchCommand : commands) {

            if(switchCommand.getSwitchable() == switchable) {

                return true;
            }
        }
        return false;
    }

    /**
     * gibt eine Liste mit allen Kommandos zurück
     *
     * @return Liste mit Kommandos
     */
    public List<SwitchCommand> getCommands() {

        return this.commands;
    }
}
