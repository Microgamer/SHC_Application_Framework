package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.DateTime.Holiday;
import net.kleditzsch.DateTime.Holidays.GermanHolidays;
import net.kleditzsch.shcCore.Automation.AbstractCondition;

import java.time.LocalDate;

/**
 * Feiertage Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class HolidaysCondition extends AbstractCondition {

    /**
     * gibt an ob die Bedingung invertiert ist
     */
    protected boolean invert = false;

    /**
     * Feiertage
     */
    protected int holidays = 0;

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
     * gibt die Feiertage zurück
     *
     * @return Feiertage
     */
    public int getHolidays() {
        return holidays;
    }

    /**
     * setzt die Feiertage
     *
     * @param holidays Feiertage
     */
    public void setHolidays(int holidays) {
        this.holidays = holidays;
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

        if(holidays > 0) {

            LocalDate today;
            if(testMode) {

                today = LocalDate.of(2016, 7, 19);
            } else {

                today = LocalDate.now();
            }
            GermanHolidays germanHolidays = Holiday.getGermanHolidays();
            if(
                    ((holidays & GermanHolidays.NEW_YEARS_DAY) == GermanHolidays.NEW_YEARS_DAY && germanHolidays.newYearsDay().isEqual(today))
                    || ((holidays & GermanHolidays.EPIPHANY) == GermanHolidays.EPIPHANY && germanHolidays.epiphany().isEqual(today))
                    || ((holidays & GermanHolidays.MAUNDY_THURSDAY) == GermanHolidays.MAUNDY_THURSDAY && germanHolidays.maundyThursday().isEqual(today))
                    || ((holidays & GermanHolidays.GOOD_FRIDAY) == GermanHolidays.GOOD_FRIDAY && germanHolidays.goodFriday().isEqual(today))
                    || ((holidays & GermanHolidays.EASTER_DAY) == GermanHolidays.EASTER_DAY && germanHolidays.epiphany().isEqual(today))
                    || ((holidays & GermanHolidays.EASTER_MONDAY) == GermanHolidays.EASTER_MONDAY && germanHolidays.easterMonday().isEqual(today))
                    || ((holidays & GermanHolidays.DAY_OF_WORK) == GermanHolidays.DAY_OF_WORK && germanHolidays.dayOfWork().isEqual(today))
                    || ((holidays & GermanHolidays.ASCENSION_DAY) == GermanHolidays.ASCENSION_DAY && germanHolidays.ascensionDay().isEqual(today))
                    || ((holidays & GermanHolidays.WHIT_SUN) == GermanHolidays.WHIT_SUN && germanHolidays.whitsun().isEqual(today))
                    || ((holidays & GermanHolidays.WHIT_MONDAY) == GermanHolidays.WHIT_MONDAY && germanHolidays.whitMonday().isEqual(today))
                    || ((holidays & GermanHolidays.CORPUS_CHRISTI) == GermanHolidays.CORPUS_CHRISTI && germanHolidays.corpusChristi().isEqual(today))
                    || ((holidays & GermanHolidays.ASSUMPTION) == GermanHolidays.ASSUMPTION && germanHolidays.assumption().isEqual(today))
                    || ((holidays & GermanHolidays.GERMAN_UNIFICATION_DAY) == GermanHolidays.GERMAN_UNIFICATION_DAY && germanHolidays.germanUnificationDay().isEqual(today))
                    || ((holidays & GermanHolidays.REFOMATION_DAY) == GermanHolidays.REFOMATION_DAY && germanHolidays.reformationDay().isEqual(today))
                    || ((holidays & GermanHolidays.ALL_SAINTS_DAY) == GermanHolidays.ALL_SAINTS_DAY && germanHolidays.allSaintsDay().isEqual(today))
                    || ((holidays & GermanHolidays.DAY_OF_REPENTANCE) == GermanHolidays.DAY_OF_REPENTANCE && germanHolidays.dayOfRepentance().isEqual(today))
                    || ((holidays & GermanHolidays.CHRISTMAS_DAY) == GermanHolidays.CHRISTMAS_DAY && germanHolidays.christmasDay().isEqual(today))
                    || ((holidays & GermanHolidays.XMAS_DAY) == GermanHolidays.XMAS_DAY && germanHolidays.xmasDay().isEqual(today))
                    || ((holidays & GermanHolidays.BOXING_DAY) == GermanHolidays.BOXING_DAY && germanHolidays.boxingDay().isEqual(today))
                    || ((holidays & GermanHolidays.NEW_YEARS_EVE) == GermanHolidays.NEW_YEARS_EVE && germanHolidays.newYearsEve().isEqual(today))
                    ) {

                if(!invert) {

                    return true;
                }
                return false;
            }
        }
        if(!invert) {

            return false;
        }
        return true;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return HOLIDAYS_CONDITION;
    }
}
