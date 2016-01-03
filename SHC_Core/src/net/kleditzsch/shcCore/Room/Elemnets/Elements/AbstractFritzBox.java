package net.kleditzsch.shcCore.Room.Elemnets.Elements;

import net.kleditzsch.shcCore.Room.Elemnets.Abstract.AbstractSwitchable;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AbstractFritzBox extends AbstractSwitchable {

    /**
     * Funktionen
     */
    public static final int FUNCTION_SWITCH_2GHZ_WLAN = 1;
    public static final int FUNCTION_SWITCH_5GHZ_WLAN = 2;
    public static final int FUNCTION_SWITCH_GUEST_WLAN = 3;
    public static final int FUNCTION_REBOOT = 4;
    public static final int FUNCTION_RECONNETC_WAN = 5;

    /**
     * Funktion
     */
    protected int function;

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
        return FRITZ_BOX;
    }
}
