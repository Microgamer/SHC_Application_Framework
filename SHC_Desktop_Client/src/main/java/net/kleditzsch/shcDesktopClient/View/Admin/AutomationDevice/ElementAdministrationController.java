package net.kleditzsch.shcDesktopClient.View.Admin.AutomationDevice;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import net.kleditzsch.shcCore.Automation.Devices.Readable.Input;
import net.kleditzsch.shcCore.Automation.Devices.Readable.UserAtHome;
import net.kleditzsch.shcCore.Automation.Devices.SensorValue.*;
import net.kleditzsch.shcCore.Automation.Devices.Switchable.*;
import net.kleditzsch.shcCore.Automation.Interface.AutomationDevice;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.SensorValue;
import net.kleditzsch.shcCore.Automation.Interface.Sensor.VirtualSensorValue;
import net.kleditzsch.shcCore.ClientData.AutomationDevice.AutomationDeviceResponse;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.Settings.BooleanSetting;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.Util.UiNotificationHelper;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.FormDialogManager;
import net.kleditzsch.shcDesktopClient.View.Admin.Form.Forms.AutomationElements.FritzBoxRebootReconnectFormController;
import net.kleditzsch.shcDesktopClient.View.MainViewLoader;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.MaskerPane;

/**
 * Elemente Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ElementAdministrationController {

    private static class ActiveCell extends TableCell<AutomationDevice, Boolean> {

        /**
         * @param item  The new item for the cell.
         * @param empty whether or not this cell represents data from the list. If it
         *              is empty, then it does not represent any domain data, but is a cell
         *              being used to render an "empty" row.
         * @expert
         */
        @Override
        protected void updateItem(Boolean item, boolean empty) {

            if(item != null) {

                if (item.booleanValue()) {

                    setText("nein");
                } else {

                    setText(("ja"));
                }
            } else {

                setText("");
            }
        }
    }

    private static class TypeCell extends TableCell<AutomationDevice, Integer> {

        /**         *
         * @param item  The new item for the cell.
         * @param empty whether or not this cell represents data from the list. If it
         *              is empty, then it does not represent any domain data, but is a cell
         *              being used to render an "empty" row.
         * @expert
         */
        @Override
        protected void updateItem(Integer item, boolean empty) {

            if(item != null) {

                setText(AutomationDeviceResponse.getTypeName(item));
            } else {

                setText("");
            }
        }
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootStackPane"
    private StackPane rootStackPane; // Value injected by FXMLLoader

    @FXML // fx:id="rootBorderPane"
    private BorderPane rootBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="rootToolbar"
    private ToolBar rootToolbar; // Value injected by FXMLLoader

    @FXML // fx:id="buttonBack"
    private Button buttonBack; // Value injected by FXMLLoader

    @FXML // fx:id="buttonRefresh"
    private Button buttonRefresh; // Value injected by FXMLLoader

    @FXML // fx:id="toolbarSpace"
    private Pane toolbarSpace; // Value injected by FXMLLoader

    @FXML // fx:id="filterLabel"
    private Label filterLabel; // Value injected by FXMLLoader

    @FXML // fx:id="inputTypeFilter"
    private CheckComboBox<String> inputTypeFilter; // Value injected by FXMLLoader

    @FXML // fx:id="inputNameFilter"
    private TextField inputNameFilter; // Value injected by FXMLLoader

    @FXML // fx:id="elementsTable"
    private TableView<AutomationDevice> elementsTable; // Value injected by FXMLLoader

    @FXML // fx:id="columnName"
    private TableColumn<AutomationDevice, String> columnName; // Value injected by FXMLLoader

    @FXML // fx:id="columnActive"
    private TableColumn<AutomationDevice, Boolean> columnActive; // Value injected by FXMLLoader

    @FXML // fx:id="columnType"
    private TableColumn<AutomationDevice, Integer> columnType; // Value injected by FXMLLoader

    @FXML // fx:id="columnComment"
    private TableColumn<AutomationDevice, String> columnComment; // Value injected by FXMLLoader

    @FXML // fx:id="tableContextMenu"
    private ContextMenu tableContextMenu; // Value injected by FXMLLoader

    @FXML // fx:id="menueButtenCreate"
    private MenuItem menuButtonCreate; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonEdit"
    private MenuItem menuButtonEdit; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonDelete"
    private MenuItem menuButtonDelete; // Value injected by FXMLLoader

    /**
     * Ladeanzeige
     */
    private MaskerPane maskerPane = new MaskerPane();

    /**
     * Response
     */
    protected AutomationDeviceResponse automationDeviceResponse;

    @FXML
    void back(ActionEvent event) {

        MainViewLoader.loadAdminMenueView();
    }

    @FXML
    void createElement(ActionEvent event) {

        List<String> choices = new ArrayList<>();
        //lesbare Elemente
        choices.add("Eingang");
        choices.add("Benutzer zu Hause");

        //schaltbare Elemente
        choices.add("Ausgang");
        choices.add("AVM Steckdose");
        choices.add("Edimax Steckdose");
        choices.add("Funk Steckdose");
        choices.add("Virtuelle Steckdose");
        choices.add("Wake on Lan");
        choices.add("Fritz!Box WLan");
        choices.add("Fritz!Box Reboot/Reconnect");
        choices.add("Reboot/Shutdown");
        choices.add("einfaches Script");
        choices.add("erweitertes Script");

        //Sensorwerte
        choices.add("aktueller Energieverbrauch");
        choices.add("Energieverbrauch");
        choices.add("Luftdruck");
        choices.add("Standorthöhe");
        choices.add("Batterie");
        choices.add("Entfernung");
        choices.add("Betriebszeit");
        choices.add("Gas Menge");
        choices.add("Wasser Menge");
        choices.add("Luftfeuchte");
        choices.add("Lichtstärke");
        choices.add("Feuchtigkeit");
        choices.add("Zeichenkette");
        choices.add("Temeratur");

        //Virtuelle Sensorwerte
        choices.add("Virtueller aktueller Energieverbrauch");
        choices.add("Virtueller Energieverbrauch");
        choices.add("Virtuelle Gas Menge");
        choices.add("Virtuelle Wasser Menge");
        choices.add("Virtuelle Lichtstärke");
        choices.add("Virtuelle Temeratur");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Ausgang", choices);
        dialog.setTitle("Typauswahl");
        dialog.setContentText("wähle den Typ");
        dialog.initOwner(ShcDesktopClient.getInstance().getPrimaryStage());

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {

            switch(result.get()) {

                //lesbare Elemente
                case "Eingang":

                    Optional<Input> input = FormDialogManager.showInputDialog(new Input(), automationDeviceResponse.getSwitchServers());
                    if(input.isPresent()) {

                        sendCreateRequest(input.get());
                    }
                    break;
                case "Benutzer zu Hause":

                    Optional<UserAtHome> userAtHome = FormDialogManager.showUserAtHomeDialog(new UserAtHome());
                    if(userAtHome.isPresent()) {

                        sendCreateRequest(userAtHome.get());
                    }
                    break;

                    //schaltbare Elemente
                case "Ausgang":

                    Optional<Output> output = FormDialogManager.showOutputDialog(new Output(), automationDeviceResponse.getSwitchServers());
                    if(output.isPresent()) {

                        sendCreateRequest(output.get());
                    }
                    break;
                case "AVM Steckdose":

                    Optional<AvmSocket> avmSocket = FormDialogManager.showAvmSocketDialog(new AvmSocket());
                    if(avmSocket.isPresent()) {

                        sendCreateRequest(avmSocket.get());
                    }
                    break;
                case "Edimax Steckdose":

                    Optional<EdimaxSocket> edimaxSocket = FormDialogManager.showEdimaxSocketDialog(new EdimaxSocket());
                    if(edimaxSocket.isPresent()) {

                        sendCreateRequest(edimaxSocket.get());
                    }
                    break;
                case "Funk Steckdose":

                    Optional<RadioSocket> radioSocket = FormDialogManager.showRadioSocketDialog(new RadioSocket(), automationDeviceResponse.getSwitchServers());
                    if(radioSocket.isPresent()) {

                        sendCreateRequest(radioSocket.get());
                    }
                    break;
                case "Virtuelle Steckdose":

                    Optional<VirtualSocket> virtualSocket = FormDialogManager.showVirtualSocketDialog(new VirtualSocket());
                    if(virtualSocket.isPresent()) {

                        sendCreateRequest(virtualSocket.get());
                    }
                    break;
                case "Wake on Lan":

                    Optional<WakeOnLan> wakeOnLan = FormDialogManager.showWakeOnLanDialog(new WakeOnLan());
                    if(wakeOnLan.isPresent()) {

                        sendCreateRequest(wakeOnLan.get());
                    }
                    break;
                case "Fritz!Box WLan":

                    Optional<FritzBoxWirelessLan> fritzBoxWirelessLan = FormDialogManager.showFritzBoxWirelessLanDialog(new FritzBoxWirelessLan());
                    if(fritzBoxWirelessLan.isPresent()) {

                        sendCreateRequest(fritzBoxWirelessLan.get());
                    }
                    break;
                case "Fritz!Box Reboot/Reconnect":

                    Optional<FritzBoxRebootReconnect> fritzBoxRebootReconnect = FormDialogManager.showFritzBoxRebootReconnectDialog(new FritzBoxRebootReconnect());
                    if(fritzBoxRebootReconnect.isPresent()) {

                        sendCreateRequest(fritzBoxRebootReconnect.get());
                    }
                    break;
                case "Reboot/Shutdown":

                    Optional<RebootShutdown> rebootShutdown = FormDialogManager.showRebootShutdownDialog(new RebootShutdown(), automationDeviceResponse.getSwitchServers());
                    if(rebootShutdown.isPresent()) {

                        sendCreateRequest(rebootShutdown.get());
                    }
                    break;
                case "einfaches Script":

                    Optional<ScriptSingle> scriptSingle = FormDialogManager.showScriptSingleDialog(new ScriptSingle(), automationDeviceResponse.getSwitchServers());
                    if(scriptSingle.isPresent()) {

                        sendCreateRequest(scriptSingle.get());
                    }
                    break;
                case "erweitertes Script":

                    Optional<ScriptDouble> scriptDouble = FormDialogManager.showScriptDoubleDialog(new ScriptDouble(), automationDeviceResponse.getSwitchServers());
                    if(scriptDouble.isPresent()) {

                        sendCreateRequest(scriptDouble.get());
                    }
                    break;

                //Sensorwerte
                case "aktueller Energieverbrauch":

                    Optional<SensorValue> actualPowerValue = FormDialogManager.showSensorValueDialog(new ActualPowerValue());
                    if(actualPowerValue.isPresent()) {

                        sendCreateRequest(actualPowerValue.get());
                    }
                    break;
                case "Energieverbrauch":

                    Optional<SensorValue> energyValue = FormDialogManager.showSensorValueDialog(new EnergyValue());
                    if(energyValue.isPresent()) {

                        sendCreateRequest(energyValue.get());
                    }
                    break;
                case "Luftdruck":

                    Optional<SensorValue> airPressureValue = FormDialogManager.showSensorValueDialog(new AirPressureValue());
                    if(airPressureValue.isPresent()) {

                        sendCreateRequest(airPressureValue.get());
                    }
                    break;
                case "Standorthöhe":

                    Optional<SensorValue> altitudeValue = FormDialogManager.showSensorValueDialog(new AltitudeValue());
                    if(altitudeValue.isPresent()) {

                        sendCreateRequest(altitudeValue.get());
                    }
                    break;
                case "Batterie":

                    Optional<SensorValue> batteryLevelValue = FormDialogManager.showSensorValueDialog(new BatteryLevelValue());
                    if(batteryLevelValue.isPresent()) {

                        sendCreateRequest(batteryLevelValue.get());
                    }
                    break;
                case "Entfernung":

                    Optional<SensorValue> distanceValue = FormDialogManager.showSensorValueWithOffsetDialog(new DistanceValue());
                    if(distanceValue.isPresent()) {

                        sendCreateRequest(distanceValue.get());
                    }
                    break;
                case "Betriebszeit":

                    Optional<SensorValue> durationValue = FormDialogManager.showSensorValueDialog(new DurationValue());
                    if(durationValue.isPresent()) {

                        sendCreateRequest(durationValue.get());
                    }
                    break;
                case "Gas Menge":

                    Optional<SensorValue> gasAmountValue = FormDialogManager.showSensorValueDialog(new GasAmountValue());
                    if(gasAmountValue.isPresent()) {

                        sendCreateRequest(gasAmountValue.get());
                    }
                    break;
                case "Wasser Menge":

                    Optional<SensorValue> waterAmountValue = FormDialogManager.showSensorValueDialog(new WaterAmountValue());
                    if(waterAmountValue.isPresent()) {

                        sendCreateRequest(waterAmountValue.get());
                    }
                    break;
                case "Luftfeuchte":

                    Optional<SensorValue> humidityValue = FormDialogManager.showSensorValueDialog(new HumidityValue());
                    if(humidityValue.isPresent()) {

                        sendCreateRequest(humidityValue.get());
                    }
                    break;
                case "Lichtstärke":

                    Optional<SensorValue> lightIntensityValue = FormDialogManager.showSensorValueDialog(new LightIntensityValue());
                    if(lightIntensityValue.isPresent()) {

                        sendCreateRequest(lightIntensityValue.get());
                    }
                    break;
                case "Feuchtigkeit":

                    Optional<SensorValue> moistureValue = FormDialogManager.showSensorValueDialog(new MoistureValue());
                    if(moistureValue.isPresent()) {

                        sendCreateRequest(moistureValue.get());
                    }
                    break;
                case "Zeichenkette":

                    Optional<SensorValue> stringValue = FormDialogManager.showSensorValueDialog(new StringValue());
                    if(stringValue.isPresent()) {

                        sendCreateRequest(stringValue.get());
                    }
                    break;
                case "Temeratur":

                    Optional<SensorValue> temperatureValue = FormDialogManager.showSensorValueWithOffsetDialog(new TemperatureValue());
                    if(temperatureValue.isPresent()) {

                        sendCreateRequest(temperatureValue.get());
                    }
                    break;

                //Virtuelle Sensorwerte
                case "Virtueller aktueller Energieverbrauch":

                    Optional<VirtualSensorValue> virtualActualPower = FormDialogManager.showVirtualSensorValueDialog(new VirtualActualPowerValue(), automationDeviceResponse.getAutomationDevices());
                    if(virtualActualPower.isPresent()) {

                        sendCreateRequest(virtualActualPower.get());
                    }
                    break;
                case "Virtueller Energieverbrauch":

                    Optional<VirtualSensorValue> virtualEnergy = FormDialogManager.showVirtualSensorValueDialog(new VirtualEnergyValue(), automationDeviceResponse.getAutomationDevices());
                    if(virtualEnergy.isPresent()) {

                        sendCreateRequest(virtualEnergy.get());
                    }
                    break;
                case "Virtuelle Gas Menge":

                    Optional<VirtualSensorValue> virtualGasAmount = FormDialogManager.showVirtualSensorValueDialog(new VirtualGasAmountValue(), automationDeviceResponse.getAutomationDevices());
                    if(virtualGasAmount.isPresent()) {

                        sendCreateRequest(virtualGasAmount.get());
                    }
                    break;
                case "Virtuelle Wasser Menge":

                    Optional<VirtualSensorValue> virtualWaterAmount = FormDialogManager.showVirtualSensorValueDialog(new VirtualWaterAmountValue(), automationDeviceResponse.getAutomationDevices());
                    if(virtualWaterAmount.isPresent()) {

                        sendCreateRequest(virtualWaterAmount.get());
                    }
                    break;
                case "Virtuelle Lichtstärke":

                    Optional<VirtualSensorValue> virtualLightIntensity = FormDialogManager.showVirtualSensorValueDialog(new VirtualLightIntensityValue(), automationDeviceResponse.getAutomationDevices());
                    if(virtualLightIntensity.isPresent()) {

                        sendCreateRequest(virtualLightIntensity.get());
                    }
                    break;
                case "Virtuelle Temeratur":

                    Optional<VirtualSensorValue> virtualTemerature = FormDialogManager.showVirtualSensorValueDialog(new VirtualTemperatureValue(), automationDeviceResponse.getAutomationDevices());
                    if(virtualTemerature.isPresent()) {

                        sendCreateRequest(virtualTemerature.get());
                    }
                    break;
            }
        }
    }

    @FXML
    void editElement(ActionEvent event) {

        tableContextMenu.hide();
        AutomationDevice device = elementsTable.getSelectionModel().getSelectedItem();
        if(device instanceof Input) {

            Optional<Input> input = FormDialogManager.showInputDialog((Input) device, automationDeviceResponse.getSwitchServers());
            if(input.isPresent()) {

                sendEditRequest(input.get());
            }
        } else if(device instanceof UserAtHome) {

            Optional<UserAtHome> userAtHome = FormDialogManager.showUserAtHomeDialog((UserAtHome) device);
            if(userAtHome.isPresent()) {

                sendEditRequest(userAtHome.get());
            }
        } else if(device instanceof Output) {

            Optional<Output> output = FormDialogManager.showOutputDialog((Output) device, automationDeviceResponse.getSwitchServers());
            if(output.isPresent()) {

                sendEditRequest(output.get());
            }
        } else if(device instanceof AvmSocket) {

            Optional<AvmSocket> avmSocket = FormDialogManager.showAvmSocketDialog((AvmSocket) device);
            if(avmSocket.isPresent()) {

                sendEditRequest(avmSocket.get());
            }
        } else if(device instanceof EdimaxSocket) {

            Optional<EdimaxSocket> edimaxSocket = FormDialogManager.showEdimaxSocketDialog((EdimaxSocket) device);
            if(edimaxSocket.isPresent()) {

                sendEditRequest(edimaxSocket.get());
            }
        } else if(device instanceof RadioSocket) {

            Optional<RadioSocket> radioSocket = FormDialogManager.showRadioSocketDialog((RadioSocket) device, automationDeviceResponse.getSwitchServers());
            if(radioSocket.isPresent()) {

                sendEditRequest(radioSocket.get());
            }
        } else if(device instanceof VirtualSocket) {

            Optional<VirtualSocket> virtualSocket = FormDialogManager.showVirtualSocketDialog((VirtualSocket) device);
            if(virtualSocket.isPresent()) {

                sendEditRequest(virtualSocket.get());
            }
        } else if(device instanceof WakeOnLan) {

            Optional<WakeOnLan> wakeOnLan = FormDialogManager.showWakeOnLanDialog((WakeOnLan) device);
            if(wakeOnLan.isPresent()) {

                sendEditRequest(wakeOnLan.get());
            }
        } else if(device instanceof FritzBoxWirelessLan) {

            Optional<FritzBoxWirelessLan> fritzBoxWirelessLan = FormDialogManager.showFritzBoxWirelessLanDialog((FritzBoxWirelessLan) device);
            if(fritzBoxWirelessLan.isPresent()) {

                sendEditRequest(fritzBoxWirelessLan.get());
            }
        } else if(device instanceof FritzBoxRebootReconnect) {

            Optional<FritzBoxRebootReconnect> fritzBoxRebootReconnect = FormDialogManager.showFritzBoxRebootReconnectDialog((FritzBoxRebootReconnect) device);
            if(fritzBoxRebootReconnect.isPresent()) {

                sendEditRequest(fritzBoxRebootReconnect.get());
            }
        } else if(device instanceof RebootShutdown) {

            Optional<RebootShutdown> rebootShutdown = FormDialogManager.showRebootShutdownDialog((RebootShutdown) device, automationDeviceResponse.getSwitchServers());
            if(rebootShutdown.isPresent()) {

                sendEditRequest(rebootShutdown.get());
            }
        } else if(device instanceof ScriptSingle) {

            Optional<ScriptSingle> scriptSingle = FormDialogManager.showScriptSingleDialog((ScriptSingle) device, automationDeviceResponse.getSwitchServers());
            if(scriptSingle.isPresent()) {

                sendEditRequest(scriptSingle.get());
            }
        } else if(device instanceof ScriptDouble) {

            Optional<ScriptDouble> scriptDouble = FormDialogManager.showScriptDoubleDialog((ScriptDouble) device, automationDeviceResponse.getSwitchServers());
            if(scriptDouble.isPresent()) {

                sendEditRequest(scriptDouble.get());
            }
        } else if(device instanceof VirtualSensorValue) {

            Optional<VirtualSensorValue> virtualSensorValue = FormDialogManager.showVirtualSensorValueDialog((VirtualSensorValue) device, automationDeviceResponse.getAutomationDevices());
            if(virtualSensorValue.isPresent()) {

                sendEditRequest(virtualSensorValue.get());
            }
        }  else if(device instanceof SensorValue) {

            Optional<SensorValue> sensorValue = FormDialogManager.showSensorValueDialog((SensorValue) device);
            if(sensorValue.isPresent()) {

                sendEditRequest(sensorValue.get());
            }
        }
    }

    @FXML
    void deleteElement(ActionEvent event) {

        AutomationDevice device = elementsTable.getSelectionModel().getSelectedItem();
        if(device != null) {

            sendDeleteRequest(device);
        }
    }

    @FXML
    void filterName(KeyEvent event) {

    }

    @FXML
    void refreshList(ActionEvent event) {

        update();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootStackPane != null : "fx:id=\"rootStackPane\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert rootBorderPane != null : "fx:id=\"rootBorderPane\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert rootToolbar != null : "fx:id=\"rootToolbar\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert buttonRefresh != null : "fx:id=\"buttonRefresh\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert toolbarSpace != null : "fx:id=\"toolbarSpace\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert filterLabel != null : "fx:id=\"filterLabel\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert inputNameFilter != null : "fx:id=\"inputNameFilter\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert inputTypeFilter != null : "fx:id=\"inputTypeFilter\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert elementsTable != null : "fx:id=\"elementsTable\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert columnName != null : "fx:id=\"columnName\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert columnType != null : "fx:id=\"columnType\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert columnActive != null : "fx:id=\"columnActive\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert columnComment != null : "fx:id=\"columnComment\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert tableContextMenu != null : "fx:id=\"tableContextMenu\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert menuButtonCreate != null : "fx:id=\"menuButtonCreate\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert menuButtonEdit != null : "fx:id=\"menuButtonEdit\" was not injected: check your FXML file 'ElementAdministration.fxml'.";
        assert menuButtonDelete != null : "fx:id=\"menuButtonDelete\" was not injected: check your FXML file 'ElementAdministration.fxml'.";

        elementsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        columnName.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        columnType.setMaxWidth(1f * Integer.MAX_VALUE * 15);
        columnActive.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        columnComment.setMaxWidth(1f * Integer.MAX_VALUE * 45);

        //Spalten
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnActive.setCellValueFactory(new PropertyValueFactory<>("Disabled"));
        columnActive.setCellFactory(param -> new ActiveCell());
        columnType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        columnType.setCellFactory(param -> new TypeCell());
        columnComment.setCellValueFactory(new PropertyValueFactory<>("Comment"));

        //Filter Liste erstellen
        inputTypeFilter.getItems().addAll("schaltbare Elemente", "lesbare Elemente", "Sensorwerte", "virtuelle Sensorwerte");
        inputTypeFilter.getCheckModel().checkAll();

        //Ladeanzeige
        rootStackPane.getChildren().add(maskerPane);
        maskerPane.setText("Bitte warten ...");

        update();

        //resizeToolbar();
        //ShcDesktopClient.getInstance().getMainViewController().getMainPane().getScene().widthProperty().addListener(e -> {

        //    resizeToolbar();
        //});
    }

    protected void update() {

        maskerPane.setVisible(true);

        menuButtonCreate.setDisable(true);
        menuButtonEdit.setDisable(true);
        menuButtonDelete.setDisable(true);

        //Geräte laden
        Task<AutomationDeviceResponse> task = new Task<AutomationDeviceResponse>() {
            @Override
            protected AutomationDeviceResponse call() throws Exception {

                try {

                    return ShcDesktopClient.getInstance().getConnectionManager().getAutomationDevices();
                } catch (IOException e) {

                    AutomationDeviceResponse automationDeviceResponse = new AutomationDeviceResponse();
                    automationDeviceResponse.setSuccess(false);
                    automationDeviceResponse.setMessage(e.getLocalizedMessage());
                    return automationDeviceResponse;
                }
            }
        };
        task.setOnSucceeded((WorkerStateEvent event) -> {

            automationDeviceResponse = (AutomationDeviceResponse) event.getSource().getValue();
            if(automationDeviceResponse != null) {

                if (automationDeviceResponse.isSuccess()) {

                    //Daten
                    elementsTable.getItems().clear();
                    elementsTable.getItems().addAll(automationDeviceResponse.getAutomationDevices().values());
                    maskerPane.setVisible(false);
                    menuButtonCreate.setDisable(false);
                    menuButtonEdit.setDisable(false);
                    menuButtonDelete.setDisable(false);
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Fehler", automationDeviceResponse.getMessage());
                    if (automationDeviceResponse.getErrorCode() == 100) {

                        ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                    }
                }
            } else {

                MainViewLoader.loadLoginView();
            }
        });
        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * sendet die Anfrage zum erstellen eines Gerätes an den Server
     *
     * @param automationDevice Automatisierungsgerät
     */
    protected void sendCreateRequest(final AutomationDevice automationDevice) {

        //Daten an Server senden
        Task<SuccessResponse> task = new Task<SuccessResponse>() {
            @Override
            protected SuccessResponse call() throws Exception {

                try {

                    return ShcDesktopClient.getInstance().getConnectionManager().addAutomationDevice(automationDevice);
                } catch (IOException e) {

                    SuccessResponse sr = new SuccessResponse();
                    sr.setSuccess(false);
                    sr.setMessage(e.getLocalizedMessage());
                    return sr;
                }
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {

            SuccessResponse successResponse = (SuccessResponse) e.getSource().getValue();
            if(successResponse != null) {

                if(successResponse.isSuccess()) {

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Das Gerät wurde erfolgreich erstellt");
                    update();
                } else {

                    maskerPane.setVisible(false);
                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Gerät erstellen fehlgeschlagen", successResponse.getMessage());
                    if(successResponse.getErrorCode() == 100) {

                        ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                    }
                }
            } else {

                MainViewLoader.loadLoginView();
            }
        });
        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * sendet die Anfrage zum bearbeiten eines Gerätes an den Server
     *
     * @param automationDevice Automatisierungsgerät
     */
    protected void sendEditRequest(final AutomationDevice automationDevice) {

        //Daten an Server senden
        Task<SuccessResponse> task = new Task<SuccessResponse>() {
            @Override
            protected SuccessResponse call() throws Exception {

                try {

                    return ShcDesktopClient.getInstance().getConnectionManager().editAutomationDevice(automationDevice);
                } catch (IOException e) {

                    SuccessResponse sr = new SuccessResponse();
                    sr.setSuccess(false);
                    sr.setMessage(e.getLocalizedMessage());
                    return sr;
                }
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {

            SuccessResponse successResponse = (SuccessResponse) e.getSource().getValue();
            if(successResponse != null) {

                if(successResponse.isSuccess()) {

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Das Gerät wurde erfolgreich bearbeitet");
                    update();
                } else {

                    maskerPane.setVisible(false);
                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Gerät bearbeiten fehlgeschlagen", successResponse.getMessage());
                    if(successResponse.getErrorCode() == 100) {

                        ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                    }
                }
            } else {

                MainViewLoader.loadLoginView();
            }
        });
        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * sendet die Anfrage zum löschen eines Gerätes an den Server
     *
     * @param automationDevice Automatisierungsgerät
     */
    protected void sendDeleteRequest(final AutomationDevice automationDevice) {

        //Daten an Server senden
        Task<SuccessResponse> task = new Task<SuccessResponse>() {
            @Override
            protected SuccessResponse call() throws Exception {

                try {

                    return ShcDesktopClient.getInstance().getConnectionManager().deleteAutomationDevice(automationDevice);
                } catch (IOException e) {

                    SuccessResponse sr = new SuccessResponse();
                    sr.setSuccess(false);
                    sr.setMessage(e.getLocalizedMessage());
                    return sr;
                }
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {

            SuccessResponse successResponse = (SuccessResponse) e.getSource().getValue();
            if(successResponse != null) {

                if(successResponse.isSuccess()) {

                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "", "Das Gerät wurde erfolgreich gelöscht");
                    update();
                } else {

                    maskerPane.setVisible(false);
                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Gerät löschen fehlgeschlagen", successResponse.getMessage());
                    if(successResponse.getErrorCode() == 100) {

                        ShcDesktopClient.getInstance().getConnectionManager().setSessionidInvalid();
                    }
                }
            } else {

                MainViewLoader.loadLoginView();
            }
        });
        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * passt die Toolbar in der Breite an
     */
    protected void resizeToolbar() {

        //Abstand zwischen den Toolbar feldern
        toolbarSpace.setPrefWidth(
                rootToolbar.getWidth()
                - buttonBack.getWidth()
                - buttonRefresh.getWidth()
                - filterLabel.getWidth()
                - inputNameFilter.getWidth()
                - inputTypeFilter.getWidth()
                - 40
        );
    }
}
