package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Rain;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RainSerializer implements JsonSerializer<Rain>, JsonDeserializer<Rain> {

    @Override
    public Rain deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Rain element = new Rain();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setMoisture(jo.get("moisture").getAsDouble());

        return element;
    }

    @Override
    public JsonElement serialize(Rain rain, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, rain);

        //Spezifische Daten serialisieren
        jo.add("moisture", new JsonPrimitive(rain.getMoisture()));

        return jo;
    }
}
