package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements.Groups;

import com.google.gson.*;
import net.kleditzsch.Util.DatabaseDateTimeUtil;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Groups.Button;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ButtonSerializer implements JsonSerializer<Button>, JsonDeserializer<Button> {

    @Override
    public Button deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Button element = new Button();
        SerializerUtil.deserializeAbstractSwitchableGroup(jo, element);

        //Spezifische Daten serialisieren
        element.setIntervall(jo.get("intervall").getAsInt());
        element.setSwitchBackTime(DatabaseDateTimeUtil.parseDateTimeFromDatabase(jo.get("switchBackTime").getAsString()));
        element.setSwitchBackCommand(jo.get("switchBackCommand").getAsInt());

        return element;
    }

    @Override
    public JsonElement serialize(Button button, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchableGroup(jo, button);

        //Spezifische Daten serialisieren
        jo.add("intervall", new JsonPrimitive(button.getIntervall()));
        jo.add("switchBackTime", new JsonPrimitive(DatabaseDateTimeUtil.getDatabaseDateTimeStr(button.getSwitchBackTime())));
        jo.add("switchBackCommand", new JsonPrimitive(button.getSwitchBackCommand()));

        return jo;
    }
}
