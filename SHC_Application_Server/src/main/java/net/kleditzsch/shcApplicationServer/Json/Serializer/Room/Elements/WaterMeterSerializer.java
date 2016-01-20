package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.WaterMeter;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class WaterMeterSerializer implements JsonSerializer<WaterMeter>, JsonDeserializer<WaterMeter> {

    @Override
    public WaterMeter deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        WaterMeter element = new WaterMeter();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setAmount(jo.get("amount").getAsDouble());

        return element;
    }

    @Override
    public JsonElement serialize(WaterMeter waterMeter, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, waterMeter);

        //Spezifische Daten serialisieren
        jo.add("amount", new JsonPrimitive(waterMeter.getAmount()));

        return jo;
    }
}
