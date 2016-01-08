package net.kleditzsch.shcCore.Room.Elements.Interface;

/**
 * Liste aller Raum Elemente
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface ViewElements {

    /**
     * Spezielle Elemente
     */
    int ROOM = 1;
    int BOX = 2;

    /**
     * schaltbare Elemente
     */
    int AVM_SOCKET = 50;
    int EDIMAX_SOCKET = 51;
    int FRITZ_BOX = 52;
    int RADIO_SOCKET = 53;
    int REBOOT = 54;
    int REMOTE_REBOOT = 55;
    int SHUTDOWN = 56;
    int REMOTE_SHUTDOWN = 57;
    int OUTPUT = 58;
    int SCRIPT = 59;
    int REMOTE_SCRIPT = 60;
    int VIRTUAL_SOCKET = 61;
    int WAKE_ON_LAN = 62;
    int BUTTON = 63;

    /**
     * schaltbare Gruppenelemente
     */
    int ACTIVITY = 100;
    int COUNTDOWN = 101;

    /**
     * lesbare Elemente
     */
    int INPUT = 150;
    int USER_AT_HOME = 151;

    /**
     * Sensoren
     */
    int BMP = 200;
    //int COMET_THERMOSTAT = 201;
    int DHT = 202;
    int DS18X20 = 203;
    int GAS_METER = 204;
    int WATER_METER = 205;
    int ELECTRIC_METER = 206;
    int HC_SR04 = 207;
    int HYGROMETER = 208;
    int LDR = 209;
    int RAIN = 210;
    int SCT_013 = 211;

    /**
     * virtuelle Sensoren
     */
    int VIRTUAL_ENERGY = 250;
    int VIRTUAL_AMOUNT = 251;
    int VIRTUAL_HUMIDITY = 252;
    int VIRTUAL_LIGHT_INTENSITY = 253;
    int VIRTUAL_MOISTURE = 254;
    int VIRTUAL_ACTUEL_POWER = 255;
    int VIRTUAL_TEMPERATURE = 256;
}
