package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import java.time.MonthDay;

/**
 * Datums Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DateCondition extends net.kleditzsch.shcCore.Automation.Conditions.DateCondition {

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

        if(startDate != null && endDate != null) {

            //Test Modus
            MonthDay today;
            if(testMode) {

                today = MonthDay.of(7, 19);
            } else {

                today = MonthDay.now();
            }

            //Datumsbereich prüfen
            if(startDate.isBefore(endDate)) {

                //start vor ende
                if((today.isAfter(startDate) && today.isBefore(endDate)) || today.equals(startDate) || today.equals(endDate)) {

                    return true;
                }
            } else {

                //ende vor start
                if((today.isBefore(startDate) && today.isBefore(endDate)) || today.equals(startDate) || today.equals(endDate)) {

                    return true;
                }
            }
        }
        return false;
    }
}
