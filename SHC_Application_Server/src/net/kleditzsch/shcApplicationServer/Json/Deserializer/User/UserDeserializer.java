package net.kleditzsch.shcApplicationServer.Json.Deserializer.User;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;

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

        User user = new User(jo.get("originator").getAsBoolean());
        user.setHash(jo.get("hash").getAsString());
        user.setName(jo.get("name").getAsString());
        user.setPasswordHash(jo.get("passwordHash").getAsString());

        JsonArray array = jo.get("userGroups").getAsJsonArray();
        UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
        for(int i = 0; i < array.size(); i++) {

            String userGroupHash = array.get(i).getAsString();
            UserGroup userGroup = userEditor.getUserGroup(userGroupHash);
            user.getUserGroups().add(userGroup);
        }

        return user;
    }
}
