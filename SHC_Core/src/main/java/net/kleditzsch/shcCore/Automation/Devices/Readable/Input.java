package net.kleditzsch.shcCore.Automation.Devices.Readable;

import net.kleditzsch.shcCore.Automation.Interface.Readable.AbstractReadable;

/**
 * Eingang
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Input extends AbstractReadable {

    /**
     * Schaltserver (wenn leer, daten über Sensorschnittstelle)
     */
    protected String switchServerHash;

    /**
     * Pin
     */
    protected int pin;

    /**
     * Invertierung
     */
    protected boolean inverse = false;

    /**
     * gibt an ob Ping verwendet werden soll oder ob der Status von Außen kommt
     */
    protected boolean useExternalData = false;

    /**
     * Typ
     */
    protected int type = INPUT;

    /**
     * gibt den Schaltserver zurück
     *
     * @return Schaltserver
     */
    public String getSwitchServer() {
        return switchServerHash;
    }

    /**
     * setzt den Schaltserver
     *
     * @param switchServerHash Schaltserver
     */
    public void setSwitchServer(String switchServerHash) {
        this.switchServerHash = switchServerHash;
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
    public boolean isInverse() {
        return inverse;
    }

    /**
     * aktiviert/deaktiviert die Invertierung
     *
     * @param inverse aktiviert/deaktiviert
     */
    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }

    /**
     * gibt an ob der Eingang durch externe Daten gesetzt werden soll
     *
     * @return true die Daten von außen kommen sollen
     */
    public boolean isUseExternalData() {
        return useExternalData;
    }

    /**
     * aktiviert/deaktiviert die interne Abfrage des EIngangs
     *
     * @param useExternalData true die Daten von außen kommen sollen
     */
    public void setUseExternalData(boolean useExternalData) {
        this.useExternalData = useExternalData;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return type;
    }
}
