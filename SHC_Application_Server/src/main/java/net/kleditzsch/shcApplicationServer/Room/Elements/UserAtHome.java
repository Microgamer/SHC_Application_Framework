package net.kleditzsch.shcApplicationServer.Room.Elements;

import net.kleditzsch.shcCore.Room.Elements.Elements.AbstractUserAtHome;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Benutzer zu Hause
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserAtHome extends AbstractUserAtHome {

    /**
     * liest den Status des Elements
     * (die Ausführung kann bis zu 500ms dauern, diese Methode sollte Asynchron verwendet werden)
     *
     * @return Status
     */
    @Override
    public int readState() {

        int state;
        try {

            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            if(inetAddress.isReachable(500)) {

                state = ONLINE;
                updateLastContact();
            } else {

                state = OFFLINE;
            }

        } catch (IOException e) {

            state = OFFLINE;
        }
        return state;
    }
}
