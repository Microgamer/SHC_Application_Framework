package net.kleditzsch.shcCore.Automation.Conditions;

import net.kleditzsch.shcCore.Automation.AbstractCondition;

/**
 * Datei Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class FileCondition extends AbstractCondition {

    /**
     * gibt an ob die Bedingung invertiert ist
     */
    protected boolean invert = false;

    /**
     * soll die Datei nach dem Prüfen gelöscht werden?
     */
    protected boolean deleteFileIfExist = false;

    /**
     * Datei welche überwacht werden soll
     */
    protected String file;

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
     * gibt an ob die Datei nach dem prüfen gelöscht werden soll
     *
     * @return true wenn aktiviert
     */
    public boolean isDeleteFileIfExist() {
        return deleteFileIfExist;
    }

    /**
     * aktiviert/deaktiviert das löschen der Datei nach dem prüfen
     *
     * @param deleteFileIfExist aktiviert/deaktiviert
     */
    public void setDeleteFileIfExist(boolean deleteFileIfExist) {
        this.deleteFileIfExist = deleteFileIfExist;
    }

    /**
     * gibt die Datei die von der Bedingung überwacht wird zurück
     *
     * @return Datei
     */
    public String getFile() {
        return file;
    }

    /**
     * setzt die Datei die von der Bedingung überwacht werden soll
     *
     * @param file Datei
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * gibt an ob die Bedingung erfuellt ist
     *
     * @return true wenn die Bedingung erfüllt ist
     */
    @Override
    public boolean isSatisfies() {
        return false;
    }

    /**
     * gibt den Typ des Elementes zurück
     *
     * @return Typ ID
     */
    @Override
    public int getType() {
        return FILE_CONDITION;
    }
}
