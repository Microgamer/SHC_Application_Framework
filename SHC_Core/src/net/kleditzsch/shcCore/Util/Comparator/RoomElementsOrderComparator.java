package net.kleditzsch.shcCore.Util.Comparator;

import net.kleditzsch.shcCore.Room.Elemnets.Interface.RoomElement;

import java.util.Comparator;
import java.util.Map;

/**
 * Sortiert Raum Elemente nach ihrer Sortierungs ID
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RoomElementsOrderComparator implements Comparator<Map.Entry<Integer, RoomElement>> {

    @Override
    public int compare(Map.Entry<Integer, RoomElement> o1, Map.Entry<Integer, RoomElement> o2) {

        if(o1.getKey() < o2.getKey()) {

            return 1;
        } else if(o1.getKey() > o2.getKey()) {

            return  -1;
        }
        return 0;
    }
}
