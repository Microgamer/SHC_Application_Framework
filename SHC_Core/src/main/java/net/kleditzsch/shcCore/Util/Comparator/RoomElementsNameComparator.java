package net.kleditzsch.shcCore.Util.Comparator;

import net.kleditzsch.shcCore.Room.Elements.Interface.RoomElement;

import java.util.Comparator;
import java.util.Map;

/**
 * Sortiert Raum Elemente nach ihrem Namen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RoomElementsNameComparator implements Comparator<Map.Entry<Integer, RoomElement>> {

    @Override
    public int compare(Map.Entry<Integer, RoomElement> o1, Map.Entry<Integer, RoomElement> o2) {

        return o1.getValue().getName().compareTo(o2.getValue().getName());
    }
}