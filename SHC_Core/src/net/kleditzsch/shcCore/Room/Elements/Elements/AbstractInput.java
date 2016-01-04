package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractReadable;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;

/**
 * Eingang
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractInput extends AbstractReadable {

    /**
     * Schaltserver
     */
    protected SwitchServer switchServer;

    /**
     * Pin
     */
    protected int pin;

    /**
     * Invertierung
     */
    protected boolean invert = false;

    /**
     * gibt den Schaltserver zurück
     *
     * @return Schaltserver
     */
    public SwitchServer getSwitchServer() {
        return switchServer;
    }

    /**
     * setzt den Schaltserver
     *
     * @param switchServer Schaltserver
     */
    public void setSwitchServer(SwitchServer switchServer) {
        this.switchServer = switchServer;
    }

    /**
     * gibt den GPIO Pin zurück
     *
     * @return GPIO Pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * setzt den GPIO Pin
     *
     * @param pin GPIO Pin
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    /**
     * gibt an ob die Invertierung aktiviert/deaktiviert ist
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isInvert() {
        return invert;
    }

    /**
     * aktiviert/deaktiviert die Invertierung
     *
     * @param invert aktiviert/deaktiviert
     */
    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return INPUT;
    }
}
