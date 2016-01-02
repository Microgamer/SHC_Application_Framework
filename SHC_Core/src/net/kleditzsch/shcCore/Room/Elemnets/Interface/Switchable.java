package net.kleditzsch.shcCore.Room.Elemnets.Interface;

/**
 * schaltbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Switchable extends StateElement {

    /**
     * setzt den Text der Buttons des schaltbaren Elements
     *
     * @param buttonOn  Text Button "an"
     * @param buttonOff Text Button "aus"
     */
    void setButtonText(String buttonOn, String buttonOff);
    /**
     * gibt den Text des "an" Button zurück
     *
     * @return Text
     */
    String getOnButtonText();

    /**
     * gibt den Text des "aus" Button zurück
     *
     * @return Text
     */
    String getOffButtonText();

    /**
     * schaltet zwichen den Zuständen um
     */
    void triggerToggle();

    /**
     * Aktion die bei Betätigung des "an" Buttons ausgeführt wird
     */
    void triggerOn();

    /**
     * Aktion die bei Betätigung des "aus" Buttons ausgeführt wird
     */
    void triggerOff();
}
