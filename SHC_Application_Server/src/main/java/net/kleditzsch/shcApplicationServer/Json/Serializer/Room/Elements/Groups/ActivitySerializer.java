package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements.Groups;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Groups.Activity;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ActivitySerializer implements JsonSerializer<Activity>, JsonDeserializer<Activity> {

    @Override
    public Activity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Activity element = new Activity();
        SerializerUtil.deserializeAbstractSwitchableGroup(jo, element);

        return element;
    }

    @Override
    public JsonElement serialize(Activity activity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchableGroup(jo, activity);

        return jo;
    }
}
