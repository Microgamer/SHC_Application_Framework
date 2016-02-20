package net.kleditzsch.shcCore.Util.Comparator;

import java.util.Comparator;
import java.util.Map;

/**
 * Sortiert Raum Elemente nach ihrer Sortierungs ID
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RoomElementsOrderComparator implements Comparator<Map.Entry<Integer, String>> {

    @Override
    public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {

        if(o1.getKey() < o2.getKey()) {

            return 1;
        } else if(o1.getKey() > o2.getKey()) {

            return  -1;
        }
        return 0;
    }
}
