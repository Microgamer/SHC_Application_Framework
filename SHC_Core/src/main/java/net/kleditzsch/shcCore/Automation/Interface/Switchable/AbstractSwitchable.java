package net.kleditzsch.shcCore.Automation.Interface.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.AbstractAutomationDevice;

import java.time.LocalDateTime;

/**
 * schaltbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractSwitchable extends AbstractAutomationDevice implements Switchable {

    /**
     * Status
     */
    protected int state = OFF;

    /**
     * Zeitpunkt des letzten Schaltvorganges
     */
    protected LocalDateTime lastToggleTime;

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
     * gibt die Zeit des letzten Schaltvorgans zurück
     *
     * @return Zeit
     */
    @Override
    public LocalDateTime getLastToggleTime() {
        return this.lastToggleTime;
    }

    /**
     * setzt die Zeit des letzen Schaltvorganges
     *
     * @param lastToggleTime Zeit
     */
    @Override
    public void setLastToggleTime(LocalDateTime lastToggleTime) {
        this.lastToggleTime = lastToggleTime;
    }
}
