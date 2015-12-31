package net.kleditzsch.shcCore.Core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * Basiselement aller SHC Datenobjekte
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class BasicElement {

    /**
     * Eindeutige Identifizierung
     */
    private String hash = "";

    /**
     * Name des Elements
     */
    private String name = "";

    /**
     * gibt die eindeutige Identifizierung des Elements zurück
     *
     * @return Hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * setzt die die eindeutige Identifizierung des Elements
     *
     * @param hash eindeutige Identifizierung
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * erzeugt einen neuen Hash zur eindeutigen Identifizierung
     *
     * @return neue eindeutige Identifizierung
     */
    public static String createHash() {

        long nanoTime = System.nanoTime();

        MessageDigest md = null;
        try {

            md = MessageDigest.getInstance("SHA1");

            md.reset();
            byte[] buffer = Long.toString(nanoTime).getBytes();
            md.update(buffer);
            byte[] digest = md.digest();

            String hexStr = "";
            for (int i = 0; i < digest.length; i++) {
                hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
            }
            return hexStr;
        } catch (NoSuchAlgorithmException e) {}

        return null;
    }

    /**
     * gibt den Namen des Elements zurück
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * setzt den Namen des Elements
     *
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }
}
