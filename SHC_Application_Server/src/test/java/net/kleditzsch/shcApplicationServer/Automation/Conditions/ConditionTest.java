package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.MonthDay;

import static org.junit.Assert.*;

/**
 * Bedingungen Test
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ConditionTest {

    /**
     * Datums Bedingung
     *
     * @throws Exception
     */
    @org.junit.Test
    public void testDateCondition() throws Exception {

        //Daten vorbereiten
        DateCondition dateCondition = new DateCondition();
        dateCondition.setTestMode(true); //Vergleichsdatum immer der 19.07.

        //Test im Datumsbereich
        dateCondition.setStartDate(MonthDay.of(7, 10));
        dateCondition.setEndDate(MonthDay.of(7, 22));

        if(dateCondition.isSatisfies() == false) {

            fail("Datum nicht im Datumsbereich, sollte aber!");
        }

        //Test nicht im Datumsbereich
        dateCondition.setStartDate(MonthDay.of(5, 10));
        dateCondition.setEndDate(MonthDay.of(5, 22));

        if(dateCondition.isSatisfies() == true) {

            fail("Datum im Datumsbereich, sollte aber nicht!");
        }

        //Test im Datumsbereich rückwärts
        dateCondition.setStartDate(MonthDay.of(11, 10));
        dateCondition.setEndDate(MonthDay.of(8, 22));

        if(dateCondition.isSatisfies() == false) {

            fail("Datum nicht im Datumsbereich, sollte aber!");
        }

        //Test im Datumsbereich rückwärts
        dateCondition.setStartDate(MonthDay.of(11, 10));
        dateCondition.setEndDate(MonthDay.of(5, 22));

        if(dateCondition.isSatisfies() == true) {

            fail("Datum im Datumsbereich, sollte aber nicht!");
        }
    }

    /**
     * Wochentag Bedingung
     *
     * @throws Exception
     */
    @org.junit.Test
    public void testDayOfWeekCondition() throws Exception {

        DayOfWeekCondition dayOfWeekCondition = new DayOfWeekCondition();
        dayOfWeekCondition.setTestMode(true); //19.07.2016 -> Dienstag

        //Test im Bereich
        dayOfWeekCondition.setStartDay(DayOfWeekCondition.MONDAY);
        dayOfWeekCondition.setEndDay(DayOfWeekCondition.FRIDAY);

        if(dayOfWeekCondition.isSatisfies() == false) {

            fail("Tag nicht im Bereichereich, sollte aber!");
        }

        //Test nicht im Bereich
        dayOfWeekCondition.setStartDay(DayOfWeekCondition.WEDNESDAY);
        dayOfWeekCondition.setEndDay(DayOfWeekCondition.FRIDAY);

        if(dayOfWeekCondition.isSatisfies() == true) {

            fail("Tag im Bereichereich, sollte aber nicht!");
        }

        //Test im Bereich rückwärts
        dayOfWeekCondition.setStartDay(DayOfWeekCondition.SATURDAY);
        dayOfWeekCondition.setEndDay(DayOfWeekCondition.WEDNESDAY);

        if(dayOfWeekCondition.isSatisfies() == false) {

            fail("Tag nicht im Bereichereich, sollte aber!");
        }

        //Test nicht im Bereich rückwärts
        dayOfWeekCondition.setStartDay(DayOfWeekCondition.SATURDAY);
        dayOfWeekCondition.setEndDay(DayOfWeekCondition.MONDAY);

        if(dayOfWeekCondition.isSatisfies() == true) {

            fail("Tag im Bereichereich, sollte aber nicht!");
        }
    }

    /**
     * Datei Bedingung
     *
     * @throws Exception
     */
    @org.junit.Test
    public void testFileCondition() throws Exception {

        String testFile = "/tmp/test";
        Path testPath = Paths.get(testFile);

        FileCondition fileCondition = new FileCondition();
        fileCondition.setFile(testFile);

        //Test Datei existiert
        Files.createFile(testPath);

        if(fileCondition.isSatisfies() == false) {

            fail("Datei nicht erkannt, sollte aber!");
        }

        //Test Datei existiert und wird nach der Test gelöscht
        fileCondition.setDeleteFileIfExist(true);
        if(fileCondition.isSatisfies() == false) {

            fail("Datei nicht erkannt, sollte aber!");
        }
        if(Files.exists(testPath)) {

            fail("Datei wurde nach dem Test nicht gelöscht, sollte aber!");
        }
        fileCondition.setDeleteFileIfExist(false);

        //Test Datei existiert nicht
        Files.deleteIfExists(testPath);

        if(fileCondition.isSatisfies() == true) {

            fail("Datei erkannt, sollte aber nicht!");
        }

        //Test invertiert Datei existiert nicht
        fileCondition.setInvert(true);
        if(fileCondition.isSatisfies() == false) {

            fail("Datei erkannt, sollte aber nicht!");
        }

        //Test invertiert Datei existiert
        Files.createFile(testPath);

        if(fileCondition.isSatisfies() == true) {

            fail("Datei nicht erkannt, sollte aber!");
        }

        //cleanup
        Files.deleteIfExists(testPath);
    }

    /**
     * Datei Bedingung
     *
     * @throws Exception
     */
    @org.junit.Test
    public void testHolidayCondition() throws Exception {

        HolidaysCondition holidaysCondition = new HolidaysCondition();
        holidaysCondition.setTestMode(true);

        //Nicht invertiert
        if(holidaysCondition.isSatisfies() == true) {

            fail("Feiertag erkannt, sollte aber nicht!");
        }

        //Invertiert
        holidaysCondition.setInvert(true);
        if(holidaysCondition.isSatisfies() == false) {

            fail("Feiertag erkannt, sollte aber nicht!");
        }
    }
}