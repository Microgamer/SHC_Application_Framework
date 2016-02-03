package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import net.kleditzsch.shcCore.Room.Elements.Interface.Switchable;
import net.kleditzsch.shcCore.Util.Constant;

/**
 * schaltbare Elemente Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SwitchableStateCondition extends net.kleditzsch.shcCore.Automation.Conditions.SwitchableStateCondition {

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

        if(switchableList.size() > 0) {

            for (Switchable switchable : switchableList) {

                if(!invert) {

                    //auf Status "1" prüfen
                    if(switchable.getState() == Constant.HIGH) {

                        return true;
                    }
                } else {

                    //auf Status "0" prüfen
                    if(switchable.getState() == Constant.LOW) {

                        return true;
                    }
                }
            }
        }
        return false;
    }
}
