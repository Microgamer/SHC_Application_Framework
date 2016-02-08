package net.kleditzsch.shcApplicationServer.HTTPInterface;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;

/**
 * Server Thread
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ServerRunnable implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        try {

            HttpServer server = new HttpServer();
            server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
            System.out.println("HTTPs Server auf Port " + server.getListeningPort() + " erfolgreich gestartet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
