package net.kleditzsch.shcCore.View;

/**
 * Liste aller Icons
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class Icon {

    /**
     * Icon Konstanten
     */
    public static String SOCKET = "icon-socket";
    public static String TV = "icon-socket";

    public static String[] getAvailableIcons() {

        return new String[] {
                SOCKET,
                TV
        };
    }
}
