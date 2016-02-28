package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractSingleSwitchable;

/**
 * Script mit nur einem Befehl
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ScriptSingle extends AbstractSingleSwitchable {

    /**
     * Befehle
     */
    protected String command;

    /**
     * Schaltserver (wenn leer lokal)
     */
    protected String switchServerHash;

    /**
     * Typ
     */
    protected int type = SCRIPT_SINGLE;

    /**
     * gibt den Einschaltbefehl zurück
     *
     * @return Einschaltbefehl
     */
    public String getCommand() {
        return command;
    }

    /**
     * setzt den Einschaltbefehl
     *
     * @param command Einschaltbefehl
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * gibt den Schaltserver zurück
     *
     * @return Schaltserver
     */
    public String getSwitchServer() {
        return switchServerHash;
    }

    /**
     * setzt den Schaltserver
     *
     * @param switchServerHash Schaltserver
     */
    public void setSwitchServer(String switchServerHash) {
        this.switchServerHash = switchServerHash;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return type;
    }
}
