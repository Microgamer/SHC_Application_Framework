package net.kleditzsch.shcCore.Automation.Interface.Switchable;

import net.kleditzsch.shcCore.Automation.AutomationElements;
import net.kleditzsch.shcCore.Core.Element;
import net.kleditzsch.shcCore.Util.Constant;

import java.time.LocalDateTime;

/**
 * schaltbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Switchable extends AutomationElements, Constant, Element {

    /**
     * gibt an ob das ELement deaktiviert ist
     *
     * @return true wenn deaktiviert
     */
    boolean isDisabled();

    /**
     * aktiviert/deaktiviert das Element
     *
     * @param disabled aktiviert/deaktiviert
     */
    void setDisabled(boolean disabled);

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
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    int getType();
}
