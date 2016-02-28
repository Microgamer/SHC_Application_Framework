package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractDoubleSwitchable;

/**
 * Fritz!BOx WLan Funktionen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class FritzBoxWirelessLan extends AbstractDoubleSwitchable {

    /**
     * Funktionen
     */
    public static final int FUNCTION_SWITCH_2GHZ_WLAN = 1;
    public static final int FUNCTION_SWITCH_5GHZ_WLAN = 2;
    public static final int FUNCTION_SWITCH_GUEST_WLAN = 3;

    /**
     * Funktion
     */
    protected int function = FUNCTION_SWITCH_GUEST_WLAN;

    /**
     * Typ
     */
    protected int type = FRITZ_BOX_WLAN;

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
