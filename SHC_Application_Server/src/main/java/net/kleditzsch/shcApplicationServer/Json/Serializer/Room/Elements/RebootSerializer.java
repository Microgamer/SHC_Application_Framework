package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Reboot;
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
public class RebootSerializer implements JsonSerializer<Reboot>, JsonDeserializer<Reboot> {

    @Override
    public Reboot deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Reboot element = new Reboot();
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
    public JsonElement serialize(Reboot reboot, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, reboot);

        //Spezifische Daten serialisieren
        if(reboot.getSwitchServer() != null) {

            jo.add("switchServer", new JsonPrimitive(reboot.getSwitchServer().getHash()));
        } else {

            jo.add("switchServer", new JsonPrimitive(""));
        }

        return jo;
    }
}
