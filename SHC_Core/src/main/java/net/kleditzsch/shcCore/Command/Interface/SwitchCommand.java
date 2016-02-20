package net.kleditzsch.shcCore.Command.Interface;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.Switchable;

/**
 * Schaltbefehl
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface SwitchCommand {

    /**
     * gibt das schaltbare Element zurück
     *
     * @return schaltbares Element
     */
    public Switchable getSwitchable();

    /**
     * setzt das schaltbare Element
     *
     * @param switchable schaltbares Element
     */
    public void setSwitchable(Switchable switchable);

    /**
     * gibt den Befehl zurück
     *
     * @return Befehl
     */
    public int getCommand();

    /**
     * setzt den Befehl
     *
     * @param command Befehl
     */
    public void setCommand(int command);

    /**
     * führt einen Schaltbefehl aus
     */
    public void execute();
}
