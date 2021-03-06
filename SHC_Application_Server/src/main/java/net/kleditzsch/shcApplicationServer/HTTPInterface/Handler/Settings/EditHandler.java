package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Settings;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcApplicationServer.Settings.Settings;
import net.kleditzsch.shcCore.ClientData.SettingsResponse;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Einstellungen speichern
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class EditHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(EditHandler.class);

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson   Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        SuccessResponse successResponse;
        User sessionUser = checkSession(params);
        String json;

        logger.info("Einstellungen speichern");
        successResponse = new SuccessResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.USER_ADMINISTRATION)) {

                //Befehl ausführen
                if(params.containsKey("data")) {

                    SettingsResponse settingsResponse1 = gson.fromJson(params.get("data"), SettingsResponse.class);
                    if(settingsResponse1 != null) {

                        Settings settings = ShcApplicationServer.getInstance().getSettings();
                        synchronized (ShcApplicationServer.getInstance().getSettings()) {

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
                        }

                        successResponse.setSuccess(true);
                        logger.info("Einstellungen gespeichert");
                    } else {

                        //ungültige Daten
                        successResponse.setSuccess(false);
                        successResponse.setMessage("ungültige Daten");
                        logger.warning(successResponse.getMessage());
                    }
                } else {

                    //fehlender Parameter data
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(200);
                    successResponse.setMessage("Fehlender Parameter \"data\"");
                    logger.warning(successResponse.getMessage());
                }
                json = gson.toJson(successResponse);
                logger.fine(json);
                return json;
            }
            //nicht Berechtigt
            successResponse.setSuccess(false);
            successResponse.setErrorCode(101);
            successResponse.setMessage("Fehlende Berechtigung");
            logger.warning(successResponse.getMessage());
            json = gson.toJson(successResponse);
            logger.fine(json);
            return json;
        }
        //Ungültige Session
        successResponse.setSuccess(false);
        successResponse.setErrorCode(100);
        successResponse.setMessage("Ungültige Session");
        logger.warning(successResponse.getMessage());
        json = gson.toJson(successResponse);
        logger.fine(json);
        return json;
    }
}
