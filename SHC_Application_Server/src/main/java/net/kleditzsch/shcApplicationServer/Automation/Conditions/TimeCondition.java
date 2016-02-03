package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import java.time.LocalTime;

/**
 * Zeit Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class TimeCondition extends net.kleditzsch.shcCore.Automation.Conditions.TimeCondition {

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

        LocalTime now = LocalTime.now();
        if(startTime.isBefore(endTime)) {

            System.out.println("start vor ende");
            if((now.isAfter(startTime) && now.isBefore(endTime)) || now.equals(startTime) || now.equals(endTime)) {

                return true;
            }
        } else {

            System.out.println("ende vor start");
            if((now.isBefore(endTime) && now.isBefore(startTime)) || now.equals(startTime) || now.equals(endTime)) {

                return true;
            }
        }
        return false;
    }
}
