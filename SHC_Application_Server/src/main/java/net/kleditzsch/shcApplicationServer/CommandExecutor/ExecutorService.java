package net.kleditzsch.shcApplicationServer.CommandExecutor;

import net.kleditzsch.shcCore.Command.Interface.SwitchCommand;
import net.kleditzsch.shcCore.Util.Constant;

import java.util.Queue;

/**
 * Thread zum ausführen der Schaltbefehle
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ExecutorService implements Runnable {

    /**
     * Warteschlange
     */
    protected Queue<SwitchCommand> commandQueue;

    /**
     * @param commandQueue Warteschlange
     */
    public ExecutorService(Queue<SwitchCommand> commandQueue) {
        this.commandQueue = commandQueue;
    }

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

        while (true) {

            while (commandQueue.size() > 0) {

                //Befeol aus der Warteschlange holen
                SwitchCommand switchCommand = commandQueue.poll();

                //TODO Ausführung implementieren
                System.out.println(switchCommand.getSwitchable().getName() + " -> " + (switchCommand.getCommand() == Constant.SWITCH_ON ? "an" : "aus") + " Queue size: " + commandQueue.size());

                //Beenden bei Interrupt
                if(Thread.currentThread().isInterrupted()) {

                    return;
                }
            }

            //20ms Wartezeit
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            //Beenden bei Interrupt
            if(Thread.currentThread().isInterrupted()) {

                return;
            }
        }
    }
}
