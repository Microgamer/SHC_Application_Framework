package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.Util.DatabaseDateTimeUtil;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.UserAtHome;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserAtHomeSerializer implements JsonSerializer<UserAtHome>, JsonDeserializer<UserAtHome> {

    @Override
    public UserAtHome deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        UserAtHome element = new UserAtHome();
        SerializerUtil.deserializeAbstractReadable(jo, element);

        //Spezifische Daten serialisieren
        element.setIpAddress(jo.get("ipAddress").getAsString());
        element.setTimeout(jo.get("timeout").getAsInt());
        element.setLastContact(DatabaseDateTimeUtil.parseDateTimeFromDatabase(jo.get("lastContact").getAsString()));
        element.setUseInternalAvailabilityCheck(jo.get("useInternalAvailabilityCheck").getAsBoolean());

        return element;
    }

    @Override
    public JsonElement serialize(UserAtHome userAtHome, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractReadable(jo, userAtHome);

        //Spezifische Daten serialisieren
        jo.add("ipAddress", new JsonPrimitive(userAtHome.getIpAddress()));
        jo.add("timeout", new JsonPrimitive(userAtHome.getTimeout()));
        jo.add("lastContact", new JsonPrimitive(DatabaseDateTimeUtil.getDatabaseDateTimeStr(userAtHome.getLastContact())));
        jo.add("useInternalAvailabilityCheck", new JsonPrimitive(userAtHome.isUseInternalAvailabilityCheck()));

        return jo;
    }
}
