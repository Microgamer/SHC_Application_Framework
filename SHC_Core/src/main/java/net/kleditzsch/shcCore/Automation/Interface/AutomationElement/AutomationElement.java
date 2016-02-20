package net.kleditzsch.shcCore.Automation.Interface.AutomationElement;

import net.kleditzsch.shcCore.Automation.AutomationElements;
import net.kleditzsch.shcCore.Automation.Interface.Condition.Condition;
import net.kleditzsch.shcCore.Automation.Interface.Switchable.Switchable;
import net.kleditzsch.shcCore.Core.Element;

import java.util.Set;

/**
 * Schnittstelle Automatisierungs Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface AutomationElement extends Element, AutomationElements {

    /**
     * gibt an ob das Element aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    boolean isEnabled();

    /**
     * aktiviert/deaktiviert das Element
     *
     * @param enabled aktiviert/deaktiviert
     */
    void setEnabled(boolean enabled);

    /**
     * gibt die Liste der Bedingungen zurück
     *
     * @return Liste der Bedingungen
     */
    Set<Condition> getConditions();

    /**
     * gibt die Liste der schaltbaren Elemente zurück
     *
     * @return Liste der schaltbaren Elemente
     */
    Set<Switchable> getSwitchables();

    /**
     * gibt an ob die Bedingung erfuellt ist
     *
     * @return true wenn die Bedingung erfüllt ist
     */
    boolean isSatisfies();

    /**
     * führt die Schaltaktionen aus
     */
    void execute();

    /**
     * prüft ob der Konfigurierte Zustand zurifft und führt alle Schaltaktionen aus
     */
    void run();

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    int getType();
}
