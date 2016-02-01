package net.kleditzsch.shcApplicationServer.Automation.Conditions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Datei Bedingung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class FileCondition extends net.kleditzsch.shcCore.Automation.Conditions.FileCondition {

    /**
     * gibt an ob die Bedingung erfuellt ist
     *
     * @return true wenn die Bedingung erfüllt ist
     */
    @Override
    public boolean isSatisfies() {

        //prüfen ob deaktiviert
        if(!isEnabled()) {

            return true;
        }

        if(file != null) {

            Path path = Paths.get(file);
            if(invert == false) {

                //prüfen ob Datei vorhanden
                if(Files.exists(path)) {

                    if(deleteFileIfExist) {

                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException e) {}
                    }

                    return true;
                }
            } else {

                //prüfen ob Datei nicht vorhanden
                if(!Files.exists(path)) {

                    return true;
                }
            }
        }
        return false;
    }
}
