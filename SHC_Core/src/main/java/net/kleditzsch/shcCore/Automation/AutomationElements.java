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

    //Automations Elemente

    //lesbare Elemente
    int INPUT = 200;
    int USER_AT_HOME = 201;

    //schaltbare Elemente
    int AVM_SOCKET = 300;
    int EDIMAX_SOCKET = 301;
    int FRITZ_BOX_WLAN = 302;
    int FRITZ_BOX_REBOOT_RECONNECT = 303;
    int OUTPUT = 304;
    int RADIO_SOCKET = 305;
    int REBOOT_SHUTDOWN = 306;
    int SCRIPT_SINGLE = 307;
    int SCRIPT_DOUBLE = 308;
    int WAKE_ON_LAN = 309;
    int VIRTUAL_SOCKET = 310;

    //Sensorwerte
    int ACTUAL_POWER = 400;
    int AIR_PRESSURE = 401;
    int ALTITUDE = 402;
    int BATTERIE_LEVEL = 403;
    int DISTANCE = 404;
    int DURATION = 405;
    int ENERGY = 406;
    int GAS_AMOUNT = 407;
    int HUMIDITY = 408;
    int LIGHT_INTENSITY = 409;
    int MOISTURE = 410;
    int STRING = 411;
    int TEMPERATURE = 412;
    int WATER_AMOUNT = 413;

    //Virtuelle Sensorwerte
    int VIRTUAL_ACTUAL_POWER = 500;
    int VIRTUAL_ENERGY = 501;
    int VIRTUAL_GAS_AMOUNT = 502;
    int VIRTUAL_LIGHT_INTENSITY = 503;
    int VIRTUAL_WATER_AMOUNT = 504;
    int VIRTUAL_TEMPERATURE = 505;

    //Wochentage
    int MONDAY = 1;
    int TUESDAY = 2;
    int WEDNESDAY = 3;
    int THURSDAY = 4;
    int FRIDAY = 5;
    int SATURDAY = 6;
    int SUNDAY = 7;
}
