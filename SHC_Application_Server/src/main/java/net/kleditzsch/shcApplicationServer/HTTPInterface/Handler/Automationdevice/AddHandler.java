package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler.Automationdevice;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Automation.AutomationDeviceEditor;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.HTTPInterface.AbstractRequestHandler;
import net.kleditzsch.shcCore.Automation.Devices.SensorValue.ActualPowerValue;
import net.kleditzsch.shcCore.Automation.Devices.SensorValue.EnergyValue;
import net.kleditzsch.shcCore.Automation.Devices.SensorValue.TemperatureValue;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.AvmSocket;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.EdimaxSocket;
import net.kleditzsch.shcCore.Automation.Interface.AutomationDevice;
import net.kleditzsch.shcCore.ClientData.AutomationDevice.AutomationDeviceResponse;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.Core.BasicElement;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Automatisierungsgeräte hinzufügen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AddHandler extends AbstractRequestHandler {

    private static Logger logger = LoggerUtil.getLogger(AddHandler.class);

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

        logger.info("Automatisierungsgerät hinzufügen");
        successResponse = new SuccessResponse();
        if(sessionUser != null) {

            if(checkUserPermission(sessionUser, Permissions.DEVICE_ADMINISTRATION)) {

                //Befehl ausführen
                if(params.containsKey("data") && params.containsKey("type")) {

                    Class typeClass = AutomationDeviceResponse.getClassForType(Integer.parseInt(params.get("type")));
                    if(typeClass != null) {

                        AutomationDevice automationDevice = (AutomationDevice) gson.fromJson(params.get("data"), typeClass);
                        if(automationDevice != null) {

                            AutomationDeviceEditor automationDeviceEditor = ShcApplicationServer.getInstance().getAutomationDeviceEditor();
                            synchronized (ShcApplicationServer.getInstance().getAutomationDeviceEditor()) {

                                if(automationDevice.getHash().length() > 0) {

                                    //prüfen ob Hash schon vorhanden
                                    if(!automationDeviceEditor.getAutomationDevices().containsKey(automationDevice.getHash())) {

                                        //Geräte an die Sensoren gebunden sind
                                        if(automationDevice instanceof AvmSocket) {

                                            //Sensorwerte erstellen
                                            TemperatureValue temp = new TemperatureValue();
                                            temp.setHash(BasicElement.createHash());
                                            temp.setIdentifier(BasicElement.createHash().substring(0, 10));
                                            temp.setName(automationDevice.getName() + " #temp");
                                            temp.setComment("AVM Steckdose Temperatur");
                                            temp.setSystemValue(true);

                                            ActualPowerValue power = new ActualPowerValue();
                                            power.setHash(BasicElement.createHash());
                                            power.setIdentifier(BasicElement.createHash().substring(0, 10));
                                            power.setName(automationDevice.getName() + " #power");
                                            power.setComment("AVM Steckdose Momentanverbrauch");
                                            power.setSystemValue(true);

                                            EnergyValue energy = new EnergyValue();
                                            energy.setHash(BasicElement.createHash());
                                            energy.setIdentifier(BasicElement.createHash().substring(0, 10));
                                            energy.setName(automationDevice.getName() + " #energy");
                                            energy.setComment("AVM Steckdose Energieverbrauch");
                                            energy.setSystemValue(true);

                                            //Sensoren an Steckdose binden
                                            ((AvmSocket) automationDevice).setTempSensorHash(temp.getHash());
                                            ((AvmSocket) automationDevice).setPowerSensorHash(power.getHash());
                                            ((AvmSocket) automationDevice).setEnergySensorHash(energy.getHash());

                                            //neues Gerät speichern
                                            automationDeviceEditor.getAutomationDevices().put(automationDevice.getHash(), automationDevice);
                                            automationDeviceEditor.getAutomationDevices().put(temp.getHash(), temp);
                                            automationDeviceEditor.getAutomationDevices().put(power.getHash(), power);
                                            automationDeviceEditor.getAutomationDevices().put(energy.getHash(), energy);
                                        } else if(automationDevice instanceof EdimaxSocket && ((EdimaxSocket) automationDevice).getSocketType() == EdimaxSocket.TYPE_SP_2101W) {

                                            //Sensorwerte erstellen
                                            ActualPowerValue power = new ActualPowerValue();
                                            power.setHash(BasicElement.createHash());
                                            power.setIdentifier(BasicElement.createHash().substring(0, 10));
                                            power.setName(automationDevice.getName() + " #power");
                                            power.setComment("Edimax Steckdose Momentanverbrauch");
                                            power.setSystemValue(true);

                                            EnergyValue energy = new EnergyValue();
                                            energy.setHash(BasicElement.createHash());
                                            energy.setIdentifier(BasicElement.createHash().substring(0, 10));
                                            energy.setName(automationDevice.getName() + " #energy");
                                            energy.setComment("Edimax Steckdose Energieverbrauch");
                                            energy.setSystemValue(true);

                                            //Sensoren an Steckdose binden
                                            ((EdimaxSocket) automationDevice).setPowerSensorHash(power.getHash());
                                            ((EdimaxSocket) automationDevice).setEnergySensorHash(energy.getHash());

                                            //neues Gerät speichern
                                            automationDeviceEditor.getAutomationDevices().put(automationDevice.getHash(), automationDevice);
                                            automationDeviceEditor.getAutomationDevices().put(power.getHash(), power);
                                            automationDeviceEditor.getAutomationDevices().put(energy.getHash(), energy);
                                        } else {

                                            //neues Gerät speichern
                                            automationDeviceEditor.getAutomationDevices().put(automationDevice.getHash(), automationDevice);
                                        }

                                        successResponse.setSuccess(true);
                                        logger.info("Automatisierungsgerät \"" + automationDevice.getName() + "\" erfolgreich erstellt");
                                    } else {

                                        //Hash schon vorhanden
                                        successResponse.setSuccess(false);
                                        successResponse.setMessage("Der Hash ist schon vorhanden");
                                        logger.warning(successResponse.getMessage());
                                    }
                                } else {

                                    //Fehler ungültiger Hash
                                    successResponse.setSuccess(false);
                                    successResponse.setMessage("Ungültiger Hash");
                                    logger.warning(successResponse.getMessage());
                                }
                            }
                        } else {

                            //Fehler ungültige Daten
                            successResponse.setSuccess(false);
                            successResponse.setMessage("Ungültige Daten");
                            logger.warning(successResponse.getMessage());
                        }
                    } else {

                        //Fehler ungältiger Typ
                        successResponse.setSuccess(false);
                        successResponse.setMessage("Ungültiger Typ");
                        logger.warning(successResponse.getMessage());
                    }
                } else {

                    //fehlender Parameter hash
                    successResponse.setSuccess(false);
                    successResponse.setErrorCode(200);
                    successResponse.setMessage("Fehlender Parameter \"data\" oder \"type\"");
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
