package net.kleditzsch.shcApplicationServer.Json.Deserializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Battery;

import java.lang.reflect.Type;

/**
 * Deserialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class BatteryDeserializer implements JsonDeserializer<Battery> {

    @Override
    public Battery deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Battery element = new Battery();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setBatteryLevel(jo.get("batteryLevel").getAsDouble());
        element.setChargeing(jo.get("isChargeing").getAsBoolean());

        return element;
    }
}
