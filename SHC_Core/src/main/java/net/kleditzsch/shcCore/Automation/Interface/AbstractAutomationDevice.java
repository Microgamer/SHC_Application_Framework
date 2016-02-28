package net.kleditzsch.shcCore.Automation.Interface;

import net.kleditzsch.shcCore.Core.BasicElement;

/**
 * Automations Gerät
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractAutomationDevice extends BasicElement implements AutomationDevice {

    /**
     * deaktiviert
     */
    protected boolean disabled = false;

    /**
     * Kommentar
     */
    protected String comment = "";

    /**
     * gibt an ob das ELement deaktiviert ist
     *
     * @return true wenn deaktiviert
     */
    public boolean isDisabled() {
        return this.disabled;
    }

    /**
     * aktiviert/deaktiviert das Element
     *
     * @param disabled aktiviert/deaktiviert
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * gibt den Kommentar zurück
     *
     * @return Kommentar
     */
    public String getComment() {
        return comment;
    }

    /**
     * setzt den Kommentar
     *
     * @param comment Kommentar
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
