package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.AbstractCondition;

import java.time.DayOfWeek;

/**
 * Wochentag Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DayOfWeekCondition extends AbstractCondition {

    /**
     * start und end Tag
     */
    protected int startDay = 1;
    protected int endDay = 5;

    /**
     * gibt den start Tag zurück
     *
     * @return start Tag
     */
    public int getStartDay() {
        return startDay;
    }

    /**
     * setzt den start Tag
     *
     * @param startDay start Tag
     */
    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    /**
     * gibt den end Tag zurück
     *
     * @return end Tag
     */
    public int getEndDay() {
        return endDay;
    }

    /**
     * setzt den end Tag
     *
     * @param endDay end Tag
     */
    public void setEndDay(int endDay) {
        this.endDay = endDay;
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
     * gibt das Wochentags Objekt zurück
     *
     * @param day Tag
     * @return Wochentags Objekt
     */
    protected DayOfWeek getDayOfWeek(int day) {

        switch(day) {

            case MONDAY:

                return DayOfWeek.MONDAY;
            case TUESDAY:

                return DayOfWeek.TUESDAY;
            case WEDNESDAY:

                return DayOfWeek.WEDNESDAY;
            case THURSDAY:

                return DayOfWeek.THURSDAY;
            case FRIDAY:

                return DayOfWeek.FRIDAY;
            case SATURDAY:

                return DayOfWeek.SATURDAY;
            case SUNDAY:

                return DayOfWeek.SUNDAY;
        }
        return DayOfWeek.MONDAY;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return DAY_OF_WEEK_CONDITION;
    }
}
