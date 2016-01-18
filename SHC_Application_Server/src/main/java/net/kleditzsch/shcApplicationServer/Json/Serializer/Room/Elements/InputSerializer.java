package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Input;
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
public class InputSerializer implements JsonSerializer<Input>, JsonDeserializer<Input> {

    @Override
    public Input deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Input element = new Input();
        SerializerUtil.deserializeAbstractReadable(jo, element);

        //Spezifische Daten serialisieren
        SwitchServerEditor switchServerEditor = ShcApplicationServer.getInstance().getSwitchServerEditor();
        SwitchServer switchServer = switchServerEditor.getSwitchServer(jo.get("switchServer").getAsString());
        if(switchServer != null) {

            element.setSwitchServer(switchServer);
        }
        element.setPin(jo.get("pin").getAsInt());
        element.setInvert(jo.get("invert").getAsBoolean());

        return element;
    }

    @Override
    public JsonElement serialize(Input input, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractReadable(jo, input);

        //Spezifische Daten serialisieren
        jo.add("switchServer", new JsonPrimitive(input.getSwitchServer().getHash()));
        jo.add("pin", new JsonPrimitive(input.getPin()));
        jo.add("invert", new JsonPrimitive(input.isInvert()));

        return jo;
    }
}
