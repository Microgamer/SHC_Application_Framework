package net.kleditzsch.shcCore.Room.Elemnets.Interface;

/**
 * Liste aller Raum Elemente
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface ViewElements {

    /**
     * Spezielle Elemente
     */
    int ROOM = 1;
    int BOX = 2;

    /**
     * schaltbare Elemente
     */
    int AVM_SOCKET = 8;
    int EDIMAX_SOCKET = 9;
    int FRITZ_BOX = 10;
    int RADIO_SOCKET = 4;
    int REBOOT = 11;
    int REMOTE_REBOOT = 12;
    int SHUTDOWN = 13;
    int REMOTE_SHUTDOWN = 14;
    int OUTPUT = 15;
    int SCRIPT = 16;

    /**
     * schaltbare Gruppenelemente
     */
    int ACTIVITY = 5;
    int COUNTDOWN = 6;

    /**
     * lesbare Elemente
     */
    int INPUT = 3;
    int USER_AT_HOME = 7;

    /**
     * Sensoren
     */
}
