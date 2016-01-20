package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.RemoteScript;
import net.kleditzsch.shcApplicationServer.SwitchServer.SwitchServerEditor;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RemoteScriptSerializer implements JsonSerializer<RemoteScript>, JsonDeserializer<RemoteScript> {

    @Override
    public RemoteScript deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        RemoteScript element = new RemoteScript();
        SerializerUtil.deserializeAbstractSwitchable(jo, element);

        //Spezifische Daten serialisieren
        element.setOnCommand(jo.get("onCommand").getAsString());
        element.setOffCommand(jo.get("offCommand").getAsString());
        SwitchServerEditor switchServerEditor = ShcApplicationServer.getInstance().getSwitchServerEditor();
        SwitchServer switchServer = switchServerEditor.getSwitchServer(jo.get("switchServer").getAsString());
        if(switchServer != null) {

            element.setSwitchServer(switchServer);
        }

        return element;
    }

    @Override
    public JsonElement serialize(RemoteScript remoteScript, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, remoteScript);

        //Spezifische Daten serialisieren
        jo.add("onCommand", new JsonPrimitive(remoteScript.getOnCommand()));
        jo.add("offCommand", new JsonPrimitive(remoteScript.getOffCommand()));
        jo.add("switchServer", new JsonPrimitive(remoteScript.getSwitchServer().getHash()));

        return jo;
    }
}
