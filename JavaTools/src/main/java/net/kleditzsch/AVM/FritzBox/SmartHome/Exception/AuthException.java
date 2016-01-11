package net.kleditzsch.AVM.FritzBox.SmartHome.Exception;

/**
 * Fehler beim anmelden an der Fritz!Box
 */
public class AuthException extends RuntimeException {

    public AuthException(String message) {

        super(message);
    }
}
