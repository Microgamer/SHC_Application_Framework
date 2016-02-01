package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import net.kleditzsch.shcApplicationServer.DateTime.Sunrise;
import net.kleditzsch.shcApplicationServer.DateTime.Sunset;

import java.time.LocalTime;

/**
 * Nacht Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class NightCondition extends net.kleditzsch.shcCore.Automation.Conditions.NightCondition {

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

        LocalTime sunrise = Sunrise.now();
        LocalTime sunset = Sunset.now();
        LocalTime now = LocalTime.now();

        if(!((now.isAfter(sunrise) && now.isBefore(sunset)) || now.equals(sunrise))) {

            return true;
        }
        return false;
    }
}
