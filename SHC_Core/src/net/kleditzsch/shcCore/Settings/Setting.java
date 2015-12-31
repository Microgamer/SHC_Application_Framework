package net.kleditzsch.shcCore.Settings;

/**
 * Einstellung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Setting<T> {

    /**
     * Name der Einstellung
     */
    private String name;

    /**
     * Wert der Einstellung
     */
    private T value;

    /**
     * Standard der Einstellung
     */
    private T defaultValue;

    public Setting() {}

    /**
     * @param name
     * @param value
     * @param defaultValue
     */
    public Setting(String name, T value, T defaultValue) {
        this.name = name;
        this.value = value;
        this.defaultValue = defaultValue;
    }

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
     * gibt den Wert der Einstellung zurück
     *
     * @return Wert
     */
    public T getValue() {
        return value;
    }

    /**
     * setzt den Wert der Einstellung
     *
     * @param value Wert
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * gibt den Standardwert zurück
     *
     * @return Standardwert
     */
    public T getDefaultValue() {
        return defaultValue;
    }

    /**
     * setzt den Standardwert
     *
     * @param defaultValue Standardwert
     */
    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }
}
