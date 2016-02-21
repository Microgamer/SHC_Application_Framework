package net.kleditzsch.shcCore.Util.Comparator;

import net.kleditzsch.shcCore.Room.Interface.ViewElement;

import java.util.Comparator;
import java.util.Map;

/**
 * View Elemente Sortieren
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ViewElementsOrderComparator implements Comparator<Map.Entry<Integer, ViewElement>> {

    @Override
    public int compare(Map.Entry<Integer, ViewElement> o1, Map.Entry<Integer, ViewElement> o2) {

        if(o1.getKey() < o2.getKey()) {

            return 1;
        } else if(o1.getKey() > o2.getKey()) {

            return  -1;
        }
        return 0;
    }
}
