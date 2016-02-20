package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractDoubleSwitchable;

/**
 * Script mit an/aus Funktion
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ScriptDouble extends AbstractDoubleSwitchable {

    /**
     * Befehle
     */
    protected String onCommand;
    protected String offCommand;

    /**
     * Schaltserver (wenn leer lokal)
     */
    protected String switchServerHash;

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
        return SCRIPT_DOUBLE;
    }
}
