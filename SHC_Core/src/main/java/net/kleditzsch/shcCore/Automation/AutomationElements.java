package net.kleditzsch.shcCore.Automation;

/**
 * Konstanten
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface AutomationElements {

    //Bedingungen
    int CALENDAR_WEEK_CONDITION = 1;
    int DATE_CONDITION = 2;
    int DAY_OF_WEEK_CONDITION = 3;
    int FILE_CONDITION = 4;
    int HOLIDAYS_CONDITION = 5;
    int HUMIDITY_CONDITION = 6;
    int INPUT_CONDITION = 7;
    int LIGHT_INTENSITY_CONDITION = 8;
    int MOISTURE_CONDITION = 9;
    int NOBODY_AT_HOME_CONDITION = 10;
    int DAY_CONDITION = 11;
    int NIGHT_CONDITION = 12;
    int SWITCHABLE_STATE_CONDITION = 13;
    int TEMPERATURE_CONDITION = 14;
    int TIME_CONDITION = 15;
    int USER_AT_HOME_CONDITION = 16;
    int USER_NOT_AT_HOME_CONDITION = 17;

    //Wochentage
    int MONDAY = 1;
    int TUESDAY = 2;
    int WEDNESDAY = 3;
    int THURSDAY = 4;
    int FRIDAY = 5;
    int SATURDAY = 6;
    int SUNDAY = 7;
}
