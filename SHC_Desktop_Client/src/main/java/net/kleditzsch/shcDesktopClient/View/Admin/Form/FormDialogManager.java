package net.kleditzsch.shcDesktopClient.View.Admin.Form;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.kleditzsch.Ui.UiDialogHelper;
import net.kleditzsch.shcCore.Automation.Devices.Readable.Input;
import net.kleditzsch.shcCore.Automation.Devices.Readable.UserAtHome;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.*;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;
import net.kleditzsch.shcCore.ClientData.User.UserData;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements.*;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.User.UserFormController;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.User.UserGroupFormController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Dialogverwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class FormDialogManager {

    /**
     * erzeugt ein Modales Dialogfenster
     *
     * @return Dialog Stage
     */
    protected static Stage createModalDialog() {

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(ShcDesktopClient.getInstance().getPrimaryStage());
        dialog.initStyle(StageStyle.UTILITY);
        return dialog;
    }

    /**
     * öffnet den Benutzer Formular Dialog
     *
     * @param userData Benutzer Daten
     * @param userGroupDataList Liste der Benutzergruppen
     * @return Benutzer Daten
     */
    public static Optional<UserData> showUserDataDialog(UserData userData, List<UserGroupData> userGroupDataList) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/User/UserForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Benutzer Formular");
            UserFormController userFormController = loader.getController();
            userFormController.setGroups(userGroupDataList);
            userFormController.setUser(userData);
            dialog.showAndWait();

            if(!userFormController.isCanceld()) {

                return Optional.of(userFormController.getUser());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Benutzergruppen Formular Dialog
     *
     * @param userGroupData Benutzergruppe Daten
     * @param permissions Berechtigungen
     * @return Benutzergruppe Daten
     */
    public static Optional<UserGroupData> showUserGroupDataDialog(UserGroupData userGroupData, List<String> permissions) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/User/UserGroupForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Benutzergruppe Formular");
            UserGroupFormController userGroupFormController = loader.getController();
            userGroupFormController.setPermissionList(permissions);
            userGroupFormController.setUserGroupData(userGroupData);
            dialog.showAndWait();

            if(!userGroupFormController.isCanceld()) {

                return Optional.of(userGroupFormController.getUserGroupData());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Benutzer zu Hause Formular Dialog
     *
     * @param userAtHome Benutzergruppe Daten
     * @return Benutzer zu Hause Daten
     */
    public static Optional<UserAtHome> showUserAtHomeDialog(UserAtHome userAtHome) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/UserAtHomeForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Benutzer zu Hause Formular");
            UserAtHomeFormController userAtHomeFormController = loader.getController();
            userAtHomeFormController.setElement(userAtHome);
            dialog.showAndWait();

            if(!userAtHomeFormController.isCanceld()) {

                return Optional.of(userAtHomeFormController.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Eingangs Formular Dialog
     *
     * @param input Eingang
     * @param switchServers Liste der Schaltserver
     * @return Eingang
     */
    public static Optional<Input> showInputDialog(Input input, Map<String, SwitchServer> switchServers) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/InputForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Eingangs Formular");
            InputFormController inputFormController = loader.getController();
            inputFormController.setSwitchServers(switchServers);
            inputFormController.setElement(input);
            dialog.showAndWait();

            if(!inputFormController.isCanceld()) {

                return Optional.of(inputFormController.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Ausgangs Formular Dialog
     *
     * @param output Ausgang
     * @param switchServers Liste der Schaltserver
     * @return Ausgang
     */
    public static Optional<Output> showOutputDialog(Output output, Map<String, SwitchServer> switchServers) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/OutputForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Eingangs Formular");
            OutputFormController outputFormController = loader.getController();
            outputFormController.setSwitchServers(switchServers);
            outputFormController.setElement(output);
            dialog.showAndWait();

            if(!outputFormController.isCanceld()) {

                return Optional.of(outputFormController.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den AVM Steckdosen Formular Dialog
     *
     * @param avmSocket AVM Steckdose
     * @return AVM Steckdose
     */
    public static Optional<AvmSocket> showAvmSocketDialog(AvmSocket avmSocket) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/AvmSocketForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("AVM Steckdose Formular");
            AvmSocketController avmSocketController = loader.getController();
            avmSocketController.setElement(avmSocket);
            dialog.showAndWait();

            if(!avmSocketController.isCanceld()) {

                return Optional.of(avmSocketController.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Edimax Steckdosen Formular Dialog
     *
     * @param edimaxSocket Edimax Steckdose
     * @return Edimax Steckdose
     */
    public static Optional<EdimaxSocket> showEdimaxSocketDialog(EdimaxSocket edimaxSocket) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/EdimaxSocketForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Edimax Steckdose Formular");
            EdimaxSocketFormController edimaxSocketFormController = loader.getController();
            edimaxSocketFormController.setElement(edimaxSocket);
            dialog.showAndWait();

            if(!edimaxSocketFormController.isCanceld()) {

                return Optional.of(edimaxSocketFormController.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Funk Steckdosen Formular Dialog
     *
     * @param element Funk Steckdose
     * @param switchServers Liste der Schaltserver
     * @return Funk Steckdose
     */
    public static Optional<RadioSocket> showRadioSocketDialog(RadioSocket element, Map<String, SwitchServer> switchServers) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/RadioSocketForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Funksteckdose Formular");
            RadioSocketFormController controller = loader.getController();
            controller.setSwitchServers(switchServers);
            controller.setElement(element);
            dialog.showAndWait();

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Virtuelle Steckdosen Formular Dialog
     *
     * @param element Funk Steckdose
     * @return Funk Steckdose
     */
    public static Optional<VirtualSocket> showVirtualSocketDialog(VirtualSocket element) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/VirtualSocketForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Virtuelle Steckdose Formular");
            VirtualSocketFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Wake on Lan Formular Dialog
     *
     * @param element Funk Steckdose
     * @return Funk Steckdose
     */
    public static Optional<WakeOnLan> showWakeOnLanDialog(WakeOnLan element) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/WakeOnLanForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Wake on Lan Formular");
            WakeOnLanFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den FritzBox Wlan Formular Dialog
     *
     * @param element FritzBox Wlan
     * @return FritzBox Wlan
     */
    public static Optional<FritzBoxWirelessLan> showFritzBoxWirelessLanDialog(FritzBoxWirelessLan element) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/FritzBoxWlanForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Fritz!Box Wlan Formular");
            FritzBoxWlanFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den FritzBox Reboot/Reconnect Formular Dialog
     *
     * @param element FritzBox Reboot/Reconnect
     * @return FritzBox Reboot/Reconnect
     */
    public static Optional<FritzBoxRebootReconnect> showFritzBoxRebootReconnectDialog(FritzBoxRebootReconnect element) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/FritzBoxRebootReconnectForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Fritz!Box Reboot/Reconnect Formular");
            FritzBoxRebootReconnectFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Neustart/Herunterfahren Formular Dialog
     *
     * @param element Neustart/Herunterfahren
     * @param switchServers Liste der Schaltserver
     * @return Neustart/Herunterfahren
     */
    public static Optional<RebootShutdown> showRebootShutdownDialog(RebootShutdown element, Map<String, SwitchServer> switchServers) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/RebootShutdownForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Neustart/Herunterfahren Formular");
            RebootShutdownFormController controller = loader.getController();
            controller.setSwitchServers(switchServers);
            controller.setElement(element);
            dialog.showAndWait();

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Script Formular Dialog
     *
     * @param element Script
     * @param switchServers Liste der Schaltserver
     * @return Script
     */
    public static Optional<ScriptSingle> showScriptSingleDialog(ScriptSingle element, Map<String, SwitchServer> switchServers) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/ScriptSingleForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("einfaches Script Formular");
            ScriptSingleFormController controller = loader.getController();
            controller.setSwitchServers(switchServers);
            controller.setElement(element);
            dialog.showAndWait();

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Script Formular Dialog
     *
     * @param element Script
     * @param switchServers Liste der Schaltserver
     * @return Script
     */
    public static Optional<ScriptDouble> showScriptDoubleDialog(ScriptDouble element, Map<String, SwitchServer> switchServers) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/ScriptDoubleForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("an/aus Script Formular");
            ScriptDoubleFormController controller = loader.getController();
            controller.setSwitchServers(switchServers);
            controller.setElement(element);
            dialog.showAndWait();

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Sensor Werte Formular Dialog
     *
     * @param element Sensorwert
     * @return Sensorwert
     */
    public static Optional<SensorValue> showSensorValueDialog(SensorValue element) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/SensorValueForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Sensorwert Formular");
            SensorValueFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }

    /**
     * öffnet den Sensor Werte Formular Dialog
     *
     * @param element Sensorwert
     * @return Sensorwert
     */
    public static Optional<SensorValue> showSensorValueWithOffsetDialog(SensorValue element) {

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource("FXML/Admin/Form/AutomationDevice/SensorValueWithOffsetForm.fxml"));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Sensorwert Formular");
            SensorValueWithOffsetFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            e.printStackTrace();
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }
}
