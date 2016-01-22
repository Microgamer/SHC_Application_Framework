package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.VirtualAmount;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualAmountSerializer implements JsonSerializer<VirtualAmount>, JsonDeserializer<VirtualAmount> {

    @Override
    public VirtualAmount deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        VirtualAmount element = new VirtualAmount();
        SerializerUtil.deserializeAbstractVirtualSensor(jo, element);

        //Daten aktualisieren
        element.processSensorData();

        return element;
    }

    @Override
    public JsonElement serialize(VirtualAmount virtualAmount, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractVirtualSensor(jo, virtualAmount);

        return jo;
    }
}
