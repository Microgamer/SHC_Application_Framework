package net.kleditzsch.shcCore.Room.Elements;

import net.kleditzsch.shcCore.Room.Abstract.AbstractRoomElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Button Element
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ButtonElement extends AbstractRoomElement {

    /**
     * liste der zu schaltenden Elemente
     */
    protected Map<String, Integer> switchables = new HashMap<>();

    /**
     * Zeit bis zum automatischen Ausschalten in 100ms Schritten
     * (wenn 0 normaler Button)
     */
    protected long intervall = 0;

    /**
     * als einzelnen Button anzeigen
     */
    protected boolean showAsButton = false;

    /**
     * gibt die Liste mit den Schalbaren ELementen zurück
     *
     * @return
     */
    public Map<String, Integer> getSwitchables() {
        return switchables;
    }

    /**
     * gibt den Intervall zurück
     *
     * @return Intervall
     */
    public long getIntervall() {
        return intervall;
    }

    /**
     * setzt den Intervall
     *
     * @param intervall Intervall
     */
    public void setIntervall(long intervall) {
        this.intervall = intervall;
    }

    /**
     * gibt an ob der Button als einzelner Button angezeigt werden soll
     *
     * @return true wenn einzelner Button
     */
    public boolean isShowAsButton() {
        return showAsButton;
    }

    /**
     * setzt den Button als einzenen Button
     *
     * @param showAsButton true wenn Einzelner Button
     */
    public void setShowAsButton(boolean showAsButton) {
        this.showAsButton = showAsButton;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return Button;
    }
}
