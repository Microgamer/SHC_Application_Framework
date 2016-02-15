package net.kleditzsch.shcCore.ClientData.Login;

import net.kleditzsch.shcCore.ClientData.AbstractResponse;

/**
 * Handshake Data
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class Handshake extends AbstractResponse {

    protected String clientHash;
    protected String userAgent;
    protected boolean isKnown;

    public String getClientHash() {
        return clientHash;
    }

    public void setClientHash(String clientHash) {
        this.clientHash = clientHash;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public boolean isKnown() {
        return isKnown;
    }

    public void setKnown(boolean known) {
        isKnown = known;
    }
}
