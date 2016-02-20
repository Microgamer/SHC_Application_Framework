package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractSingleSwitchable;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RebootShutdown extends AbstractSingleSwitchable {

    /**
     * Funktionen
     */
    public static final int FUNCTION_REBOOT = 1;
    public static final int FUNCTION_SHUTDOWN = 2;

    /**
     * Schaltserver (wenn leer lokal)
     */
    protected String switchServerHash;

    /**
     * Funktion
     */
    protected int function = FUNCTION_REBOOT;

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
     * gibt die Funktion zurück
     *
     * @return Funktion
     */
    public int getFunction() {
        return function;
    }

    /**
     * setzt die Funktion
     *
     * @param function Funktion
     */
    public void setFunction(int function) {
        this.function = function;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return REBOOT_SHUTDOWN;
    }
}
