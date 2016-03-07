package net.kleditzsch.shcCore.Automation.Interface;

import net.kleditzsch.shcCore.Automation.AutomationElements;
import net.kleditzsch.shcCore.Core.Element;
import net.kleditzsch.shcCore.Util.Constant;

/**
 * Automations Gerät
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface AutomationDevice extends AutomationElements, Constant, Element {

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
     * gibt den Kommentar zurück
     *
     * @return Kommentar
     */
    String getComment();

    /**
     * setzt den Kommentar
     *
     * @param comment Kommentar
     */
    void setComment(String comment);

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    int getType();
}
