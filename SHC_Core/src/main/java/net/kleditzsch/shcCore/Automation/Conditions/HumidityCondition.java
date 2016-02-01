package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.AbstractCondition;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.Humidity;

import java.util.HashSet;
import java.util.Set;

/**
 * Luftfeuchte Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class HumidityCondition extends AbstractCondition {

    /**
     * gibt an ob die Bedingung invertiert ist
     */
    protected boolean invert = false;

    /**
     * liste mit allen Sensoren die überwacht werden sollen
     */
    protected final Set<Humidity> sensorList = new HashSet<>();

    /**
     * Grenzwert
     */
    protected double limit = 0.0;

    /**
     * gibt an ob die Bedingung Invertiert ist
     *
     * @return Invertiert
     */
    public boolean isInvert() {
        return invert;
    }

    /**
     * setzt die Invertierung der Bedingung
     *
     * @param invert Invertiert
     */
    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    /**
     * gibt die Sensoren Liste zurück
     *
     * @return Sensoren Liste
     */
    public Set<Humidity> getSensorList() {
        return sensorList;
    }

    /**
     * gibt den Grenzwert zurück
     *
     * @return Grenzwert
     */
    public double getLimit() {
        return limit;
    }

    /**
     * setzt den Grenzwert
     *
     * @param limit Grenzwert
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }

    /**
     * gibt an ob die Bedingung erfuellt ist
     *
     * @return true wenn die Bedingung erfüllt ist
     */
    @Override
    public boolean isSatisfies() {

        //prüfen ob deaktiviert
        if(!isEnabled()) {

            return true;
        }

        if(sensorList.size() > 0) {

            for (Humidity sensor : sensorList) {

                if(!invert) {

                    //größer als
                    if(sensor.getHumidity() > limit) {

                        return true;
                    }
                } else {

                    //Kleiner als
                    if(sensor.getHumidity() < limit) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return HUMIDITY_CONDITION;
    }
}
