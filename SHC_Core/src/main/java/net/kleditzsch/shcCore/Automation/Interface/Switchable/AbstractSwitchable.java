package net.kleditzsch.shcCore.Automation.Interface.Switchable;

import net.kleditzsch.shcCore.Core.BasicElement;

import java.time.LocalDateTime;

/**
 * schaltbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractSwitchable extends BasicElement implements Switchable {

    /**
     * deaktiviert
     */
    protected boolean disabled = false;

    /**
     * Status
     */
    protected int state = OFF;

    /**
     * gibt an ob der Status angezeigt werden soll
     */
    protected boolean showStateEnabled = true;

    /**
     * Zeitpunkt des letzten Schaltvorganges
     */
    protected LocalDateTime lastToggleTime;

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
     * gibt an ob die Anzeige des Status der schaltbaren Elemente aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isShowStateEnabled() {
        return showStateEnabled;
    }

    /**
     * aktiviert/deaktiviert die Anzeige des Status der schaltbaren Elemente
     *
     * @param showStateEnabled aktiviert/deaktiviert
     */
    public void setShowStateEnabled(boolean showStateEnabled) {
        this.showStateEnabled = showStateEnabled;
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
