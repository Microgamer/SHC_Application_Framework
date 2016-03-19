package net.kleditzsch.shcCore.Util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.*;

/**
 * Logger
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class LoggerUtil {

    /**
     * Log Level
     */
    protected static Level logLevel = Level.INFO;

    /**
     * Log Datei
     */
    protected static Path logFile = null;
    protected static Level logFileLevel = Level.SEVERE;
    protected static FileHandler logFileHandler = null;

    /**
     * Handler für die Ausgabe
     */
    protected static Handler consoleHandler = new Handler(){
        @Override
        public void publish(LogRecord record)
        {
            if (getFormatter() == null)
            {
                setFormatter(new SimpleFormatter());
            }

            try {
                String message = getFormatter().format(record);
                if (record.getLevel().intValue() >= Level.WARNING.intValue())
                {
                    System.err.write(message.getBytes());
                }
                else
                {
                    System.out.write(message.getBytes());
                }
            } catch (Exception exception) {
                reportError(null, exception, ErrorManager.FORMAT_FAILURE);
            }

        }

        @Override
        public void close() throws SecurityException {}
        @Override
        public void flush(){}
    };



    /**
     * erzeugt einen Logger zu einem Klassenobjekt
     *
     * @param type Klasse
     * @return Logger
     */
    public static Logger getLogger(Class type) {

        return config(Logger.getLogger(type.getName()));
    }

    /**
     * erzeugt einen Logger zu einem Objekt
     *
     * @param type Objekt
     * @return Logger
     */
    public static Logger getLogger(Object type) {

        return config(Logger.getLogger(type.getClass().getName()));
    }

    /**
     * gibt das Log Level zurück
     *
     * @return Log Level
     */
    public static Level getLogLevel() {
        return logLevel;
    }

    /**
     * setzt das Log Level
     *
     * @param logLevel Log Level
     */
    public static void setLogLevel(Level logLevel) {
        LoggerUtil.logLevel = logLevel;
    }

    /**
     * gibt den Pfad zur Log Datei zurück (null wenn keine Datei erstellt werden soll)
     *
     * @return Pfad zur Log Datei
     */
    public static Path getLogFile() {
        return logFile;
    }

    /**
     * setzt den Pfad zur Log Datei
     *
     * @param logFile Pfad zur Log Datei
     */
    public static void setLogFile(Path logFile) {
        LoggerUtil.logFile = logFile;
    }

    /**
     * gibt das Log Level für die Log Datei zurück
     *
     * @return Log Level
     */
    public static Level getLogFileLevel() {
        return logFileLevel;
    }

    /**
     * setzt das Log Level für die Log Datei
     *
     * @param logFileLevel Log Level
     */
    public static void setLogFileLevel(Level logFileLevel) {
        LoggerUtil.logFileLevel = logFileLevel;
    }

    /**
     * konfiguriert einen Logger
     *
     * @param logger Logger
     * @return Logger
     */
    protected static Logger config(Logger logger) {

        if(logFile != null && logFileHandler == null) {

            try {
                logFileHandler = new FileHandler(logFile.toString(), 1000, 1, true);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        if(logFileHandler != null) {

            logFileHandler.setLevel(logFileLevel);
            logger.addHandler(logFileHandler);
        }
        if(logLevel != Level.OFF) {

            consoleHandler.setLevel(logLevel);
            logger.addHandler(consoleHandler);
        }
        logger.setLevel(logLevel);
        logger.setUseParentHandlers(false);
        return logger;
    }
}
