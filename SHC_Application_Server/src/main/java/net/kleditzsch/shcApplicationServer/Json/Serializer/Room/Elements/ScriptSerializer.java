package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Script;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ScriptSerializer implements JsonSerializer<Script>, JsonDeserializer<Script> {

    @Override
    public Script deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Script element = new Script();
        SerializerUtil.deserializeAbstractSwitchable(jo, element);

        //Spezifische Daten serialisieren
        element.setOnCommand(jo.get("onCommand").getAsString());
        element.setOffCommand(jo.get("offCommand").getAsString());

        return element;
    }

    @Override
    public JsonElement serialize(Script script, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, script);

        //Spezifische Daten serialisieren
        jo.add("onCommand", new JsonPrimitive(script.getOnCommand()));
        jo.add("offCommand", new JsonPrimitive(script.getOffCommand()));

        return jo;
    }
}
