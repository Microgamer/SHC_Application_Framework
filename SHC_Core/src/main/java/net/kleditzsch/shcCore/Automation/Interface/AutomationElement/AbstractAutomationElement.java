package net.kleditzsch.shcCore.Automation.Interface.AutomationElement;


import net.kleditzsch.shcCore.Automation.Interface.Condition.Condition;
import net.kleditzsch.shcCore.Automation.Interface.Switchable.Switchable;
import net.kleditzsch.shcCore.Core.BasicElement;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Basisi Automatisierungs Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractAutomationElement extends BasicElement implements AutomationElement {

    /**
     * gibt an ob das Element aktiviert/deaktiviert ist
     */
    protected boolean enabled;

    /**
     * letzte Ausführungszeit
     */
    protected LocalDateTime lastExecution;

    /**
     * Liste aller Bedingungen des Elements
     */
    protected final Set<Condition> conditions = new HashSet<>();

    /**
     * Liste aller schaltbaren Elemente
     */
    protected final Set<Switchable> switchables = new HashSet<>();

    /**
     * gibt an ob das Element aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * aktiviert/deaktiviert das Element
     *
     * @param enabled aktiviert/deaktiviert
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * gibt die letzte Ausführungszeit zurück
     *
     * @return Zeitstempel
     */
    public LocalDateTime getLastExecution() {
        return lastExecution;
    }

    /**
     * setzt die letzte Ausführungszeit
     *
     * @param lastExecution Zeitstempel
     */
    public void setLastExecution(LocalDateTime lastExecution) {
        this.lastExecution = lastExecution;
    }

    /**
     * gibt die Liste der Bedingungen zurück
     *
     * @return Liste der Bedingungen
     */
    public Set<Condition> getConditions() {

        return this.conditions;
    }

    /**
     * gibt die Liste der schaltbaren Elemente zurück
     *
     * @return Liste der schaltbaren Elemente
     */
    public Set<Switchable> getSwitchables() {

        return this.switchables;
    }
}
