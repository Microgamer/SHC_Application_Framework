package net.kleditzsch.AVM.FritzBox.SmartHome.Elements;

import net.kleditzsch.AVM.FritzBox.SmartHome.FritzBoxHandler;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * AVM schaltbare Steckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Switch {

    /**
     * Verbindungs Handler
     */
    protected FritzBoxHandler fritzBoxHandler = null;

    /**
     * Daten
     */
    protected String identifier = "";
    protected int state = 0;
    protected String mode = "manuell";
    protected boolean locked = false;

    /**
     * @param fritzBoxHandler Verbindungs Handler
     * @param identifier Identifizierung
     */
    public Switch(FritzBoxHandler fritzBoxHandler, String identifier) {
        this.fritzBoxHandler = fritzBoxHandler;
        this.identifier = identifier;
    }

    /**
     * gibt den Status zurück
     *
     * @return Status
     */
    public int getState() {
        return state;
    }

    /**
     * setzt den Status
     *
     * @param state Status
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * gibt den Modus zurück
     *
     * @return Modus
     */
    public String getMode() {
        return mode;
    }

    /**
     * setzt den Modus
     *
     * @param mode Modus
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * gibt an ob das Gerät geschalten werden kann
     *
     * @return aktiviert/deaktiviert
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * aktiviert/deaktiviert das Schalten
     *
     * @param locked  aktiviert/deaktiviert
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * schaltet die Steckdose ein
     *
     * @return erfolg
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public boolean switchOn() throws IOException, NoSuchAlgorithmException {

        if(!isLocked()) {

            String response = fritzBoxHandler.sendHttpRequest("/webservices/homeautoswitch.lua?switchcmd=setswitchon&ain=" + identifier);
            if(response.trim().equals("1")) {

                return true;
            }
        }
        return false;
    }

    /**
     * schaltet die Steckdose aus
     *
     * @return erfolg
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public boolean switchOff() throws IOException, NoSuchAlgorithmException {

        if(!isLocked()) {

            String response = fritzBoxHandler.sendHttpRequest("/webservices/homeautoswitch.lua?switchcmd=setswitchoff&ain=" + identifier);
            if(response.trim().equals("0")) {

                return true;
            }
        }
        return false;
    }

    public boolean switchToggle() throws IOException, NoSuchAlgorithmException {

        if(!isLocked()) {

            String response = fritzBoxHandler.sendHttpRequest("/webservices/homeautoswitch.lua?switchcmd=setswitchtoggle&ain=" + identifier);
            if(response.trim().equals("1") || response.trim().equals("0")) {

                return true;
            }
        }
        return false;
    }
}
