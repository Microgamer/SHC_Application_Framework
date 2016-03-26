package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Settings;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.Settings.Settings;
import net.kleditzsch.shcCore.ClientData.SettingsResponse;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Einstellungen listen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ListHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(ListHandler.class);

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson   Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        SettingsResponse settingsResponse;
        User sessionUser = checkSession(params);
        String json;

        logger.info("Einstellungen listen");
        settingsResponse = new SettingsResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.SETTINGS_ADMINISTRATION)) {

                //Einstellungen zusammenstellen
                Settings settings = ShcApplicationServer.getInstance().getSettings();
                synchronized (ShcApplicationServer.getInstance().getSettings()) {

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
                }

                //Antwort
                settingsResponse.setSuccess(true);
                logger.info("Einstellungen gelistet");
                json = gson.toJson(settingsResponse);
                logger.fine(json);
                return json;
            }
            //nicht Berechtigt
            settingsResponse.setSuccess(false);
            settingsResponse.setErrorCode(101);
            settingsResponse.setMessage("Fehlende Berechtigung");
            logger.warning(settingsResponse.getMessage());
            json = gson.toJson(settingsResponse);
            logger.fine(json);
            return json;
        }
        //Ungültige Session
        settingsResponse.setSuccess(false);
        settingsResponse.setErrorCode(100);
        settingsResponse.setMessage("Ungültige Session");
        logger.warning(settingsResponse.getMessage());
        json = gson.toJson(settingsResponse);
        logger.fine(json);
        return json;
    }
}
