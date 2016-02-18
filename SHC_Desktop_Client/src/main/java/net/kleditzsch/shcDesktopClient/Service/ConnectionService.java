package net.kleditzsch.shcDesktopClient.Service;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;

/**
 * Verwaltet den Task zum prüfen der Verbindung zum App Server
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ConnectionService extends ScheduledService<Boolean> {

    public ConnectionService() {
        super();
        setPeriod(Duration.seconds(1));
    }

    /**
     * Invoked after the Service is started on the JavaFX Application Thread.
     * Implementations should save off any state into final variables prior to
     * creating the Task, since accessing properties defined on the Service
     * within the background thread code of the Task will result in exceptions.
     *
     * @return the Task to execute
     */
    @Override
    protected Task<Boolean> createTask() {

        return new ConnectionTask();
    }
}
