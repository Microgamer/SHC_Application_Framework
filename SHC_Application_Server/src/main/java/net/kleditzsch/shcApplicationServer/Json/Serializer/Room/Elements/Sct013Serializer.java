package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Sct013;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Sct013Serializer implements JsonSerializer<Sct013>, JsonDeserializer<Sct013> {

    @Override
    public Sct013 deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Sct013 element = new Sct013();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setActualPower(jo.get("actualPower").getAsDouble());

        return element;
    }

    @Override
    public JsonElement serialize(Sct013 sct013, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, sct013);

        //Spezifische Daten serialisieren
        jo.add("actualPower", new JsonPrimitive(sct013.getActualPower()));

        return jo;
    }
}
