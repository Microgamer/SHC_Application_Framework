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
     * gibt an ob die Datei in einem Jar Archiv liegt
     */
    protected boolean jarFile = false;

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

    /**
     * gibt an ob die Icon Datei in einem Jar Archiv liegt
     *
     * @return Jar Archiv
     */
    public boolean isJarFile() {
        return jarFile;
    }

    /**
     * markiert die Date als Jar Archiv Datei
     *
     * @param jarFile Jar Archiv
     */
    public void setJarFile(boolean jarFile) {
        this.jarFile = jarFile;
    }

    /**
     * gibt die kleiste verfügbare Icon größe zurück
     *
     * @return Icon größe
     */
    public int getMinSize() {

        int minSize = 0;
        for(int size : availableSize) {

            if(minSize == 0 || size < minSize) {

                minSize = size;
            }
        }
        return minSize;
    }

    /**
     * gibt die größte verfügbare Icon größe zurück
     *
     * @return Icon größe
     */
    public int getMaxSize() {

        int maxSize = 0;
        for(int size : availableSize) {

            if(maxSize == 0 || size > maxSize) {

                maxSize = size;
            }
        }
        return maxSize;
    }

    @Override
    public String toString() {

        return "Icon [name=" + getName() + "; file=" + getPath().toAbsolutePath().toString() + "]";
    }
}
