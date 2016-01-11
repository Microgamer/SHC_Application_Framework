package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSwitchable;

/**
 * Script
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractScript extends AbstractSwitchable {

    /**
     * Befehle
     */
    protected String onCommand;
    protected String offCommand;

    /**
     * gibt den Ausschaltbefehl zurück
     *
     * @return Ausschaltbefehl
     */
    public String getOffCommand() {
        return offCommand;
    }

    /**
     * setzt den Ausschaltbefehl
     *
     * @param offCommand Ausschaltbefehl
     */
    public void setOffCommand(String offCommand) {
        this.offCommand = offCommand;
    }

    /**
     * gibt den Einschaltbefehl zurück
     *
     * @return Einschaltbefehl
     */
    public String getOnCommand() {
        return onCommand;
    }

    /**
     * setzt den Einschaltbefehl
     *
     * @param onCommand Einschaltbefehl
     */
    public void setOnCommand(String onCommand) {
        this.onCommand = onCommand;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return SCRIPT;
    }
}
