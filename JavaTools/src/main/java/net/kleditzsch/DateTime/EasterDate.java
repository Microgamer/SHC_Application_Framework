package net.kleditzsch.DateTime;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Objects;

/**
 * Errechnet das Osterdatum
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class EasterDate {

    /**
     * gibt das Oster Datum zurück
     *
     * @param year Jahr
     * @return Osterdatum
     */
    public static LocalDate of(int year) {

        return EasterDate.of(Year.of(year));
    }

    /**
     * gibt das Oster Datum zurück
     *
     * @param year Jahr
     * @return Osterdatum
     */
    public static LocalDate of(Year year) {

        Objects.requireNonNull(year, "year");
        int X = year.getValue();
        int K = X / 100;
        int M = 15 + (3 * K + 3) / 4 - (8 * K + 13) / 25;
        int S = 2 - (3 * K + 3) / 4;
        int A = X % 19;
        int D = (19 * A + M) % 30;
        int R = (D + A / 11) / 29;
        int OG = 21 + D - R;
        int SZ = 7 - (X + X / 4 + S) % 7;
        int OE = 7 - (OG - SZ) % 7;
        int OS = OG + OE;

        return LocalDate.of(X, Month.MARCH, 1).plusDays(OS - 1);
    }

}
