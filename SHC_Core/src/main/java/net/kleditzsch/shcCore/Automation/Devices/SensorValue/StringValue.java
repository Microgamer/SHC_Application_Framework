package net.kleditzsch.shcCore.Automation.Devices.SensorValue;

import net.kleditzsch.shcCore.Automation.Interface.Sensor.AbstractSensorValue;

import java.time.LocalDateTime;

/**
 * Zeichenkette
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class StringValue extends AbstractSensorValue {

    /**
     * Zeichenkette
     */
    protected String string = "";

    /**
     * Typ
     */
    protected int type = STRING;

    /**
     * setzt die Zeichenkette
     *
     * @return Zeichenkette
     */
    public String getString() {
        return string;
    }

    /**
     * gibt die Zeichenkette zurück
     *
     * @param string Zeichenkette
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * fügt die Zeichenkette hinzu
     *
     * @param string Zeichenkette
     */
    public void pushString(String string) {

        this.string = string;
        this.lastPushTime = LocalDateTime.now();
    }

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    @Override
    public String getDisplayValue() {
        return this.string;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return type;
    }
}
