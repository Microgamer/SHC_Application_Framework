package net.kleditzsch.shcCore.Automation.Interface.Sensor;

import net.kleditzsch.shcCore.Automation.AutomationElements;
import net.kleditzsch.shcCore.Core.Element;
import net.kleditzsch.shcCore.Util.Constant;

import java.time.LocalDateTime;

/**
 * SensorWert
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface SensorValue extends AutomationElements, Constant, Element{

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
     * gibt den Identifizierer zurück
     *
     * @return Identifizierer
     */
    public String getIdentifier();

    /**
     * setzt den Identifizierer
     *
     * @param identifier Identifizierer
     */
    public void setIdentifier(String identifier);

    /**
     * gibt an ob der Sensorwert ein Systemwert ist (kann nicht gelöscht werden)
     *
     * @return true wenn Systemwert
     */
    boolean isSystemValue();

    /**
     * setzt den Sensorwert als Systemwert
     *
     * @param systemValue Systemwert
     */
    void setSystemValue(boolean systemValue);

    /**
     * gibt die Zeit des letzten Pushs zurück
     *
     * @return Zeit
     */
    LocalDateTime getLastPushTime();

    /**
     * setzt die Zeit des letzen Pushs
     *
     * @param lastPushTime Zeit
     */
    void setLastPushTime(LocalDateTime lastPushTime);

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    String getDisplayValue();

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    int getType();
}
