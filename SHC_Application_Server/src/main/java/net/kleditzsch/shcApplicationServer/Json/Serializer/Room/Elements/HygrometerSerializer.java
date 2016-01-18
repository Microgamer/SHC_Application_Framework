package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Hygrometer;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class HygrometerSerializer implements JsonSerializer<Hygrometer>, JsonDeserializer<Hygrometer> {

    @Override
    public Hygrometer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Hygrometer element = new Hygrometer();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setMoisture(jo.get("moisture").getAsDouble());

        return element;
    }

    @Override
    public JsonElement serialize(Hygrometer hygrometer, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, hygrometer);

        //Spezifische Daten serialisieren
        jo.add("moisture", new JsonPrimitive(hygrometer.getMoisture()));

        return jo;
    }
}
