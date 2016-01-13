package net.kleditzsch.shcApplicationServer.Json.Deserializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.Bmp;

import java.lang.reflect.Type;

/**
 * Deserialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class BmpDeserializer implements JsonDeserializer<Bmp> {

    @Override
    public Bmp deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        Bmp element = new Bmp();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setTemerature(jo.get("temperature").getAsDouble());
        element.setTemperatureOffset(jo.get("temperatureOffset").getAsDouble());
        element.setAirPressure(jo.get("airPressure").getAsDouble());
        element.setAltitude(jo.get("altitude").getAsDouble());

        return element;
    }
}
