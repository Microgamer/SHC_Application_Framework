package net.kleditzsch.shcApplicationServer.DateTime;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Settings.Settings;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;

/**
 * Sonnenuntergang
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Sunset {

    public static LocalTime now() {

        Settings settings = ShcApplicationServer.getInstance().getSettings();

        //Längen/Breitengrad
        double latitude = settings.getDoubleSetting(Settings.SETTING_LATITUDE).getValue();
        double longitude = settings.getDoubleSetting(Settings.SETTING_LONGITUDE).getValue();

        //LocalTime erstellen
        Location location = new Location(latitude, longitude);
        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, "Europe/Berlin");
        Calendar officialSunset = calculator.getOfficialSunsetCalendarForDate(Calendar.getInstance());
        LocalTime sunset = LocalDateTime.ofInstant(officialSunset.toInstant(), ZoneId.of("Europe/Berlin")).toLocalTime();

        //Offset
        int offset = settings.getIntegerSetting(Settings.SETTING_SUNSET_OFFSET).getValue();
        return sunset.plusMinutes(offset);
    }
}
