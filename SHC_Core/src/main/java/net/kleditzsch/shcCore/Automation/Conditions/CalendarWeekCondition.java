package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.AbstractCondition;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Kalenderwoche Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class CalendarWeekCondition extends AbstractCondition {

    /**
     * gibt an ob die Bedingung invertiert ist
     */
    protected boolean invert = false;

    /**
     * gibt an ob die Bedingung Invertiert ist
     *
     * @return Invertiert
     */
    public boolean isInvert() {
        return invert;
    }

    /**
     * setzt die Invertierung der Bedingung
     *
     * @param invert Invertiert
     */
    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    /**
     * gibt an ob die Bedingung erfuellt ist
     *
     * @return true wenn die Bedingung erfüllt ist
     */
    @Override
    public boolean isSatisfies() {

        //prüfen ob deaktiviert
        if(!isEnabled()) {

            return true;
        }

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int week = LocalDate.now().get(weekFields.weekOfWeekBasedYear());
        if(!invert) {

            //Gerade Kalenderwoche
            if(week % 2 == 0) {

                return true;
            }
        } else {

            //Ungerade Kalenderwoche
            if(week % 2 != 0) {

                return true;
            }
        }
        return false;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return CALENDAR_WEEK_CONDITION;
    }
}
