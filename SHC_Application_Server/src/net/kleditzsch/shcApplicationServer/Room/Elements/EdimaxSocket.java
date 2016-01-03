package net.kleditzsch.shcApplicationServer.Room.Elements;

import net.kleditzsch.shcCore.Room.Elemnets.Elements.AbstractEdimaxSocket;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class EdimaxSocket extends AbstractEdimaxSocket {

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    @Override
    public void triggerOn() {
        super.triggerOn();
    }

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    @Override
    public void triggerOff() {
        super.triggerOff();
    }

    /**
     * liest die Sensordaten und den Status der Steckdose
     */
    public void readData() {


    }
}
