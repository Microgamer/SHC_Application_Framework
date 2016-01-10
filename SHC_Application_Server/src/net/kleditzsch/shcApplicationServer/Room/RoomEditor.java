package net.kleditzsch.shcApplicationServer.Room;

import net.kleditzsch.shcApplicationServer.Database.DatabaseEditor;
import net.kleditzsch.shcCore.Room.Elements.Interface.RoomElement;
import net.kleditzsch.shcCore.Room.Elements.Interface.Sensor;

/**
 * Raum Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RoomEditor implements DatabaseEditor {

    /**
     * lädt die Daten des Editors aus der Datenbank
     */
    @Override
    public void loadData() {

        //Normale Elemente
        //Aktivität/Countdown/Button
        //Virtuelle Sensoren
        //Box
        //Raum
    }

    public RoomElement getElement(String hash) {

        return null;
    }

    /**
     * schreibt die Daten des Editors in die Datenbank
     */
    @Override
    public void saveData() {

        //Nach Baustruktur
    }
}
