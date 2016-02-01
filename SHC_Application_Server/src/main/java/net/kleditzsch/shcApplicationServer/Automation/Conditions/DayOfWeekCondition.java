package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Wochentag Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DayOfWeekCondition extends net.kleditzsch.shcCore.Automation.Conditions.DayOfWeekCondition {

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

        LocalDate today;
        if(testMode) {

            today = LocalDate.of(2016, 7, 19);
        } else {

            today = LocalDate.now();
        }

        if(startDay < endDay) {

            //start Tag vor dem End Tag
            LocalDate startDayDate = today.with(TemporalAdjusters.previousOrSame(getDayOfWeek(startDay)));
            LocalDate endDayDate = today.with(TemporalAdjusters.previousOrSame(getDayOfWeek(endDay)));
            if(endDayDate.isBefore(startDayDate)) {

                endDayDate = today.with(TemporalAdjusters.nextOrSame(getDayOfWeek(endDay)));
            }

            if((today.isAfter(startDayDate) && today.isBefore(endDayDate)) || today.equals(startDayDate) || today.equals(endDayDate)) {

                return true;
            }

        } else if(startDay > endDay) {

            //start Tag nach dem End Tag
            LocalDate startDayDate = today.with(TemporalAdjusters.previousOrSame(getDayOfWeek(startDay)));
            LocalDate endDayDate = today.with(TemporalAdjusters.nextOrSame(getDayOfWeek(endDay)));
            if(endDayDate.isAfter(today.plusDays(7 - startDay))) {

                endDayDate = today.with(TemporalAdjusters.previousOrSame(getDayOfWeek(endDay)));
            }

            if((today.isAfter(startDayDate) && today.isBefore(endDayDate)) || today.equals(startDayDate) || today.equals(endDayDate)) {

                return true;
            }
        } else {

            //Start Tag und end Tag gleich
            if(today.getDayOfWeek().equals(getDayOfWeek(startDay))) {

                return true;
            }
        }
        return false;
    }
}
