package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Bmp;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class BmpSerializer implements JsonSerializer<Bmp> {

    @Override
    public JsonElement serialize(Bmp bmp, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, bmp);

        //Spezifische Daten serialisieren
        jo.add("temperature", new JsonPrimitive(bmp.getTemperature()));
        jo.add("temperatureOffset", new JsonPrimitive(bmp.getTemperatureOffset()));
        jo.add("airPressure", new JsonPrimitive(bmp.getAirPressure()));
        jo.add("altitude", new JsonPrimitive(bmp.getAltitude()));

        return jo;
    }
}
