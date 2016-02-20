package net.kleditzsch.shcCore.Automation.Interface.Sensor;

import java.util.HashSet;
import java.util.Set;

/**
 * Virtueller Sensor
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractVirtualSensorValue extends AbstractSensorValue implements VirtualSensorValue {

    /**
     * überwachte Sensoren
     */
    protected final Set<String> sensorValues = new HashSet<>();

    /**
     * gibt die Liste der überwachten Sensoren zurück
     *
     * @return Liste der überwachten Sensoren
     */
    public Set<String> getSensorValues() {
        return sensorValues;
    }
}
