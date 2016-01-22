package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.VirtualMoisture;

import java.lang.reflect.Type;

/**
 * Serializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualMoistureSerializer implements JsonSerializer<VirtualMoisture>, JsonDeserializer<VirtualMoisture> {

    @Override
    public VirtualMoisture deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        VirtualMoisture element = new VirtualMoisture();
        SerializerUtil.deserializeAbstractVirtualSensor(jo, element);

        //Daten aktualisieren
        element.processSensorData();

        return element;
    }

    @Override
    public JsonElement serialize(VirtualMoisture virtualMoisture, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractVirtualSensor(jo, virtualMoisture);

        return jo;
    }
}
