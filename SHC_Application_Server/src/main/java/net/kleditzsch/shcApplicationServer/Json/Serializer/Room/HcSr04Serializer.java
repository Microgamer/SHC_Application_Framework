package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.HcSr04;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class HcSr04Serializer implements JsonSerializer<HcSr04>, JsonDeserializer<HcSr04> {

    @Override
    public HcSr04 deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        HcSr04 element = new HcSr04();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setDistance(jo.get("distance").getAsDouble());
        element.setDistanceOffset(jo.get("distanceOffset").getAsDouble());

        return element;
    }

    @Override
    public JsonElement serialize(HcSr04 hcSr04, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, hcSr04);

        //Spezifische Daten serialisieren
        jo.add("distance", new JsonPrimitive(hcSr04.getDistance()));
        jo.add("distanceOffset", new JsonPrimitive(hcSr04.getDistanceOffset()));

        return jo;
    }
}
