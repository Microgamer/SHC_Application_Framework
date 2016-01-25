package net.kleditzsch.shcApplicationServer.CommandExecutor;

import net.kleditzsch.shcCore.Command.Interface.SwitchCommand;

import java.util.Queue;
import java.util.concurrent.*;

/**
 * Klasse zum verarbeiten und Ausführen von Schaltbefehlen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class CommandExecutor {

    /**
     * Singleton Instanz
     */
    protected static CommandExecutor instance = new CommandExecutor();

    /**
     * Warteschlange für die Schaltbefehle
     */
    protected final Queue<SwitchCommand> commandQueue = new ConcurrentLinkedQueue<>();

    /**
     * Thread für den ExecutorService
     */
    protected Thread serviceThread;

    /**
     * gibt an ob der Service bereit ist
     */
    protected boolean ready = false;

    /**
     * Singleton Konstruktor
     */
    protected CommandExecutor() {}

    /**
     * fügt der Warteschlange einen neuen Schaltbefehl hinzu
     *
     * @param switchCommand Schaltbefehl
     */
    public void addSwtichCommand(SwitchCommand switchCommand) {

        commandQueue.offer(switchCommand);
        startService();
    }

    /**
     * startet den Executor Service
     */
    public void startService() {

        if(!ready) {

            ExecutorService executorService = new ExecutorService(commandQueue);
            serviceThread = new Thread(executorService);
            serviceThread.start();
            ready = true;
        }
    }

    /**
     * stoppt den Executor Service
     */
    public void stopService() {

        if(ready) {

            serviceThread.interrupt();
            ready = false;
        }
    }

    /**
     * gibt die Singleton Instanz zurück
     *
     * @return CommandExecutor Objekt
     */
    public static CommandExecutor getInstance() {

        return instance;
    }
}
