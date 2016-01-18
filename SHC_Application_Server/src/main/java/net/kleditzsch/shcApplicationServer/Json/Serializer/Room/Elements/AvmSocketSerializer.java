package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.AvmSocket;

import java.lang.reflect.Type;

/**
 * Serialisiert eine AVM Steckdose
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class AvmSocketSerializer implements JsonSerializer<AvmSocket>, JsonDeserializer<AvmSocket> {

    @Override
    public AvmSocket deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        AvmSocket element = new AvmSocket();
        SerializerUtil.deserializeAbstractSwitchable(jo, element);

        //Spezifische Daten serialisieren
        element.setIdentifier(jo.get("identifier").getAsString());
        element.setTemerature(jo.get("temperature").getAsDouble());
        element.setTemperatureOffset(jo.get("temperatureOffset").getAsDouble());

        return element;
    }

    @Override
    public JsonElement serialize(AvmSocket avmSocket, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractSwitchable(jo, avmSocket);

        //Spezifische Daten serialisieren
        jo.add("identifier", new JsonPrimitive(avmSocket.getIdentifier()));
        jo.add("temperature", new JsonPrimitive(avmSocket.getTemperature()));
        jo.add("temperatureOffset", new JsonPrimitive(avmSocket.getTemperatureOffset()));

        return jo;
    }
}
