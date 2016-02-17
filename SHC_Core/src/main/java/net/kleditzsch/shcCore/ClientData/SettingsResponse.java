package net.kleditzsch.shcCore.ClientData;

import net.kleditzsch.shcCore.Settings.BooleanSetting;
import net.kleditzsch.shcCore.Settings.DoubleSetting;
import net.kleditzsch.shcCore.Settings.IntegerSetting;
import net.kleditzsch.shcCore.Settings.StringSetting;

import java.util.HashMap;
import java.util.Map;

/**
 * Einstellungen Antwort
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SettingsResponse extends AbstractResponse {

    protected Map<String, StringSetting> stringSettings = new HashMap<>();
    protected Map<String, IntegerSetting> integerSettings = new HashMap<>();
    protected Map<String, DoubleSetting> doubleSettings = new HashMap<>();
    protected Map<String, BooleanSetting> booleanSettings = new HashMap<>();

    public Map<String, StringSetting> getStringSettings() {
        return stringSettings;
    }

    public Map<String, IntegerSetting> getIntegerSettings() {
        return integerSettings;
    }

    public Map<String, DoubleSetting> getDoubleSettings() {
        return doubleSettings;
    }

    public Map<String, BooleanSetting> getBooleanSettings() {
        return booleanSettings;
    }
}
