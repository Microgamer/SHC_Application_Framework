package net.kleditzsch.shcApplicationServer.Json.Serializer.User;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcCore.User.User;
import net.kleditzsch.shcCore.User.UserGroup;

import java.lang.reflect.Type;

/**
 * serialisiert einen Benutzer in ein JsonElement
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UserSerializer implements JsonSerializer<User>, JsonDeserializer<User> {

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
            UserGroup userGroup = userEditor.getUserGroupByHash(userGroupHash);
            user.getUserGroups().add(userGroup);
        }

        return user;
    }

    @Override
    public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject result = new JsonObject();
        result.add("hash", new JsonPrimitive(user.getHash()));
        result.add("name", new JsonPrimitive(user.getName()));
        result.add("passwordHash", new JsonPrimitive(user.getPasswordHash()));
        result.add("originator", new JsonPrimitive(user.isOriginator()));

        JsonArray array = new JsonArray();
        for(UserGroup userGroup : user.getUserGroups()) {

            array.add(new JsonPrimitive(userGroup.getHash()));
        }
        result.add("userGroups", array);

        return result;
    }
}
