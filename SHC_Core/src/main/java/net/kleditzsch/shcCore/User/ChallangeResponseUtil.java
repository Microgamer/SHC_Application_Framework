package net.kleditzsch.shcCore.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hilfsklasse zum berechnen der Challange Response
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class ChallangeResponseUtil {

    /**
     * berechnet die Challange Respononse
     *
     * @param challange Challange
     * @param userName Benutzername
     * @param userHash Benutzer Hash
     * @param clientHash ClientData Hash
     * @return ChallangeResponse
     */
    public static String computeChallangeResponse(String challange, String userName, String userHash, String clientHash) throws NoSuchAlgorithmException {

        String challangeResponse = challange + "-" + userName + "-";
        String stringToHash = "<" + challange + userHash + clientHash + ">";

        MessageDigest md = MessageDigest.getInstance("SHA1");

        md.reset();
        byte[] buffer = stringToHash.getBytes();
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        challangeResponse += hexStr;
        return challangeResponse;
    }
}
