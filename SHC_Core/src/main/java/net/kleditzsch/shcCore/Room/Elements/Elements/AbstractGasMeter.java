package net.kleditzsch.shcCore.Room.Elements.Elements;

import net.kleditzsch.shcCore.Room.Elements.Abstract.AbstractSensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.SensorDataModel.Amount;

import java.time.LocalDateTime;

/**
 * Gaszähler
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class AbstractGasMeter extends AbstractSensor implements Amount {

    /**
     * Menge
     */
    protected double amount;

    /**
     * gibt die Menge zurück
     *
     * @return Menge
     */
    @Override
    public double getAmount() {
        return this.amount;
    }

    /**
     * setzt die Mange
     *
     * @param amount Menge
     */
    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * setzt die Sensordaten
     *
     * @param amount Menge
     */
    public void pushValues(double amount) {

        this.amount += amount;
        this.lastContactTime = LocalDateTime.now();
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return GAS_METER;
    }
}
