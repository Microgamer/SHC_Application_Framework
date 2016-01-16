package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.DS18X20;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DS18X20Serializer implements JsonSerializer<DS18X20> {

    @Override
    public JsonElement serialize(DS18X20 ds18X20, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, ds18X20);

        //Spezifische Daten serialisieren
        jo.add("temperature", new JsonPrimitive(ds18X20.getTemperature()));
        jo.add("temperatureOffset", new JsonPrimitive(ds18X20.getTemperatureOffset()));

        return jo;
    }
}
