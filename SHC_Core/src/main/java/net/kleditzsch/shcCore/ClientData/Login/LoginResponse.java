package net.kleditzsch.shcCore.ClientData.Login;

import net.kleditzsch.shcCore.ClientData.AbstractResponse;

import java.util.HashSet;
import java.util.Set;

/**
 * Login Antwort
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LoginResponse extends AbstractResponse {

    protected String sessionId;

    protected int protocolVersion = 1;

    protected Set<String> permissions = new HashSet<>();

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Set<String> getPermissions() {
        return permissions;
    }
}
