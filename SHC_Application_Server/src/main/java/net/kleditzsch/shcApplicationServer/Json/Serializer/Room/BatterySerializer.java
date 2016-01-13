package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Battery;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class BatterySerializer implements JsonSerializer<Battery> {

    @Override
    public JsonElement serialize(Battery battery, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSensor(jo, battery);

        //Spezifische Daten serialisieren
        jo.add("batteryLevel", new JsonPrimitive(battery.getBatteryLevel()));
        jo.add("isChargeing", new JsonPrimitive(battery.isChargeing()));

        return jo;
    }
}
