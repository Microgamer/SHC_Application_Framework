package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.LightIntensity;

/**
 * Lichstärke Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LightIntensityCondition extends net.kleditzsch.shcCore.Automation.Conditions.LightIntensityCondition {

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

        if(sensorList.size() > 0) {

            for (LightIntensity sensor : sensorList) {

                if(!invert) {

                    //größer als
                    if(sensor.getLightIntensity() > limit) {

                        return true;
                    }
                } else {

                    //Kleiner als
                    if(sensor.getLightIntensity() < limit) {

                        return true;
                    }
                }
            }
        }
        return false;
    }
}
