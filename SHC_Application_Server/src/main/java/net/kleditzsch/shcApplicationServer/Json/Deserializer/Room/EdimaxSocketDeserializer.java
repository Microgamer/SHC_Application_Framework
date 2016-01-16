package net.kleditzsch.shcApplicationServer.Json.Deserializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.EdimaxSocket;

import java.lang.reflect.Type;

/**
 * Deserialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class EdimaxSocketDeserializer implements JsonDeserializer<EdimaxSocket> {

    @Override
    public EdimaxSocket deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        EdimaxSocket element = new EdimaxSocket();
        SerializerUtil.deserializeAbstractSwitchable(jo, element);

        //Spezifische Daten serialisieren
        element.setIpAddress(jo.get("ipAddress").getAsString());
        element.setUsername(jo.get("username").getAsString());
        element.setPassword(jo.get("password").getAsString());
        element.setSocketType(jo.get("socketType").getAsInt());

        return element;
    }
}
