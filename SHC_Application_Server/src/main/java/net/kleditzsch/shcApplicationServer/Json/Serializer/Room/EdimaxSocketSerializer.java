package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.EdimaxSocket;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class EdimaxSocketSerializer implements JsonSerializer<EdimaxSocket>, JsonDeserializer<EdimaxSocket> {

    @Override
    public EdimaxSocket deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        EdimaxSocket element = new EdimaxSocket();
        SerializerUtil.deserializeAbstractSwitchable(jo, element);

        //Spezifische Daten serialisieren
        element.setIpAddress(jo.get("ipAddress").getAsString());
        element.setUsername(jo.get("username").getAsString());
        element.setPassword(jo.get("password").getAsString());
        element.setSocketType(jo.get("socketType").getAsInt());

        return element;
    }

    @Override
    public JsonElement serialize(EdimaxSocket edimaxSocket, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, edimaxSocket);

        //Spezifische Daten serialisieren
        jo.add("ipAddress", new JsonPrimitive(edimaxSocket.getIpAddress()));
        jo.add("username", new JsonPrimitive(edimaxSocket.getUsername()));
        jo.add("password", new JsonPrimitive(edimaxSocket.getPassword()));
        jo.add("socketType", new JsonPrimitive(edimaxSocket.getSocketType()));

        return jo;
    }
}
