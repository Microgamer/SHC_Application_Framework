package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.AbstractCondition;

import java.time.MonthDay;

/**
 * Datums Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DateCondition extends AbstractCondition {

    /**
     * start Datum
     */
    protected MonthDay startDate;

    /**
     * end Datum
     */
    protected MonthDay endDate;

    /**
     * gibt das start Datum zurück
     *
     * @return start Datum
     */
    public MonthDay getStartDate() {
        return startDate;
    }

    /**
     * setzt das start Datum
     *
     * @param startDate start Datum
     */
    public void setStartDate(MonthDay startDate) {
        this.startDate = startDate;
    }

    /**
     * gibt das end Datum zurück
     *
     * @return end Datum
     */
    public MonthDay getEndDate() {
        return endDate;
    }

    /**
     * setzt das end Datum
     *
     * @param endDate end Datum
     */
    public void setEndDate(MonthDay endDate) {
        this.endDate = endDate;
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

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return DATE_CONDITION;
    }
}
