package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.RadioSocket;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RadioSocketSerializer implements JsonSerializer<RadioSocket>, JsonDeserializer<RadioSocket> {

    @Override
    public RadioSocket deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        RadioSocket element = new RadioSocket();
        SerializerUtil.deserializeAbstractSwitchable(jo, element);

        //Spezifische Daten serialisieren
        element.setProtocol(jo.get("protocol").getAsString());
        element.setSystemCode(jo.get("systemCode").getAsString());
        element.setDeviceCode(jo.get("deviceCode").getAsString());
        element.setContinues(jo.get("continues").getAsInt());
        element.setUseID(jo.get("useId").getAsBoolean());

        return element;
    }

    @Override
    public JsonElement serialize(RadioSocket radioSocket, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, radioSocket);

        //Spezifische Daten serialisieren
        jo.add("protocol", new JsonPrimitive(radioSocket.getProtocol()));
        jo.add("systemCode", new JsonPrimitive(radioSocket.getSystemCode()));
        jo.add("deviceCode", new JsonPrimitive(radioSocket.getDeviceCode()));
        jo.add("continues", new JsonPrimitive(radioSocket.getContinues()));
        jo.add("useId", new JsonPrimitive(radioSocket.isUseID()));

        return jo;
    }
}
