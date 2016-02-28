package net.kleditzsch.shcCore.Json;

import com.google.gson.*;
import net.kleditzsch.shcCore.Automation.Devices.Readable.UserAtHome;
import net.kleditzsch.shcCore.Automation.Interface.AutomationDevice;
import net.kleditzsch.shcCore.ClientData.AutomationDevice.AutomationDeviceResponse;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;
import net.kleditzsch.shcCore.SwitchServer.MicroControllerSwitchServer;
import net.kleditzsch.shcCore.SwitchServer.RaspberryPiSwitchServer;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Serialisiert AutomationDevice Antworten
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AutomationDeviceResponseSerializer implements JsonSerializer<AutomationDeviceResponse>, JsonDeserializer<AutomationDeviceResponse> {

    @Override
    public AutomationDeviceResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();
        AutomationDeviceResponse automationDeviceResponse = new AutomationDeviceResponse();
        automationDeviceResponse.setSuccess(jo.get("success").getAsBoolean());
        automationDeviceResponse.setMessage(jo.get("message").getAsString());
        automationDeviceResponse.setErrorCode(jo.get("errorCode").getAsInt());

        JsonArray ja = jo.get("devices").getAsJsonArray();
        for(int i = 0; i < ja.size(); i++) {

            JsonObject deviceJson = ja.get(i).getAsJsonObject();
            AutomationDevice automationDevice = jsonDeserializationContext.deserialize(ja.get(i), AutomationDeviceResponse.getClassForType(deviceJson.get("type").getAsInt()));
            automationDeviceResponse.getAutomationDevices().put(automationDevice.getHash(), automationDevice);
        }

        JsonArray ja1 = jo.get("switchServer").getAsJsonArray();
        for(int i = 0; i < ja1.size(); i++) {

            JsonObject switchServerJson = ja1.get(i).getAsJsonObject();
            SwitchServer switchServer;
            if(switchServerJson.get("type").getAsInt() == SwitchServer.SWITCH_SERVER_RASPBERRY_PI) {

                switchServer = jsonDeserializationContext.deserialize(ja1.get(i), RaspberryPiSwitchServer.class);
            } else {

                switchServer = jsonDeserializationContext.deserialize(ja1.get(i), MicroControllerSwitchServer.class);
            }
            automationDeviceResponse.getSwitchServers().put(switchServer.getHash(), switchServer);
        }
        return automationDeviceResponse;
    }

    @Override
    public JsonElement serialize(AutomationDeviceResponse automationDeviceResponse, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        jo.addProperty("success", automationDeviceResponse.isSuccess());
        jo.addProperty("message", automationDeviceResponse.getMessage());
        jo.addProperty("errorCode", automationDeviceResponse.getErrorCode());

        JsonArray ja = new JsonArray();
        Map<String, AutomationDevice> automationDeviceMap = automationDeviceResponse.getAutomationDevices();
        for(String hash : automationDeviceMap.keySet()) {

            AutomationDevice automationDevice = automationDeviceMap.get(hash);
            ja.add(jsonSerializationContext.serialize(automationDevice));
        }

        jo.add("devices", ja);

        JsonArray ja1 = new JsonArray();
        Map<String, SwitchServer> switchServers = automationDeviceResponse.getSwitchServers();
        for(String hash : switchServers.keySet()) {

            SwitchServer switchServer = switchServers.get(hash);
            ja1.add(jsonSerializationContext.serialize(switchServer));
        }

        jo.add("switchServer", ja1);
        return jo;
    }
}
