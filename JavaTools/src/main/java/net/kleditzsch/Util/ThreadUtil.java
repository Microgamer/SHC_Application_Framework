package net.kleditzsch.Util;

/**
 * Hilfsfunktionen für Threads
 */
public abstract class ThreadUtil {

    /**
     * wartet bis alle Threads der übergebenen Gruppe abgearbeitet sind
     *
     * @param threadGroup
     * @throws InterruptedException
     */
    public static void threadGroupJoin(ThreadGroup threadGroup) throws InterruptedException {

        synchronized (threadGroup) {

            while (threadGroup.activeCount() > 0) {

                threadGroup.wait(10);
            }
        }
    }
}
