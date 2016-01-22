package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements.Groups;

import com.google.gson.*;
import net.kleditzsch.Util.DatabaseDateTimeUtil;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Groups.Countdown;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class CountdownSerializer implements JsonSerializer<Countdown>, JsonDeserializer<Countdown> {

    @Override
    public Countdown deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Countdown element = new Countdown();
        SerializerUtil.deserializeAbstractSwitchableGroup(jo, element);

        //Spezifische Daten serialisieren
        element.setIntervall(jo.get("intervall").getAsInt());
        element.setSwitchBackTime(DatabaseDateTimeUtil.parseDateTimeFromDatabase(jo.get("switchBackTime").getAsString()));
        element.setSwitchBackCommand(jo.get("switchBackCommand").getAsInt());

        return element;
    }

    @Override
    public JsonElement serialize(Countdown countdown, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchableGroup(jo, countdown);

        //Spezifische Daten serialisieren
        jo.add("intervall", new JsonPrimitive(countdown.getIntervall()));
        jo.add("switchBackTime", new JsonPrimitive(DatabaseDateTimeUtil.getDatabaseDateTimeStr(countdown.getSwitchBackTime())));
        jo.add("switchBackCommand", new JsonPrimitive(countdown.getSwitchBackCommand()));

        return jo;
    }
}
