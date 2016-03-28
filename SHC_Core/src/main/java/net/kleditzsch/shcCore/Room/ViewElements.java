package net.kleditzsch.shcCore.Room;

import net.kleditzsch.shcCore.Core.Element;

/**
 * Anzeige Elemente
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface ViewElements extends Element {

    int ROOM = 1;

    int BUTTON = 100;
    int READABLE = 101;
    int SENSOR = 102;
    int VIRTUAL_SENSOR = 103;
    int SEPARATOR = 104;
}
