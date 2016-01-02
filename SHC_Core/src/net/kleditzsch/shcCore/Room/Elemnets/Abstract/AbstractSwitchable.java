package net.kleditzsch.shcCore.Room.Elemnets.Abstract;

import net.kleditzsch.shcCore.Room.Elemnets.Interface.Switchable;
import net.kleditzsch.shcCore.Util.Constant;

/**
 * schaltbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractSwitchable extends AbstractStateElement implements Switchable {

    /**
     * Button Text
     */
    protected String buttonTextOn;
    protected String buttonTextOff;

    /**
     * setzt den Text der Buttons des schaltbaren Elements
     *
     * @param buttonOn  Text Button "an"
     * @param buttonOff Text Button "aus"
     */
    public void setButtonText(String buttonOn, String buttonOff) {

        buttonTextOn = buttonOn;
        buttonTextOff = buttonOff;
    }

    /**
     * gibt den Text des "an" Button zurück
     *
     * @return Text
     */
    public String getOnButtonText() {

        return buttonTextOn;
    }

    /**
     * gibt den Text des "aus" Button zurück
     *
     * @return Text
     */
    public String getOffButtonText() {

        return buttonTextOff;
    }

    /**
     * schaltet zwichen den Zuständen um
     */
    public void triggerToggle() {

        if(state == Constant.ON) {

            triggerOff();
        } else {

            triggerOn();
        }
    }
}
