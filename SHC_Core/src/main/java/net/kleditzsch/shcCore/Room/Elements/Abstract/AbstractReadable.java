package net.kleditzsch.shcCore.Room.Elements.Abstract;


import net.kleditzsch.shcCore.Room.Elements.Interface.Readable;
import net.kleditzsch.shcCore.Util.Constant;

/**
 * lesbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractReadable extends AbstractStateElement implements Readable {

    /**
     * Status
     */
    protected int state = Constant.OFF;

    /**
     * gibt den aktuellen Status zurück
     *
     * @return Status
     */
    public int getState() {
        return state;
    }

    /**
     * setzt den aktuellen Status
     *
     * @param state Status
     */
    public void setState(int state) {
        this.state = state;
    }
}
