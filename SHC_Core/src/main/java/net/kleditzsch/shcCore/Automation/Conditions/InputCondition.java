package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.AbstractCondition;
import net.kleditzsch.shcCore.Room.Elements.Interface.Readable;
import net.kleditzsch.shcCore.Util.Constant;

import java.util.HashSet;
import java.util.Set;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class InputCondition extends AbstractCondition {

    /**
     * gibt an ob die Bedingung invertiert ist
     */
    protected boolean invert = false;

    /**
     * liste mit allen Eingängen die überwacht werden sollen
     */
    protected final Set<Readable> readableList = new HashSet<>();

    /**
     * gibt an ob die Bedingung Invertiert ist
     *
     * @return Invertiert
     */
    public boolean isInvert() {
        return invert;
    }

    /**
     * setzt die Invertierung der Bedingung
     *
     * @param invert Invertiert
     */
    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    /**
     * gibt die Sensoren Liste zurück
     *
     * @return Sensoren Liste
     */
    public Set<Readable> getReadableList() {
        return readableList;
    }

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

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return INPUT_CONDITION;
    }
}
