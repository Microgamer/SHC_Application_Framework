package net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.VirtualActualPower;

import java.lang.reflect.Type;

/**
 * Serialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class VirtualActualPowerSerializer implements JsonSerializer<VirtualActualPower>, JsonDeserializer<VirtualActualPower> {

    @Override
    public VirtualActualPower deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        VirtualActualPower element = new VirtualActualPower();
        SerializerUtil.deserializeAbstractVirtualSensor(jo, element);

        //Daten aktualisieren
        element.processSensorData();

        return element;
    }

    @Override
    public JsonElement serialize(VirtualActualPower virtualActualPower, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jo = new JsonObject();
        SerializerUtil.serializeAbstractVirtualSensor(jo, virtualActualPower);

        return jo;
    }
}
