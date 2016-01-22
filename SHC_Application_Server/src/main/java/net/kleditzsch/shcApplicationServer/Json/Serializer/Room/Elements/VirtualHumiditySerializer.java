package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.VirtualHumidity;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualHumiditySerializer implements JsonSerializer<VirtualHumidity>, JsonDeserializer<VirtualHumidity> {

    @Override
    public VirtualHumidity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        VirtualHumidity element = new VirtualHumidity();
        SerializerUtil.deserializeAbstractVirtualSensor(jo, element);

        //Daten aktualisieren
        element.processSensorData();

        return element;
    }

    @Override
    public JsonElement serialize(VirtualHumidity virtualHumidity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractVirtualSensor(jo, virtualHumidity);

        return jo;
    }
}
