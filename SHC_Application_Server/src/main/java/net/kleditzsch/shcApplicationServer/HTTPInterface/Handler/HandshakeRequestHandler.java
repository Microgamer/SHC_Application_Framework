package net.kleditzsch.shcApplicationServer.HTTPInterface.Handler;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.DeviceManager.ClientDevice;
import net.kleditzsch.shcApplicationServer.DeviceManager.DeviceManager;
import net.kleditzsch.shcCore.ClientData.Login.Handshake;
import net.kleditzsch.shcApplicationServer.HTTPInterface.RequestHandler;

import java.util.Map;

/**
 * Handshake Anfrage
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class HandshakeRequestHandler implements RequestHandler {

    /**
     * behandelt eine Anfrage
     *
     * @param params GET Parameter
     * @param gson Gson Objekt
     * @return Json Antwort
     */
    @Override
    public String handleRequest(Map<String, String> params, Gson gson) {

        Handshake handshake = new Handshake();
        handshake.setClientHash(params.get("clientHash"));
        handshake.setUserAgent(params.get("userAgent"));

        if(handshake.getClientHash() != null && handshake.getClientHash().length() > 20 && handshake.getUserAgent() != null) {

            DeviceManager deviceManager = ShcApplicationServer.getInstance().getDeviceManager();
            if(!deviceManager.getDevices().containsKey(handshake.getClientHash())) {

                //Neues Gerät
                ClientDevice clientDevice = new ClientDevice();
                clientDevice.setClientHash(handshake.getClientHash());
                clientDevice.setUserAgend(handshake.getUserAgent());
                if(deviceManager.getDevices().size() == 0) {

                    clientDevice.setAllowed(true);
                }
                deviceManager.getDevices().put(clientDevice.getClientHash(), clientDevice);

                handshake.setSuccess(true);
                handshake.setKnown(false);
            } else {

                //bekanntes Gerät
                handshake.setSuccess(false);
                handshake.setKnown(true);
                handshake.setMessage("Das Gerät ist bereits bekannt");
            }
        } else {

            handshake.setSuccess(false);
            handshake.setErrorCode(200);
            handshake.setMessage("Parameter fehler!");
        }
        return gson.toJson(handshake);
    }
}
