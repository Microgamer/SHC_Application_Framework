package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.Devices.Readable.UserAtHome;
import net.kleditzsch.shcCore.Util.Constant;

/**
 * Benutzer zu Hause Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserAtHomeCondition extends net.kleditzsch.shcCore.Automation.Conditions.UserAtHomeCondition {

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

            for (UserAtHome readable : userAtHomeList) {

                //Deaktivierte überspringen
                if(readable.isDisabled()) {

                    continue;
                }

                //auf Status "1" prüfen
                if(readable.getState() == Constant.HIGH) {

                    return true;
                }
            }
        }
        return false;
    }
}
