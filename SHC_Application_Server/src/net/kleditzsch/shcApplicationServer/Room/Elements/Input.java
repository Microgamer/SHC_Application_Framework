package net.kleditzsch.shcApplicationServer.Room.Elements;

import net.kleditzsch.shcCore.Room.Elemnets.Elements.AbstractInput;

/**
 * Eingang
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Input extends AbstractInput {

    /**
     * liest den Status des Elements
     *
     * @return Status
     */
    @Override
    public int readState() {

        //TODO implementieren (Invertierung beachten)
        return 0;
    }
}
