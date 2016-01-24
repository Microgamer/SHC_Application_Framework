package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Box;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class BoxSerializer implements JsonSerializer<Box>, JsonDeserializer<Box> {

    @Override
    public Box deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Box element = new Box();

        //Spezifische Daten serialisieren
        element.setOrderId(jo.get("orderId").getAsInt());

        //Abstract Room Element
        SerializerUtil.deserializeAbstractRoomElement(jo, element);

        //Abstract View Element
        SerializerUtil.deserializeAbstractViewElement(jo, element);

        //Basic Element
        SerializerUtil.deserializeAbstractBasicElement(jo, element);

        return element;
    }

    @Override
    public JsonElement serialize(Box box, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();

        //Spezifische Daten serialisieren
        jo.add("orderId", new JsonPrimitive(box.getOrderId()));

        //Abstract Room Element
        SerializerUtil.serializeAbstractRoomElement(jo, box);

        //Abstract View Element
        SerializerUtil.serializeAbstractViewElement(jo, box);

        //Basic Element
        SerializerUtil.serializeAbstractBasicElement(jo, box);

        return jo;
    }
}
