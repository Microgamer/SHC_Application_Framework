package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.RemoteShutdown;
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
public class RemoteShutdownSerializer implements JsonSerializer<RemoteShutdown>, JsonDeserializer<RemoteShutdown> {

    @Override
    public RemoteShutdown deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        RemoteShutdown element = new RemoteShutdown();
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
    public JsonElement serialize(RemoteShutdown remoteShutdown, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, remoteShutdown);

        //Spezifische Daten serialisieren
        jo.add("switchServer", new JsonPrimitive(remoteShutdown.getSwitchServer().getHash()));

        return jo;
    }
}
