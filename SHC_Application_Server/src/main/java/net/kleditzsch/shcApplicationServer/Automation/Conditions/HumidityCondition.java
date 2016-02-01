package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.Humidity;

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

            for (Humidity sensor : sensorList) {

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
