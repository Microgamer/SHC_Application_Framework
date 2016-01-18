package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.GasMeter;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class GasMeterSerializer implements JsonSerializer<GasMeter>, JsonDeserializer<GasMeter> {

    @Override
    public GasMeter deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        GasMeter element = new GasMeter();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setAmount(jo.get("amount").getAsDouble());

        return element;
    }

    @Override
    public JsonElement serialize(GasMeter gasMeter, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, gasMeter);

        //Spezifische Daten serialisieren
        jo.add("amount", new JsonPrimitive(gasMeter.getAmount()));

        return jo;
    }
}
