package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.DeviceManager.ClientDevice;
import net.kleditzsch.shcApplicationServer.DeviceManager.DeviceManager;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.Settings.Settings;
import net.kleditzsch.shcCore.ClientData.Device.DeviceData;
import net.kleditzsch.shcCore.ClientData.Device.DeviceResponse;
import net.kleditzsch.shcCore.ClientData.SettingsResponse;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;

import java.util.Map;

/**
 * Einstellungen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SettingsRequestHandler extends AbstractRequestHandler {

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson   Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        if(params.containsKey("action")) {

            SettingsResponse settingsResponse;
            SuccessResponse successResponse;
            User sessionUser = checkSession(params);
            switch (params.get("action")) {

                case "listsettings":

                    settingsResponse = new SettingsResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.SETTINGS_ADMINISTRATION)) {

                            //Einstellungen zusammenstellen
                            Settings settings = ShcApplicationServer.getInstance().getSettings();

                            settingsResponse.getIntegerSettings().put(Settings.SETTING_SUNRISE_OFFSET, settings.getIntegerSetting(Settings.SETTING_SUNRISE_OFFSET));
                            settingsResponse.getIntegerSettings().put(Settings.SETTING_SUNSET_OFFSET, settings.getIntegerSetting(Settings.SETTING_SUNSET_OFFSET));
                            settingsResponse.getDoubleSettings().put(Settings.SETTING_LATITUDE, settings.getDoubleSetting(Settings.SETTING_LATITUDE));
                            settingsResponse.getDoubleSettings().put(Settings.SETTING_LONGITUDE, settings.getDoubleSetting(Settings.SETTING_LONGITUDE));

                            settingsResponse.getBooleanSettings().put(Settings.SETTING_FRITZBOX_ACTIVE, settings.getBooleanSetting(Settings.SETTING_FRITZBOX_ACTIVE));
                            settingsResponse.getStringSettings().put(Settings.SETTING_FRITZBOX_ADDRESS, settings.getStringSetting(Settings.SETTING_FRITZBOX_ADDRESS));
                            settingsResponse.getStringSettings().put(Settings.SETTING_FRITZBOX_USER, settings.getStringSetting(Settings.SETTING_FRITZBOX_USER));
                            settingsResponse.getStringSettings().put(Settings.SETTING_FRITZBOX_PASSWORD, settings.getStringSetting(Settings.SETTING_FRITZBOX_PASSWORD));

                            settingsResponse.getDoubleSettings().put(Settings.SETTING_ENERGY_ELECTRIC_PRICE, settings.getDoubleSetting(Settings.SETTING_ENERGY_ELECTRIC_PRICE));
                            settingsResponse.getDoubleSettings().put(Settings.SETTING_ENERGY_WATER_PRICE, settings.getDoubleSetting(Settings.SETTING_ENERGY_WATER_PRICE));
                            settingsResponse.getDoubleSettings().put(Settings.SETTING_ENERGY_GAS_PRICE, settings.getDoubleSetting(Settings.SETTING_ENERGY_GAS_PRICE));

                            //Antwort
                            settingsResponse.setSuccess(true);
                            return gson.toJson(settingsResponse);
                        }
                        //nicht Berechtigt
                        settingsResponse.setSuccess(false);
                        settingsResponse.setErrorCode(101);
                        settingsResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(settingsResponse);
                    }
                    //Ungültige Session
                    settingsResponse.setSuccess(false);
                    settingsResponse.setErrorCode(100);
                    settingsResponse.setMessage("Ungültige Session");
                    return gson.toJson(settingsResponse);
                case "setsettings":

                    successResponse = new SuccessResponse();
                    if(sessionUser != null) {

                        if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                            //Befehl ausführen
                            if(params.containsKey("data")) {

                                SettingsResponse settingsResponse1 = gson.fromJson(params.get("data"), SettingsResponse.class);
                                if(settingsResponse1 != null) {

                                    Settings settings = ShcApplicationServer.getInstance().getSettings();
                                    settings.getIntegerSetting(Settings.SETTING_SUNRISE_OFFSET).setValue(settingsResponse1.getIntegerSettings().get(Settings.SETTING_SUNRISE_OFFSET).getValue());
                                    settings.getIntegerSetting(Settings.SETTING_SUNSET_OFFSET).setValue(settingsResponse1.getIntegerSettings().get(Settings.SETTING_SUNSET_OFFSET).getValue());
                                    settings.getDoubleSetting(Settings.SETTING_LATITUDE).setValue(settingsResponse1.getDoubleSettings().get(Settings.SETTING_LATITUDE).getValue());
                                    settings.getDoubleSetting(Settings.SETTING_LONGITUDE).setValue(settingsResponse1.getDoubleSettings().get(Settings.SETTING_LONGITUDE).getValue());

                                    settings.getBooleanSetting(Settings.SETTING_FRITZBOX_ACTIVE).setValue(settingsResponse1.getBooleanSettings().get(Settings.SETTING_FRITZBOX_ACTIVE).getValue());
                                    settings.getStringSetting(Settings.SETTING_FRITZBOX_ADDRESS).setValue(settingsResponse1.getStringSettings().get(Settings.SETTING_FRITZBOX_ADDRESS).getValue());
                                    settings.getStringSetting(Settings.SETTING_FRITZBOX_USER).setValue(settingsResponse1.getStringSettings().get(Settings.SETTING_FRITZBOX_USER).getValue());
                                    settings.getStringSetting(Settings.SETTING_FRITZBOX_PASSWORD).setValue(settingsResponse1.getStringSettings().get(Settings.SETTING_FRITZBOX_PASSWORD).getValue());

                                    settings.getDoubleSetting(Settings.SETTING_ENERGY_ELECTRIC_PRICE).setValue(settingsResponse1.getDoubleSettings().get(Settings.SETTING_ENERGY_ELECTRIC_PRICE).getValue());
                                    settings.getDoubleSetting(Settings.SETTING_ENERGY_WATER_PRICE).setValue(settingsResponse1.getDoubleSettings().get(Settings.SETTING_ENERGY_WATER_PRICE).getValue());
                                    settings.getDoubleSetting(Settings.SETTING_ENERGY_GAS_PRICE).setValue(settingsResponse1.getDoubleSettings().get(Settings.SETTING_ENERGY_GAS_PRICE).getValue());

                                    successResponse.setSuccess(true);
                                } else {

                                    //ungültige Daten
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("ungültige Daten");
                                }
                            } else {

                                //fehlender Parameter data
                                successResponse.setSuccess(false);
                                successResponse.setErrorCode(200);
                                successResponse.setMessage("Fehlender Parameter \"data\"");
                            }
                            return gson.toJson(successResponse);
                        }
                        //nicht Berechtigt
                        successResponse.setSuccess(false);
                        successResponse.setErrorCode(101);
                        successResponse.setMessage("Fehlende Berechtigung");
                        return gson.toJson(successResponse);
                    }
                    //Ungültige Session
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(100);
                    successResponse.setMessage("Ungültige Session");
                    return gson.toJson(successResponse);
            }
        }
        return "";
    }
}
