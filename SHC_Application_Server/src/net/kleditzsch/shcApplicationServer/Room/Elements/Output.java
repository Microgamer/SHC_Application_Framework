package net.kleditzsch.shcApplicationServer.Room.Elements;

import net.kleditzsch.shcCore.Room.Elements.Elements.AbstractOutput;

/**
 * Ausgang
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Output extends AbstractOutput {

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    @Override
    public void triggerOn() {
        super.triggerOn();

        //TODO implementieren (Invertierung beachten)
    }

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void triggerOff() {
        super.triggerOff();

        //TODO implementieren (Invertierung beachten)
    }
}
