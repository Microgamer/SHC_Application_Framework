package net.kleditzsch.shcCore.Automation.Conditions;

import java.time.MonthDay;

import static org.junit.Assert.*;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DateConditionTest {

    @org.junit.Test
    public void testIsSatisfies() throws Exception {

        //TODO Tests bei immer Gleichem Datum ausführen

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
}