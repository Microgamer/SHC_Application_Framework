package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.VirtualTemperature;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualTemperatureSerializer implements JsonSerializer<VirtualTemperature>, JsonDeserializer<VirtualTemperature> {

    @Override
    public VirtualTemperature deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        VirtualTemperature element = new VirtualTemperature();
        SerializerUtil.deserializeAbstractVirtualSensor(jo, element);

        //Daten aktualisieren
        element.processSensorData();

        return element;
    }

    @Override
    public JsonElement serialize(VirtualTemperature virtualTemperature, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractVirtualSensor(jo, virtualTemperature);

        return jo;
    }
}
