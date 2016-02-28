package net.kleditzsch.shcCore.Automation.Interface.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.AutomationDevice;

import java.time.LocalDateTime;

/**
 * schaltbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Switchable extends AutomationDevice {

    /**
     * gibt den aktuellen Status zurück
     *
     * @return Status
     */
    int getState();

    /**
     * setzt den aktuellen Status
     *
     * @param state Status
     */
    void setState(int state);

    /**
     * gibt die Zeit des letzten Schaltvorgans zurück
     *
     * @return Zeit
     */
    LocalDateTime getLastToggleTime();

    /**
     * setzt die Zeit des letzen Schaltvorganges
     *
     * @param lastToggleTime Zeit
     */
    void setLastToggleTime(LocalDateTime lastToggleTime);
}
