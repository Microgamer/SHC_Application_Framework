package net.kleditzsch.shcCore.Room.Elements;

import net.kleditzsch.shcCore.Room.Abstract.AbstractRoomElement;

/**
 * Lesbares Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ReadableElement extends AbstractRoomElement {

    /**
     * lesbares Element
     */
    protected String readableHash = "";

    /**
     * gibt den Hash des lesbaren Elements zurück
     *
     * @return HasH
     */
    public String getReadableHash() {
        return readableHash;
    }

    /**
     * setzt den Hash des lesbaren Elements
     *
     * @param readableHash Hash
     */
    public void setReadableHash(String readableHash) {
        this.readableHash = readableHash;
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
