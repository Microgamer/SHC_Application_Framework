package net.kleditzsch.shcCore.Settings;

import net.kleditzsch.shcCore.Settings.Interface.Setting;

import java.util.ArrayList;
import java.util.List;

/**
 * Listen Einstellung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ListSetting implements Setting {

    /**
     * Name der Einstellung
     */
    private String name;

    /**
     * Wert der Einstellung
     */
    private List<String> value = new ArrayList<>();

    /**
     * Standard der Einstellung
     */
    private List<String> defaultValue = new ArrayList<>();

    /**
     * gibt den Namen der Einstellung zurück
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * setzt den Namen der Einstellung
     *
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gibt die Liste der Zeichenketten zurück
     *
     * @return Liste der Zeichenketten
     */
    public List<String> getValue() {
        return value;
    }

    /**
     * setzt die Werteliste
     *
     * @param value Werteliste
     */
    public void setValue(List<String> value) {
        this.value = value;
    }

    /**
     * gibt die Standard Liste der Zeichenketten zurück
     *
     * @return Standard Liste der Zeichenketten
     */
    public List<String> getDefaultValue() {
        return defaultValue;
    }

    /**
     * setzt die Liste der Standardwerte
     *
     * @param defaultValue Standardwerte
     */
    public void setDefaultValue(List<String> defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * gibt den Typ der Einstellung zurück
     *
     * @return Typ
     */
    @Override
    public int getType() {
        return TYPE_LIST;
    }
}
