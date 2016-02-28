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
}
