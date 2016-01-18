package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.LDR;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LdrSerializer implements JsonSerializer<LDR>, JsonDeserializer<LDR> {

    @Override
    public LDR deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        LDR element = new LDR();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setLightIntenisty(jo.get("lightIntensity").getAsDouble());

        return element;
    }

    @Override
    public JsonElement serialize(LDR ldr, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, ldr);

        //Spezifische Daten serialisieren
        jo.add("lightIntensity", new JsonPrimitive(ldr.getLightIntensity()));

        return jo;
    }
}
