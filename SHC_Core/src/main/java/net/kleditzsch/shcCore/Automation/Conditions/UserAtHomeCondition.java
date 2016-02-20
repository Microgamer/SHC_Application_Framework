package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.Interface.Condition.AbstractCondition;
import net.kleditzsch.shcCore.Automation.Devices.Readable.UserAtHome;

import java.util.HashSet;
import java.util.Set;

/**
 * Benutzer zu Hause Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserAtHomeCondition extends AbstractCondition {

    /**
     * liste mit allen Sensoren die überwacht werden sollen
     */
    protected final Set<UserAtHome> userAtHomeList = new HashSet<>();

    /**
     * gibt die Sensoren Liste zurück
     *
     * @return Sensoren Liste
     */
    public Set<UserAtHome> getUserAtHomeList() {
        return userAtHomeList;
    }

    /**
     * gibt an ob die Bedingung erfuellt ist
     *
     * @return true wenn die Bedingung erfüllt ist
     */
    @Override
    public boolean isSatisfies() {
        return false;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return USER_AT_HOME_CONDITION;
    }
}
