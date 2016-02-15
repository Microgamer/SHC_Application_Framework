package net.kleditzsch.shcCore.ClientData.Login;

import net.kleditzsch.shcCore.ClientData.AbstractResponse;

/**
 * Login Antwort
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LoginResponse extends AbstractResponse {

    protected String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
