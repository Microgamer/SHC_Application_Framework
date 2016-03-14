package net.kleditzsch.shcCore.Automation.Interface.Readable;

import net.kleditzsch.shcCore.Automation.Interface.AbstractAutomationDevice;

/**
 * lesbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractReadable extends AbstractAutomationDevice implements Readable {

    /**
     * Status
     */
    protected int state = OFF;

    /**
     * Identifizierung
     */
    protected String identifier = "";

    /**
     * gibt den aktuellen Status zurück
     *
     * @return Status
     */
    public int getState() {
        return state;
    }

    /**
     * setzt den aktuellen Status
     *
     * @param state Status
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * gibt die Identifizierung zurück
     *
     * @return Identifizierung
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * setzt die Identifizierung
     *
     * @param identifier Identifizierung
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
