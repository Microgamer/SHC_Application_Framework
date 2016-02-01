package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.Moisture;

/**
 * Feuchtigkeits Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class MoistureCondition extends net.kleditzsch.shcCore.Automation.Conditions.MoistureCondition {

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

            for (Moisture sensor : sensorList) {

                if(!invert) {

                    //größer als
                    if(sensor.getMoisture() > limit) {

                        return true;
                    }
                } else {

                    //Kleiner als
                    if(sensor.getMoisture() < limit) {

                        return true;
                    }
                }
            }
        }
        return false;
    }
}
