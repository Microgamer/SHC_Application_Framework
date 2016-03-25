package net.kleditzsch.shcApplicationServer.Icon;

import net.kleditzsch.shcCore.Util.LoggerUtil;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
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

    public void loadSystemIcons() {

        Path icons = Paths.get(IconEditor.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        if(icons.getFileName().endsWith(".jar")) {

            //Jar Datei
            System.out.println("Jar Icons");
        } else {

            //Ordner
            System.out.println("Directory Icons");
            icons = Paths.get(IconEditor.class.getClassLoader().getResource("icons").getPath());
            System.out.println(icons.toAbsolutePath());
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(icons)) {
                for (Path file: stream) {

                    if(Files.isDirectory(file)) {

                        Icon icon = new Icon();
                        icon.setName(file.getFileName().toString());
                        icon.setPath(file.toAbsolutePath());
                        try (DirectoryStream<Path> stream1 = Files.newDirectoryStream(icons.resolve(file))) {
                            for (Path iconFile : stream1) {


                            }
                        } catch (IOException | DirectoryIteratorException e) {

                            logger.log(Level.SEVERE, "Fehler beim einlesen der Icons", e);
                        }
                    }
                }
            } catch (IOException | DirectoryIteratorException e) {

                logger.log(Level.SEVERE, "Fehler beim einlesen der Icons", e);
            }
        }
    }
}
