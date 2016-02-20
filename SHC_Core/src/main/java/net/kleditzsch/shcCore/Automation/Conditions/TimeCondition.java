package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.Interface.Condition.AbstractCondition;

import java.time.LocalTime;

/**
 * Zeit Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class TimeCondition extends AbstractCondition {

    /**
     * Start & End-Zeit
     */
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * gibt die Startzeit zurück
     *
     * @return Startzeit
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * setzt die Startzeit
     *
     * @param startTime Startzeit
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * gibt die Endzeit zurück
     *
     * @return Endzeit
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * setzt die Endzeit
     *
     * @param endTime Endzeit
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * gibt an ob die Bedingung erfuellt ist
     *
     * @return true wenn die Bedingung erfüllt ist
     */
    @Override
    public boolean isSatisfies() {
        return false;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return TIME_CONDITION;
    }
}
