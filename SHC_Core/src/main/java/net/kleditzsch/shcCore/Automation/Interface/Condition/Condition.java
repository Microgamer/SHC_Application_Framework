package net.kleditzsch.shcCore.Automation.Interface.Condition;

import net.kleditzsch.shcCore.Automation.AutomationElements;
import net.kleditzsch.shcCore.Core.Element;

/**
 * Schnittstelle Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Condition extends Element,AutomationElements {

    /**
     * gibt an ob die Bedingung aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    boolean isEnabled();

    /**
     * aktiviert/deaktiviert die Bedingung
     *
     * @param enabled aktiviert/deaktiviert
     */
    void setEnabled(boolean enabled);

    /**
     * gibt an ob die Bedingung erfuellt ist
     *
     * @return true wenn die Bedingung erfüllt ist
     */
    boolean isSatisfies();

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    int getType();
}
