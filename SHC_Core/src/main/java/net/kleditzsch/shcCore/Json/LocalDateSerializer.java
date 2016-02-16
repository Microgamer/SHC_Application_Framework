package net.kleditzsch.shcCore.Json;

import com.google.gson.*;
import net.kleditzsch.Util.DatabaseDateTimeUtil;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * Serialisiert Datumsobjekte
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LocalDateSerializer implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        String ldtStr = jsonElement.getAsString();
        return DatabaseDateTimeUtil.parseDateFromDatabase(ldtStr);
    }

    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {

        String ldtStr = DatabaseDateTimeUtil.getDatabaseDateStr(localDate);
        return new JsonPrimitive(ldtStr);
    }
}
