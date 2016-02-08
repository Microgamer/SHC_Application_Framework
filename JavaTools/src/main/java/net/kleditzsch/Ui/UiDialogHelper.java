package net.kleditzsch.Ui;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * Vereinfachung der
 */
public class UiDialogHelper {

    /**
     * zeigt einen Info-Dialog an
     *
     * @param title   Titel
     * @param header  Kopfzeile
     * @param message Meldung
     */
    public static void showInfoDialog(String title, String header, String message) {

        UiDialogHelper.showInfoDialog(null, title, header, message);
    }
    /**
     * zeigt einen Info-Dialog an
     *
     * @param owner   Referenzfenster
     * @param title   Titel
     * @param header  Kopfzeile
     * @param message Meldung
     */
    public static void showInfoDialog(Window owner, String title, String header, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(owner != null) {

            alert.initOwner(owner);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * zeigt einen Warnungs-Dialog an
     *
     * @param title   Titel
     * @param header  Kopfzeile
     * @param message Meldung
     */
    public static void showWarningDialog(String title, String header, String message) {

        UiDialogHelper.showWarningDialog(null, title, header, message);
    }

    /**
     * zeigt einen Warnungs-Dialog an
     *
     * @param owner   Referenzfenster
     * @param title   Titel
     * @param header  Kopfzeile
     * @param message Meldung
     */
    public static void showWarningDialog(Window owner, String title, String header, String message) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(owner != null) {

            alert.initOwner(owner);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * zeigt einen Fehler-Dialog an
     *
     * @param title   Titel
     * @param header  Kopfzeile
     * @param message Meldung
     */
    public static void showErrorDialog(String title, String header, String message) {

        UiDialogHelper.showErrorDialog(null, title, header, message);
    }

    /**
     * zeigt einen Fehler-Dialog an
     *
     * @param owner   Referenzfenster
     * @param title   Titel
     * @param header  Kopfzeile
     * @param message Meldung
     */
    public static void showErrorDialog(Window owner, String title, String header, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(owner != null) {

            alert.initOwner(owner);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * zeigt einen Eingabe-Dialog an
     *
     * @param title   Titel
     * @param header  Kopfzeile
     * @param message Meldung
     */
    public static boolean showConfirmDialog(String title, String header, String message) {

        return UiDialogHelper.showConfirmDialog(title, header, message);
    }

    /**
     * zeigt einen Eingabe-Dialog an
     *
     * @param owner   Referenzfenster
     * @param title   Titel
     * @param header  Kopfzeile
     * @param message Meldung
     */
    public static boolean showConfirmDialog(Window owner, String title, String header, String message) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if(owner != null) {

            alert.initOwner(owner);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            return true;
        }
        return false;
    }

    /**
     * zeigt einen Text Eingabedialog an
     *
     * @param title Titel
     * @param header Kopfzeile
     * @param contentText Bezeichner des Eingabefeldes
     * @return
     */
    public static String showTextInputDialog(String title, String header, String contentText) {

        return UiDialogHelper.showTextInputDialog(title, header, contentText, "");
    }

    /**
     * zeigt einen Text Eingabedialog an
     *
     * @param owner   Referenzfenster
     * @param title Titel
     * @param header Kopfzeile
     * @param contentText Bezeichner des Eingabefeldes
     * @return
     */
    public static String showTextInputDialog(Window owner, String title, String header, String contentText) {

        return UiDialogHelper.showTextInputDialog(owner, title, header, contentText, "");
    }

    /**
     * zeigt einen Text Eingabedialog an
     *
     * @param title Titel
     * @param header Kopfzeile
     * @param contentText Bezeichner des Eingabefeldes
     * @param defaultValue Startwert
     * @return
     */
    public static String showTextInputDialog(String title, String header, String contentText, String defaultValue) {

        return UiDialogHelper.showTextInputDialog(null, title, header, contentText, defaultValue);
    }

    /**
     * zeigt einen Text Eingabedialog an
     *
     * @param owner   Referenzfenster
     * @param title Titel
     * @param header Kopfzeile
     * @param contentText Bezeichner des Eingabefeldes
     * @param defaultValue Startwert
     * @return
     */
    public static String showTextInputDialog(Window owner, String title, String header, String contentText, String defaultValue) {

        TextInputDialog dialog = new TextInputDialog(defaultValue);
        if(owner != null) {

            dialog.initOwner(owner);
        }
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(contentText);

        //Dialog anzeigen und auf eingabe Warten
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){

            return result.get();
        }
        return defaultValue;
    }

    /**
     * zeigt einen Dialog zu einer Ausnahme an
     *
     * @param e
     */
    public static void showExceptionDialog(Throwable e) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(e.getLocalizedMessage());
        alert.setHeaderText(e.getLocalizedMessage());

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Trace:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setPrefSize(800, 400);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        //Fenstergroese anpassen
        alert.getDialogPane().expandedProperty().addListener((l) -> {
            Platform.runLater(() -> {
                alert.getDialogPane().requestLayout();
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.sizeToScene();
            });
        });

        alert.showAndWait();
    }

    /**
     * zeigt einen Dialog zu einer Ausnahme an
     *
     * @param owner   Referenzfenster
     * @param e
     */
    public static void showExceptionDialog(Window owner, Throwable e) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(owner != null) {

            alert.initOwner(owner);
        }
        alert.setTitle(e.getLocalizedMessage());
        alert.setHeaderText(e.getLocalizedMessage());

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Trace:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setPrefSize(800, 400);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        //Fenstergroese anpassen
        alert.getDialogPane().expandedProperty().addListener((l) -> {
            Platform.runLater(() -> {
                alert.getDialogPane().requestLayout();
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.sizeToScene();
            });
        });

        alert.showAndWait();
    }
}
