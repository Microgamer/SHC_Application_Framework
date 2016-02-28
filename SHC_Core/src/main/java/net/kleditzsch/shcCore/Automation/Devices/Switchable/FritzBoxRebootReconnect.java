package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractSingleSwitchable;

/**
 * Fritz!Box Neustart und Neu Verbinden
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class FritzBoxRebootReconnect extends AbstractSingleSwitchable {

    /**
     * Funktionen
     */
    public static final int FUNCTION_REBOOT = 1;
    public static final int FUNCTION_RECONNETC_WAN = 2;

    /**
     * Funktion
     */
    protected int function = FUNCTION_RECONNETC_WAN;

    /**
     * Typ
     */
    protected int type = FRITZ_BOX_REBOOT_RECONNECT;

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
        return type;
    }
}
