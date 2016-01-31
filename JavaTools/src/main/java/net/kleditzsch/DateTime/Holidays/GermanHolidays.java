package net.kleditzsch.DateTime.Holidays;

import net.kleditzsch.DateTime.EasterDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Kommentar
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class GermanHolidays {

    /**
     * Feiertage
     */
    //Neujahr
    public final int NEW_YEARS_DAY = 1;
    //Heilige Drei Koenige
    public final int EPIPHANY = 2;
    //Gruendonnerstag
    public final int MAUNDY_THURSDAY = 4;
    //Karfreitag
    public final int GOOD_FRIDAY = 8;
    //Ostersonntag
    public final int EASTER_DAY = 16;
    //Ostermontag
    public final int EASTER_MONDAY = 32;
    //Tag der Arbeit
    public final int DAY_OF_WORK = 64;
    //Christi Himmelfahrt
    public final int ASCENSION_DAY = 128;
    //Pfingstsonntag
    public final int WHIT_SUN = 256;
    //Pfingstmontag
    public final int WHIT_MONDAY = 512;
    //Fronleichnam
    public final int CORPUS_CHRISTI = 1024;
    //Tag der Deutschen Einheit
    public final int GERMAN_UNIFICATION_DAY = 2048;
    //Reformationstag
    public final int REFOMATION_DAY = 4096;
    //Allerheiligen
    public final int ALL_SAINTS_DAY = 8192;
    //Buss- und Bettag
    public final int DAY_OF_REPENTANCE = 16384;
    //Heiligabend
    public final int CHRISTMAS_DAY = 32768;
    //1. Weihnachtstag
    public final int XMAS_DAY = 65536;
    //2. Weihnachtstag
    public final int BOXING_DAY = 131072;
    //Silvester
    public final int NEW_YEARS_EVE = 262144;
    //Mariae Himmelfahrt
    public final int ASSUMPTION = 524288;

    /**
     * Jahr
     */
    protected int year = 2000;

    public GermanHolidays() {
    }

    public GermanHolidays(int year) {
        this.year = year;
    }

    /**
     * gibt das Jahr zurück
     *
     * @return Jahr
     */
    public int getYear() {
        return year;
    }

    /**
     * setzt das Jahr
     *
     * @param year Jaht
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * gibt das Datum von "Neujahr" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate newYearsDay() {

        return LocalDate.of(this.year, 1, 1);
    }

    /**
     * gibt das Datum von "Heilige Drei Koenige" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate epiphany() {

        return LocalDate.of(this.year, 1, 6);
    }

    /**
     * gibt das Datum von "Gründonnerstag" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate maundyThursday() {

        LocalDate easterSunday = EasterDate.of(this.year);
        return easterSunday.minusDays(3);
    }

    /**
     * gibt das Datum von "Karfreitag" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate goodFriday() {

        LocalDate easterSunday = EasterDate.of(this.year);
        return easterSunday.minusDays(2);
    }

    /**
     * gibt das Datum von "Ostersonntag" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate easterDay() {

        return EasterDate.of(this.year);
    }

    /**
     * gibt das Datum von "Ostersonntag" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate easterMonday() {

        LocalDate easterSunday = EasterDate.of(this.year);
        return easterSunday.plusDays(1);
    }

    /**
     * gibt das Datum von "Tag der Arbeit" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate dayOfWork() {

        return LocalDate.of(this.year, 5, 1);
    }

    /**
     * gibt das Datum von "Christi Himmelfahrt" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate ascensionDay() {

        LocalDate easterSunday = EasterDate.of(this.year);
        return easterSunday.plusDays(39);
    }

    /**
     * gibt das Datum von "Pfingstsonntag" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate whitsun() {

        LocalDate easterSunday = EasterDate.of(this.year);
        return easterSunday.plusDays(49);
    }

    /**
     * gibt das Datum von "Pfingstmontag" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate whitMonday() {

        LocalDate easterSunday = EasterDate.of(this.year);
        return easterSunday.plusDays(50);
    }

    /**
     * gibt das Datum von "Fronleichnam" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate corpusChristi() {

        LocalDate easterSunday = EasterDate.of(this.year);
        return easterSunday.plusDays(60);
    }

    /**
     * gibt das Datum von "Mariä Himmelfahrt" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate assumption() {

        return LocalDate.of(this.year, 8, 15);
    }

    /**
     * gibt das Datum von "Tag der Deutschen Einheit" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate germanUnificationDay() {

        return LocalDate.of(this.year, 10, 3);
    }

    /**
     * gibt das Datum von "Reformationstag" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate reformationDay() {

        return LocalDate.of(this.year, 10, 31);
    }

    /**
     * gibt das Datum von "Allerheiligen" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate allSaintsDay() {

        return LocalDate.of(this.year, 11, 1);
    }

    /**
     * gibt das Datum von "Buss- und Bettag" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate dayOfRepentance() {

        LocalDate date = LocalDate.of(this.year, 11, 23).with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY));
        return date;
    }

    /**
     * gibt das Datum von "Heiligabend" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate christmasDay() {

        return LocalDate.of(this.year, 12, 24);
    }

    /**
     * gibt das Datum von "1. Weihnachtstag" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate xmasDay() {

        return LocalDate.of(this.year, 12, 25);
    }

    /**
     * gibt das Datum von "2. Weihnachtstag" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate boxingDay() {

        return LocalDate.of(this.year, 12, 26);
    }

    /**
     * gibt das Datum von "Silvester" zurueck
     *
     * @return Datumsobjekt
     */
    public LocalDate newYearsEve() {

        return LocalDate.of(this.year, 12, 31);
    }
}
