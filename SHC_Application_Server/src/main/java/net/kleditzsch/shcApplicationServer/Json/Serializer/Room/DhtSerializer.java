package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.DHT;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DhtSerializer implements JsonSerializer<DHT> {

    @Override
    public JsonElement serialize(DHT dht, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, dht);

        //Spezifische Daten serialisieren
        jo.add("temperature", new JsonPrimitive(dht.getTemperature()));
        jo.add("temperatureOffset", new JsonPrimitive(dht.getTemperatureOffset()));
        jo.add("humidity", new JsonPrimitive(dht.getHumidity()));

        return jo;
    }
}
