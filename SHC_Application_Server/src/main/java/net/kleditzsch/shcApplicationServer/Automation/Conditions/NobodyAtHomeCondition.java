package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import net.kleditzsch.shcCore.Room.Elements.Elements.AbstractUserAtHome;
import net.kleditzsch.shcCore.Util.Constant;

/**
 * Niemand zu Hause Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class NobodyAtHomeCondition extends net.kleditzsch.shcCore.Automation.Conditions.NobodyAtHomeCondition {

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

            for (AbstractUserAtHome userAtHome : userAtHomeList) {

                if(userAtHome.isEnabled() && userAtHome.getState() == Constant.HIGH) {

                    return false;
                }
            }
        }
        return true;
    }
}
