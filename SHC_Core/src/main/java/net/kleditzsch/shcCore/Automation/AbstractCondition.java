package net.kleditzsch.shcCore.Automation;

import net.kleditzsch.shcCore.Automation.Interface.Condition;
import net.kleditzsch.shcCore.Core.BasicElement;

/**
 * Basis Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractCondition extends BasicElement implements Condition {

    /**
     * gibt an ob die Bedingung aktiviert/deaktiviert ist
     */
    protected boolean enabled = true;

    /**
     * Tes Modus aktiviert
     */
    protected boolean testMode = false;

    /**
     * gibt an ob die Bedingung aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * aktiviert/deaktiviert die Bedingung
     *
     * @param enabled aktiviert/deaktiviert
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * gibt an ob der Test Modus aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isTestMode() {
        return testMode;
    }

    /**
     * aktiviert/deaktiviert den Test Modus
     *
     * @param testMode aktiviert/deaktiviert
     */
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }
}
