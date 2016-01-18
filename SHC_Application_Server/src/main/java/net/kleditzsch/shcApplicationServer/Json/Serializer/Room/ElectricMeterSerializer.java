package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.ElectricMeter;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ElectricMeterSerializer implements JsonSerializer<ElectricMeter>, JsonDeserializer<ElectricMeter> {

    @Override
    public ElectricMeter deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        ElectricMeter element = new ElectricMeter();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setAmount(jo.get("amount").getAsDouble());

        return element;
    }

    @Override
    public JsonElement serialize(ElectricMeter electricMeter, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, electricMeter);

        //Spezifische Daten serialisieren
        jo.add("amount", new JsonPrimitive(electricMeter.getAmount()));

        return jo;
    }
}
