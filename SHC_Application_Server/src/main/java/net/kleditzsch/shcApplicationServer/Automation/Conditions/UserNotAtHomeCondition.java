package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import net.kleditzsch.shcCore.Room.Elements.Elements.AbstractUserAtHome;
import net.kleditzsch.shcCore.Util.Constant;

/**
 * Benutzer nicht zu Hause Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserNotAtHomeCondition extends net.kleditzsch.shcCore.Automation.Conditions.UserNotAtHomeCondition {

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

        if(userAtHomeList.size() > 0) {

            for (AbstractUserAtHome readable : userAtHomeList) {

                //Deaktivierte überspringen
                if(!readable.isEnabled()) {

                    continue;
                }

                //auf Status "1" prüfen
                if(readable.getState() == Constant.LOW) {

                    return true;
                }
            }
        }
        return false;
    }
}
