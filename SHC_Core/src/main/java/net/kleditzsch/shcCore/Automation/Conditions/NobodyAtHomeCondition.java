package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.AbstractCondition;
import net.kleditzsch.shcCore.Room.Elements.Elements.AbstractUserAtHome;
import net.kleditzsch.shcCore.Util.Constant;

import java.util.HashSet;
import java.util.Set;

/**
 * Niemand zu Hause Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class NobodyAtHomeCondition extends AbstractCondition {

    /**
     * liste mit allen Sensoren die überwacht werden sollen
     */
    protected final Set<AbstractUserAtHome> userAtHomeList = new HashSet<>();

    /**
     * gibt die Liste mit den Benutzern zu Hause zurück
     *
     * @return Liste mit den Benutzern zu Hause
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
        return NOBODY_AT_HOME_CONDITION;
    }
}
