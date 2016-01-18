package net.kleditzsch.shcApplicationServer.Json.Serializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.FritzBox;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class FritzBoxSerializer implements JsonSerializer<FritzBox>, JsonDeserializer<FritzBox> {

    @Override
    public FritzBox deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        FritzBox element = new FritzBox();
        SerializerUtil.deserializeAbstractSwitchable(jo, element);

        //Spezifische Daten serialisieren
        element.setFunction(jo.get("function").getAsInt());

        return element;
    }

    @Override
    public JsonElement serialize(FritzBox fritzBox, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, fritzBox);

        //Spezifische Daten serialisieren
        jo.add("function", new JsonPrimitive(fritzBox.getFunction()));

        return jo;
    }
}
