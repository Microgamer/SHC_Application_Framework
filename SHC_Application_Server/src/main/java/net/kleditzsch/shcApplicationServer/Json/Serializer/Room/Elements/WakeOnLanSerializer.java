package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.WakeOnLan;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class WakeOnLanSerializer implements JsonSerializer<WakeOnLan>, JsonDeserializer<WakeOnLan> {

    @Override
    public WakeOnLan deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        WakeOnLan element = new WakeOnLan();
        SerializerUtil.deserializeAbstractSwitchable(jo, element);

        //Spezifische Daten serialisieren
        element.setMac(jo.get("mac").getAsString());
        element.setIpAddress(jo.get("ipAddress").getAsString());

        return element;
    }

    @Override
    public JsonElement serialize(WakeOnLan wakeOnLan, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, wakeOnLan);

        //Spezifische Daten serialisieren
        jo.add("mac", new JsonPrimitive(wakeOnLan.getMac()));
        jo.add("ipAddress", new JsonPrimitive(wakeOnLan.getIpAddress()));

        return jo;
    }
}
