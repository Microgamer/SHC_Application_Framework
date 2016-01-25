package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.VirtualSocket;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualSocketSerializer implements JsonSerializer<VirtualSocket>, JsonDeserializer<VirtualSocket> {

    @Override
    public VirtualSocket deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        VirtualSocket element = new VirtualSocket();
        SerializerUtil.deserializeAbstractSwitchable(jo, element);

        return element;
    }

    @Override
    public JsonElement serialize(VirtualSocket virtualSocket, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, virtualSocket);

        return jo;
    }
}