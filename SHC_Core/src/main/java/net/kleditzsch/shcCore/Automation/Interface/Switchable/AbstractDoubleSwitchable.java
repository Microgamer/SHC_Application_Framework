package net.kleditzsch.shcCore.Automation.Interface.Switchable;

import java.time.LocalDateTime;

/**
 * schaltbares Element mit 2 Schaltmöglichkeiten (an/aus)
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractDoubleSwitchable extends AbstractSwitchable implements DoubleSwitchable {

    /**
     * Invertierung
     */
    protected boolean inverse = false;

    /**
     * gibt an ob die Invertierung aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isInverse() {
        return inverse;
    }

    /**
     * aktiviert/deaktiviert die Invertierung
     *
     * @param inverse aktiviert/deaktiviert
     */
    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    @Override
    public void updateTriggerOn() {

        this.state = ON;
        this.lastToggleTime = LocalDateTime.now();
    }

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void updateTriggerOff() {

        this.state = OFF;
        this.lastToggleTime = LocalDateTime.now();
    }
}
