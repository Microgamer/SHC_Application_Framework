package net.kleditzsch.shcApplicationServer.Json.Deserializer.Room;

import com.google.gson.*;
import net.kleditzsch.shcApplicationServer.Json.SerializerUtil;
import net.kleditzsch.shcApplicationServer.Room.Elements.GasMeter;

import java.lang.reflect.Type;

/**
 * Deserializer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class GasMeterDeserializer implements JsonDeserializer<GasMeter> {

    @Override
    public GasMeter deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jo = jsonElement.getAsJsonObject();

        GasMeter element = new GasMeter();
        SerializerUtil.deserializeAbstractSensor(jo, element);

        //Spezifische Daten serialisieren
        element.setAmount(jo.get("amount").getAsDouble());

        return element;
    }
}
