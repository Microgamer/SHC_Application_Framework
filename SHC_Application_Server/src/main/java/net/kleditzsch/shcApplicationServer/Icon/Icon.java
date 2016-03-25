package net.kleditzsch.shcApplicationServer.Icon;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Icon
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Icon {

    /**
     * verfügbare größen
     */
    protected Set<Integer> availableSize = new HashSet<>();

    /**
     * Name
     */
    protected String name = "";

    /**
     * Pfad zur Datei
     */
    protected Path path;

    /**
     * gibt die Liste mit den verfügbaren größen zurück
     *
     * @return verfügbare größen
     */
    public Set<Integer> getAvailableSize() {
        return availableSize;
    }

    /**
     * gibt den Namen zurück
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * setzt den Namen
     *
     * @param name Namen
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gibt den Pfad zur Datei zurück
     *
     * @return Pfad zur Datei
     */
    public Path getPath() {
        return path;
    }

    /**
     * setzt den Pfad zur Datei
     *
     * @param path Pfad zur Datei
     */
    public void setPath(Path path) {
        this.path = path;
    }
}
