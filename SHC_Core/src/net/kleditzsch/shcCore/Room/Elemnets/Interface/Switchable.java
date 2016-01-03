package net.kleditzsch.shcCore.Room.Elemnets.Interface;

import java.time.LocalDateTime;

/**
 * schaltbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Switchable extends StateElement {

    /**
     * gibt an ob die Anzeige des Status der schaltbaren Elemente aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    boolean isShowStateEnabled();

    /**
     * aktiviert/deaktiviert die Anzeige des Status der schaltbaren Elemente
     *
     * @param showStateEnabled aktiviert/deaktiviert
     */
    void setShowStateEnabled(boolean showStateEnabled);

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

    /**
     * gibt die Betriebszeit in Sekunden zurück
     *
     * @return Betriebszeit
     */
    long getOperatingSeconds();

    /**
     * setzt die Betriebszeit in Sekunden
     *
     * @param operatingSeconds Betriebszeit
     */
    void setOperatingSeconds(long operatingSeconds);

    /**
     * gibt den aktuellen Energieverbrauch zurück
     *
     * @return Energieverbrauch in W
     */
    double getActualPower();

    /**
     * setzt den aktuellen Energieverbrauch
     *
     * @param actualPower Energieverbrauch in W
     */
    void setActualPower(double actualPower);

    /**
     * gibt den Energieverbrauch zurück
     *
     * @return Energieverbrauch in Wh
     */
    double getEnergy();

    /**
     * setzt den Energieverbrauch
     *
     * @param energy Energieverbrauch in Wh
     */
    void setEnergy(double energy);

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
