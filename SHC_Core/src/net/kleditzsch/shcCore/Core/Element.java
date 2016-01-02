package net.kleditzsch.shcCore.Core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Basiselement aller SHC Datenobjekte
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public interface Element {

    /**
     * gibt die eindeutige Identifizierung des Elements zurück
     *
     * @return Hash
     */
    String getHash();

    /**
     * setzt die die eindeutige Identifizierung des Elements
     *
     * @param hash eindeutige Identifizierung
     */
    void setHash(String hash);

    /**
     * erzeugt einen neuen Hash zur eindeutigen Identifizierung
     *
     * @return neue eindeutige Identifizierung
     */
    static String createHash() {

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
    String getName();

    /**
     * setzt den Namen des Elements
     *
     * @param name Name
     */
    void setName(String name);
}
