package net.kleditzsch.shcDesktopClient.Service;

import javafx.concurrent.Task;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.HttpInterface.ConnectionManager;

/**
 * Prüft die Verbindung zum App Server
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ConnectionTask extends Task<Boolean> {

    /**
     * Session abgelaufen
     */
    protected boolean expired = false;

    /**
     * Invoked when the Task is executed, the call method must be overridden and
     * implemented by subclasses. The call method actually performs the
     * background thread logic. Only the updateProgress, updateMessage, updateValue and
     * updateTitle methods of Task may be called from code within this method.
     * Any other interaction with the Task from the background thread will result
     * in runtime exceptions.
     *
     * @return The result of the background work, if any.
     * @throws Exception an unhandled exception which occurred during the
     *                   background operation
     */
    @Override
    protected Boolean call() throws Exception {

        ConnectionManager cm = ShcDesktopClient.getInstance().getConnectionManager();
        if(cm != null) {

            if(!cm.isConnected()) {

                expired = true;
                return true;
            }
        }
        expired = false;
        return false;
    }

    /**
     * A protected convenience method for subclasses, called whenever the
     * state of the Task has transitioned to the SUCCEEDED state.
     * This method is invoked on the FX Application Thread after the Task has been fully transitioned to
     * the new state.
     *
     * @since JavaFX 2.1
     */
    @Override
    protected void succeeded() {
        super.succeeded();

        if(expired) {

            //Verbindung abgebrochen
            ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
            ShcDesktopClient.getInstance().getMainViewController().setState(false);
        } else {

            ShcDesktopClient.getInstance().getMainViewController().setState(true);
        }
    }
}
