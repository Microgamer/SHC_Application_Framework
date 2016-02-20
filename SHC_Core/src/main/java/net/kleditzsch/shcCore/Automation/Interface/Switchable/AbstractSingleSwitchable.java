package net.kleditzsch.shcCore.Automation.Interface.Switchable;

import java.time.LocalDateTime;

/**
 * schaltbares Element mit einer Schaltmögkichkeit
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractSingleSwitchable extends AbstractSwitchable implements SingleSwitchable {

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    @Override
    public void updateTriggerOn() {

        lastToggleTime = LocalDateTime.now();
    }
}
