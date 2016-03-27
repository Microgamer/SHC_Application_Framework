package net.kleditzsch.shcApplicationServer.Icon;

import net.kleditzsch.shcCore.Icon.Icon;
import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Icon Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class IconEditor {

    private static Logger logger = LoggerUtil.getLogger(IconEditor.class);

    /**
     * liste aller bekannten Icons
     */
    protected List<Icon> iconList = new ArrayList<>();

    /**
     * liest die Systemicons
     */
    public void loadSystemIcons() {

        Path iconsDirectory = Paths.get(IconEditor.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        if(iconsDirectory.getFileName().toString().toLowerCase().endsWith(".jar")) {

            //Jar Datei
            readIconsFromJarFile(iconsDirectory);
        } else {

            //Ordner Entwicklungsmodus
            readIconsFromDirectory(Paths.get(IconEditor.class.getClassLoader().getResource("icons").getPath()));
        }
    }

    /**
     * Liest die Benutzericons
     */
    public void loadUserIcons() {

        Path userIconsDirectory = Paths.get("icons");
        if(!Files.exists(userIconsDirectory)) {

            try {
                Files.createDirectories(userIconsDirectory);
            } catch (IOException e) {

                logger.log(Level.SEVERE, "Erstellen des Ordners für die Benutzericons fehlgeschlagen", e);
            }
        }
        readIconsFromDirectory(userIconsDirectory);
    }

    /**
     * listet alle bekannten Icons auf
     *
     * @return Liste mit allen Icons
     */
    public List<Icon> getIconList() {

        return iconList;
    }

    /**
     * list die Icons aus dem übergebenen Ordner
     *
     * @param directory Ordner
     */
    protected void readIconsFromDirectory(Path directory) {

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path file: stream) {

                if(Files.isDirectory(file)) {

                    Icon icon = new Icon();
                    icon.setName(file.getFileName().toString());
                    icon.setPath(file.toAbsolutePath());
                    try (DirectoryStream<Path> stream1 = Files.newDirectoryStream(directory.resolve(file), "*.png")) {

                        for (Path iconFile : stream1) {

                            String fileName = iconFile.getFileName().toString().toLowerCase();
                            int size = 0;
                            size = Integer.parseInt(fileName.replace(".png", ""));
                            icon.getAvailableSize().add(size);
                        }
                        iconList.add(icon);
                    } catch (IOException | DirectoryIteratorException e) {

                        logger.log(Level.SEVERE, "Fehler beim einlesen der Icons", e);
                    }
                }
            }
        } catch (IOException | DirectoryIteratorException e) {

            logger.log(Level.SEVERE, "Fehler beim einlesen der Icons", e);
        }
    }

    /**
     * liest icons aus der übergebenen Jar Datei
     *
     * @param jarFilePath Jar Datei
     */
    protected void readIconsFromJarFile(Path jarFilePath) {

        try {

            //Jar Datei einlesen
            JarFile jarFile = new JarFile(jarFilePath.toFile());
            Enumeration<JarEntry> entries = jarFile.entries();
            List<JarEntry> iconEntries = new ArrayList<>();
            while (entries.hasMoreElements()) {

                JarEntry entry = entries.nextElement();
                if(entry.getName().startsWith("icons/")) {

                    iconEntries.add(entry);
                }
            }

            //Daten auswerten
            for (JarEntry entry : iconEntries) {

                String fileName = entry.getName().toLowerCase();
                if(!fileName.equals("icons/") && fileName.endsWith("/")) {

                    String iconName = fileName.replace("icons/", "").replace("/", "");
                    Icon icon = new Icon();
                    icon.setName(iconName);
                    icon.setPath(Paths.get(fileName));
                    icon.setJarFile(true);
                    for(JarEntry iconFile : iconEntries) {

                        String iconFileName = iconFile.getName().toLowerCase();
                        if(iconFileName.startsWith(fileName) && iconFileName.endsWith(".png")) {

                            int size = 0;
                            size = Integer.parseInt(iconFileName.replace(fileName, "").replace(".png", ""));
                            icon.getAvailableSize().add(size);
                        }
                    }
                    iconList.add(icon);
                }
            }

            //Jar Datei schliesen
            jarFile.close();
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Fehler beim einlesen der Icons", e);
        }
    }
}
