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
import net.kleditzsch.shcCore.Automation.Devices.SensorValue.*;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.*;
import net.kleditzsch.shcCore.Automation.Interface.AutomationDevice;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.VirtualSensorValue;
import net.kleditzsch.shcCore.ClientData.User.UserData;
import net.kleditzsch.shcCore.ClientData.User.UserGroupData;
import net.kleditzsch.shcCore.Room.Elements.Sensor;
import net.kleditzsch.shcCore.SwitchServer.Interface.SwitchServer;
import net.kleditzsch.shcCore.Util.LoggerUtil;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements.*;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.User.UserFormController;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.User.UserGroupFormController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dialogverwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class FormDialogManager {

    private static Logger logger = LoggerUtil.getLogger(FormDialogManager.class);

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

        String location = "FXML/Admin/Form/User/UserForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
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
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!userFormController.isCanceld()) {

                return Optional.of(userFormController.getUser());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/User/UserGroupForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
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
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!userGroupFormController.isCanceld()) {

                return Optional.of(userGroupFormController.getUserGroupData());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/UserAtHomeForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Benutzer zu Hause Formular");
            UserAtHomeFormController userAtHomeFormController = loader.getController();
            userAtHomeFormController.setElement(userAtHome);
            dialog.showAndWait();
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!userAtHomeFormController.isCanceld()) {

                return Optional.of(userAtHomeFormController.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/InputForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
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
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!inputFormController.isCanceld()) {

                return Optional.of(inputFormController.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/OutputForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
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
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!outputFormController.isCanceld()) {

                return Optional.of(outputFormController.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/AvmSocketForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("AVM Steckdose Formular");
            AvmSocketController avmSocketController = loader.getController();
            avmSocketController.setElement(avmSocket);
            dialog.showAndWait();
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!avmSocketController.isCanceld()) {

                return Optional.of(avmSocketController.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/EdimaxSocketForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Edimax Steckdose Formular");
            EdimaxSocketFormController edimaxSocketFormController = loader.getController();
            edimaxSocketFormController.setElement(edimaxSocket);
            dialog.showAndWait();
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!edimaxSocketFormController.isCanceld()) {

                return Optional.of(edimaxSocketFormController.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/RadioSocketForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
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
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/VirtualSocketForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Virtuelle Steckdose Formular");
            VirtualSocketFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/WakeOnLanForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Wake on Lan Formular");
            WakeOnLanFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/FritzBoxWlanForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Fritz!Box Wlan Formular");
            FritzBoxWlanFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/FritzBoxRebootReconnectForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Fritz!Box Reboot/Reconnect Formular");
            FritzBoxRebootReconnectFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/RebootShutdownForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
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
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/ScriptSingleForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
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
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/ScriptDoubleForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
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
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/SensorValueForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Sensorwert Formular");
            SensorValueFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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

        String location = "FXML/Admin/Form/AutomationDevice/SensorValueWithOffsetForm.fxml";
        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("Sensorwert Formular");
            SensorValueWithOffsetFormController controller = loader.getController();
            controller.setElement(element);
            dialog.showAndWait();
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
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
    public static Optional<VirtualSensorValue> showVirtualSensorValueDialog(VirtualSensorValue element, Map<String, AutomationDevice> automationDevices) {

        String location = "FXML/Admin/Form/AutomationDevice/VirtualSensorValueForm.fxml";
        List<SensorValue> sensorValues = new ArrayList<>();
        for(AutomationDevice automationDevice : automationDevices.values()) {

            if(automationDevice instanceof SensorValue) {

                sensorValues.add((SensorValue) automationDevice);
            }
        }

        FXMLLoader loader = new FXMLLoader(ShcDesktopClient.getInstance().getClassLoader().getResource(location));
        Parent pane;
        try {

            pane = loader.load();
            Stage dialog = FormDialogManager.createModalDialog();
            dialog.setScene(new Scene(pane));
            dialog.setTitle("virtueller Sensorwert Formular");
            VirtualSensorFormController controller = loader.getController();
            if(element instanceof VirtualActualPowerValue) {

                controller.setVirtualSensorType(VirtualSensorValue.VIRTUAL_ACTUAL_POWER);
            } else if(element instanceof VirtualEnergyValue) {

                controller.setVirtualSensorType(VirtualSensorValue.VIRTUAL_ENERGY);
            } else if(element instanceof VirtualLightIntensityValue) {

                controller.setVirtualSensorType(VirtualSensorValue.VIRTUAL_LIGHT_INTENSITY);
            } else if(element instanceof VirtualGasAmountValue) {

                controller.setVirtualSensorType(VirtualSensorValue.VIRTUAL_GAS_AMOUNT);
            } else if(element instanceof VirtualWaterAmountValue) {

                controller.setVirtualSensorType(VirtualSensorValue.VIRTUAL_WATER_AMOUNT);
            } else if(element instanceof VirtualTemperatureValue) {

                controller.setVirtualSensorType(VirtualSensorValue.VIRTUAL_TEMPERATURE);
            }
            controller.setSensorValues(sensorValues);
            controller.setElement(element);
            dialog.showAndWait();
            logger.info("FXML Datei \"" + location +"\" geladen");

            if(!controller.isCanceld()) {

                return Optional.of(controller.getElement());
            }
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Laden der FXML Datei \"" + location +"\" fehlgeschlagen", e);
            UiDialogHelper.showErrorDialog(ShcDesktopClient.getInstance().getPrimaryStage(), "Ladefehler", null, "Eine FXML Datei konnte nicht geladen werden");
        }
        return Optional.empty();
    }
}
