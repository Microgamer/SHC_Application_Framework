package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.AbstractCondition;
import net.kleditzsch.shcCore.Room.Elements.Elements.AbstractUserAtHome;

import java.util.HashSet;
import java.util.Set;

/**
 * Benutzer nicht zu Hause Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserNotAtHomeCondition extends AbstractCondition {

    /**
     * liste mit allen Sensoren die überwacht werden sollen
     */
    protected final Set<AbstractUserAtHome> userAtHomeList = new HashSet<>();

    /**
     * gibt die Sensoren Liste zurück
     *
     * @return Sensoren Liste
     */
    public Set<AbstractUserAtHome> getUserAtHomeList() {
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
        return USER_NOT_AT_HOME_CONDITION;
    }
}
