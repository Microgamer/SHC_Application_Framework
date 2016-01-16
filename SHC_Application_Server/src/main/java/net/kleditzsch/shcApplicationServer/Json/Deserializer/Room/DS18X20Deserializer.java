package net.kleditzsch.shcApplicationServer.Json.Deserializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.DS18X20;

import java.lang.reflect.Type;

/**
 * Deserialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DS18X20Deserializer implements JsonDeserializer<DS18X20> {

    @Override
    public DS18X20 deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        DS18X20 element = new DS18X20();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setTemerature(jo.get("temperature").getAsDouble());
        element.setTemperatureOffset(jo.get("temperatureOffset").getAsDouble());

        return element;
    }
}
