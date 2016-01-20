package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.RemoteReboot;
import net.kleditzsch.shcApplicationServer.SwitchServer.SwitchServerEditor;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RemoteRebootSerializer implements JsonSerializer<RemoteReboot>, JsonDeserializer<RemoteReboot> {

    @Override
    public RemoteReboot deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        RemoteReboot element = new RemoteReboot();
        SerializerUtil.deserializeAbstractSwitchable(jo, element);

        //Spezifische Daten serialisieren
        SwitchServerEditor switchServerEditor = ShcApplicationServer.getInstance().getSwitchServerEditor();
        SwitchServer switchServer = switchServerEditor.getSwitchServer(jo.get("switchServer").getAsString());
        if(switchServer != null) {

            element.setSwitchServer(switchServer);
        }

        return element;
    }

    @Override
    public JsonElement serialize(RemoteReboot remoteReboot, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, remoteReboot);

        //Spezifische Daten serialisieren
        jo.add("switchServer", new JsonPrimitive(remoteReboot.getSwitchServer().getHash()));

        return jo;
    }
}
