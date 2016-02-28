package net.kleditzsch.shcCore.Settings.Interface;

/**
 * Einstellung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Setting {

    /**
     * Einstellungstypen
     */
    int TYPE_STRING = 1;
    int TYPE_INTEGER = 2;
    int TYPE_DOUBLE = 3;
    int TYPE_BOOLEAN = 4;
    int TYPE_LIST = 5;

    /**
     * gibt den Namen der Einstellung zurück
     *
     * @return Name
     */
    String getName();

    /**
     * setzt den Namen der Einstellung
     *
     * @param name Name
     */
    void setName(String name);

    /**
     * gibt den Typ der Einstellung zurück
     *
     * @return Typ
     */
    int getType();
}
