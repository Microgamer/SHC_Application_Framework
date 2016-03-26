package net.kleditzsch.shcCore.Json;

import com.google.gson.*;
import net.kleditzsch.shcCore.ClientData.Login.LoginResponse;
import net.kleditzsch.shcCore.Icon.Icon;

import java.lang.reflect.Type;

/**
 * Login Antwort Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LoginResponseSerializer implements JsonSerializer<LoginResponse>, JsonDeserializer<LoginResponse> {

    @Override
    public LoginResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(jo.get("success").getAsBoolean());
        loginResponse.setMessage(jo.get("message").getAsString());
        loginResponse.setErrorCode(jo.get("errorCode").getAsInt());
        loginResponse.setSessionId(jo.get("sessionId").getAsString());
        loginResponse.setProtocolVersion(jo.get("protocolVersion").getAsInt());
        loginResponse.setFritzBoxActive(jo.get("fritzBoxActive").getAsBoolean());

        //Berechtigungen
        JsonArray permissions = jo.getAsJsonArray("permissions");
        for(int i = 0; i < permissions.size(); i++) {

            loginResponse.getPermissions().add(permissions.get(i).getAsString());
        }

        //Icons
        JsonArray icons = jo.getAsJsonArray("icons");
        for(int i = 0; i < icons.size(); i++) {

            Icon icon = new Icon();
            JsonObject iconJson = icons.get(i).getAsJsonObject();
            icon.setName(iconJson.get("name").getAsString());

            JsonArray sizeArray = iconJson.getAsJsonArray("size");
            for(int j = 0;j < sizeArray.size(); j++) {

                icon.getAvailableSize().add(sizeArray.get(j).getAsInt());
            }
            loginResponse.getIcons().add(icon);
        }

        return loginResponse;
    }

    @Override
    public JsonElement serialize(LoginResponse loginResponse, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        jo.addProperty("success", loginResponse.isSuccess());
        jo.addProperty("message", loginResponse.getMessage());
        jo.addProperty("errorCode", loginResponse.getErrorCode());
        jo.addProperty("sessionId", loginResponse.getSessionId());
        jo.addProperty("protocolVersion", loginResponse.getProtocolVersion());
        jo.addProperty("fritzBoxActive", loginResponse.isFritzBoxActive());

        //Berechtigungen
        JsonArray permissions = new JsonArray();
        for(String permission : loginResponse.getPermissions()) {

            permissions.add(permission);
        }
        jo.add("permissions", permissions);

        //Icons
        JsonArray icons = new JsonArray();
        for(Icon icon : loginResponse.getIcons()) {

            JsonObject iconJson = new JsonObject();
            iconJson.addProperty("name", icon.getName());

            JsonArray sizeJson = new JsonArray();
            for(int size : icon.getAvailableSize()) {

                sizeJson.add(size);
            }
            iconJson.add("size", sizeJson);
            icons.add(iconJson);
        }
        jo.add("icons", icons);

        return jo;
    }
}
