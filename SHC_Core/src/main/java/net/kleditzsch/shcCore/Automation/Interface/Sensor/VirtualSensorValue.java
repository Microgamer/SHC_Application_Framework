package net.kleditzsch.shcCore.Automation.Interface.Sensor;

import java.util.Set;

/**
 * Virtueller Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface VirtualSensorValue extends SensorValue {

    /**
     * gibt die Liste der überwachten Sensoren zurück
     *
     * @return Liste der überwachten Sensoren
     */
    Set<String> getSensorValues();

    /**
     * ermittelt aus den Sensorwerten die Statistischen Werte
     *
     * @param sensorValues
     */
    void processValues(Set<SensorValue> sensorValues);

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    String getDisplayMinValue();

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    String getDisplayMaxValue();

    /**
     * gibt den Wert vorbereitet für die Anzeigen zurück
     *
     * @return Anzeigewert
     */
    String getDisplaySumValue();
}
