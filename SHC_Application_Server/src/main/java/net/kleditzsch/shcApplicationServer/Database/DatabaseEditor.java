package net.kleditzsch.shcApplicationServer.Database;

/**
 * Schittstelle für Datenbank Editoren
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface DatabaseEditor {

    /**
     * lädt die Daten des Editors aus der Datenbank
     */
    void loadData();

    /**
     * schreibt die Daten des Editors in die Datenbank
     */
    void saveData();
}
