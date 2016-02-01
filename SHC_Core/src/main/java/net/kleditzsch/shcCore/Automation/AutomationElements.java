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
    int DATE_CONDITION = 1;
    int DAY_OF_WEEK_CONDITION = 2;
    int FILE_CONDITION = 3;
    int HOLIDAYS_CONDITION = 4;
    int HUMIDITY_CONDITION = 5;
    int INPUT_CONDITION = 5;

    //Wochentage
    int MONDAY = 1;
    int TUESDAY = 2;
    int WEDNESDAY = 3;
    int THURSDAY = 4;
    int FRIDAY = 5;
    int SATURDAY = 6;
    int SUNDAY = 7;
}
