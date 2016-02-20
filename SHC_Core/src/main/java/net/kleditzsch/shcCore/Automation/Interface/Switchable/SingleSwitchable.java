package net.kleditzsch.shcCore.Automation.Interface.Switchable;

/**
 * schaltbares Element mit einer Schaltmögkichkeit
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface SingleSwitchable extends Switchable {

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    void updateTriggerOn();
}
