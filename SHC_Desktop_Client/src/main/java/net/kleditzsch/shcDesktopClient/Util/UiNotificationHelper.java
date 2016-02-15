package net.kleditzsch.shcDesktopClient.Util;

import javafx.geometry.Pos;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * Hilfsklasse für Benachrichtigungen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class UiNotificationHelper {

    /**
     * Anzeige Zeit
     */
    protected static final Duration NOTIFICATION_DELAY = Duration.seconds(5);

    /**
     * Zeigt eine Notification ohne Bild
     *
     * @param title Titel
     * @param message Meldung
     */
    public static void showPlainNotification(String title, String message) {

        UiNotificationHelper.showPlainNotification(null, title, message);
    }

    /**
     * Zeigt eine Notification ohne Bild
     *
     * @param owner Referenzfenster
     * @param title Titel
     * @param message Meldung
     */
    public static void showPlainNotification(Window owner, String title, String message) {

        Notifications notification = UiNotificationHelper.initNotification(owner, title, message);
        notification.show();
    }

    /**
     * Zeigt eine Info Notification
     *
     * @param title Titel
     * @param message Meldung
     */
    public static void showInfoNotification(String title, String message) {

        UiNotificationHelper.showInfoNotification(null, title, message);
    }

    /**
     * Zeigt eine Info Notification
     *
     * @param owner Referenzfenster
     * @param title Titel
     * @param message Meldung
     */
    public static void showInfoNotification(Window owner, String title, String message) {

        Notifications notification = UiNotificationHelper.initNotification(owner, title, message);
        notification.showInformation();
    }

    /**
     * Zeigt eine Warnungs Notification
     *
     * @param title Titel
     * @param message Meldung
     */
    public static void showWarningNotification(String title, String message) {

        UiNotificationHelper.showWarningNotification(null, title, message);
    }

    /**
     * Zeigt eine Warnungs Notification
     *
     * @param owner Referenzfenster
     * @param title Titel
     * @param message Meldung
     */
    public static void showWarningNotification(Window owner, String title, String message) {

        Notifications notification = UiNotificationHelper.initNotification(owner, title, message);
        notification.showWarning();
    }

    /**
     * Zeigt eine Fehler Notification
     *
     * @param title Titel
     * @param message Meldung
     */
    public static void showErrorNotification(String title, String message) {

        UiNotificationHelper.showErrorNotification(null, title, message);
    }

    /**
     * Zeigt eine Fehler Notification
     *
     * @param owner Referenzfenster
     * @param title Titel
     * @param message Meldung
     */
    public static void showErrorNotification(Window owner, String title, String message) {

        Notifications notification = UiNotificationHelper.initNotification(owner, title, message);
        notification.showError();
    }

    /**
     * Initalisiert eine Notification
     *
     * @param owner Referenzfenster
     * @param title Titel
     * @param message Meldung
     * @return Notification
     */
    protected static Notifications initNotification(Window owner, String title, String message) {

        Notifications notification = Notifications.create();
        notification.title(title);
        notification.text(message);
        notification.hideAfter(NOTIFICATION_DELAY);
        notification.position(Pos.BOTTOM_RIGHT);
        if(owner != null) {

            notification.owner(owner);
        }
        return notification;
    }
}
