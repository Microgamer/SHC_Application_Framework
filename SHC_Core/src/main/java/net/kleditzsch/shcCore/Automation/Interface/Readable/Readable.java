package net.kleditzsch.shcCore.Automation.Interface.Readable;

import net.kleditzsch.shcCore.Automation.AutomationElements;
import net.kleditzsch.shcCore.Core.Element;
import net.kleditzsch.shcCore.Util.Constant;

/**
 * lesbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Readable extends AutomationElements, Constant, Element {

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
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    int getType();
}
