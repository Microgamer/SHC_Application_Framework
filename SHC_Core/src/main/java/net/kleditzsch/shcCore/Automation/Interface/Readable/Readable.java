package net.kleditzsch.shcCore.Automation.Interface.Readable;

import net.kleditzsch.shcCore.Automation.Interface.AutomationDevice;

/**
 * lesbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Readable extends AutomationDevice {

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
}
