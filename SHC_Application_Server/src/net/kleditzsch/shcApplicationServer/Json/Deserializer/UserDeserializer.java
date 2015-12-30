package net.kleditzsch.shcApplicationServer.Json.Deserializer;

import com.google.gson.*;
import net.kleditzsch.shcCore.User.User;

import java.lang.reflect.Type;

/**
 * Deserialisiert einen Benutzer aus einem JsonElement
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        User user = new User();
        user.setHash(jo.get("hash").getAsString());
        user.setName(jo.get("name").getAsString());
        user.setPasswordHash(jo.get("passwordHash").getAsString());
        user.setOriginator(jo.get("originator").getAsBoolean());

        JsonArray array = jo.get("userGroups").getAsJsonArray();
        for(int i = 0; i < array.size(); i++) {

            String userGroupHash = array.get(i).getAsString();
            System.out.println(userGroupHash);
            //TODO Benutzergruppe laden und dem Benutzer hinzufügen
        }

        return user;
    }
}
