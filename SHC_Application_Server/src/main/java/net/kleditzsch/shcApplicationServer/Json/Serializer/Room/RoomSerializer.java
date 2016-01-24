package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Room;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RoomSerializer implements JsonSerializer<Room>, JsonDeserializer<Room> {

    @Override
    public Room deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Room element = new Room();

        //Spezifische Daten serialisieren
        element.setOrderId(jo.get("orderId").getAsInt());

        //Abstract View Element
        SerializerUtil.deserializeAbstractViewElement(jo, element);

        //Basic Element
        SerializerUtil.deserializeAbstractBasicElement(jo, element);

        return element;
    }

    @Override
    public JsonElement serialize(Room room, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();

        //Spezifische Daten serialisieren
        jo.add("orderId", new JsonPrimitive(room.getOrderId()));;

        //Abstract View Element
        SerializerUtil.serializeAbstractViewElement(jo, room);

        //Basic Element
        SerializerUtil.serializeAbstractBasicElement(jo, room);

        return jo;
    }
}
