package net.kleditzsch.shcCore.Automation.Devices.Switchable;

import net.kleditzsch.shcCore.Automation.Interface.Switchable.AbstractDoubleSwitchable;

/**
 * AVM Steckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AvmSocket extends AbstractDoubleSwitchable {

    /**
     * Identifizierung
     */
    protected String identifier;

    /**
     * Typ
     */
    protected int type = AVM_SOCKET;

    /**
     * gibt die Identifizierung der Steckdose zurück
     *
     * @return Identifizierung
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * setzt die Identifizierung der Steckdose
     *
     * @param identifier Identifizierung
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier.replace(" ", "");
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
