package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.Devices.SensorValue.HumidityValue;

/**
 * Luftfeuchte Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class HumidityCondition extends net.kleditzsch.shcCore.Automation.Conditions.HumidityCondition {

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

            for (HumidityValue sensor : sensorList) {

                //Deaktivierte überspringen
                if(sensor.isDisabled()) {

                    continue;
                }

                if(!invert) {

                    //größer als
                    if(sensor.getHumidity() > limit) {

                        return true;
                    }
                } else {

                    //Kleiner als
                    if(sensor.getHumidity() < limit) {

                        return true;
                    }
                }
            }
        }
        return false;
    }
}
