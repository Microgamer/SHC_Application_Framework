package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import net.kleditzsch.shcCore.Room.Elements.Interface.Readable;
import net.kleditzsch.shcCore.Util.Constant;

/**
 * Eingangs Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class InputCondition extends net.kleditzsch.shcCore.Automation.Conditions.InputCondition {

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

        if(readableList.size() > 0) {

            for (Readable readable : readableList) {

                if(!invert) {

                    //auf Status "1" prüfen
                    if(readable.getState() == Constant.HIGH) {

                        return true;
                    }
                } else {

                    //auf Status "0" prüfen
                    if(readable.getState() == Constant.LOW) {

                        return true;
                    }
                }
            }
        }
        return false;
    }
}
