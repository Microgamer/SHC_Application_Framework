package net.kleditzsch.shcDesktopClient.View.Admin.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import net.kleditzsch.shcCore.ClientData.SettingsResponse;
import net.kleditzsch.shcCore.ClientData.SuccessResponse;
import net.kleditzsch.shcCore.Settings.BooleanSetting;
import net.kleditzsch.shcCore.Settings.DoubleSetting;
import net.kleditzsch.shcCore.Settings.IntegerSetting;
import net.kleditzsch.shcCore.Settings.StringSetting;
import net.kleditzsch.shcCore.User.Permissions;
import net.kleditzsch.shcCore.Util.LoggerUtil;
import net.kleditzsch.shcDesktopClient.Core.ShcDesktopClient;
import net.kleditzsch.shcDesktopClient.Settings.Settings;
import net.kleditzsch.shcDesktopClient.Util.UiNotificationHelper;
import net.kleditzsch.shcDesktopClient.View.MainViewLoader;
import org.controlsfx.control.MaskerPane;

/**
 * Einstellungen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SettingsAdministrationController {

    private static Logger logger = LoggerUtil.getLogger(SettingsAdministrationController.class);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootBorderPane"
    private BorderPane rootBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="tabPane"
    private TabPane tabPane; // Value injected by FXMLLoader

    @FXML // fx:id="tabServerSettings"
    private Tab tabServerSettings; // Value injected by FXMLLoader

    @FXML // fx:id="serverSettingsStackPane"
    private StackPane serverSettingsStackPane; // Value injected by FXMLLoader

    @FXML // fx:id="inputSunriseOffset"
    private Spinner<Integer> inputSunriseOffset; // Value injected by FXMLLoader

    @FXML // fx:id="inputSunsetOffset"
    private Spinner<Integer> inputSunsetOffset; // Value injected by FXMLLoader

    @FXML // fx:id="inputLatitude"
    private Spinner<Double> inputLatitude; // Value injected by FXMLLoader

    @FXML // fx:id="inputLogitude"
    private Spinner<Double> inputLogitude; // Value injected by FXMLLoader

    @FXML // fx:id="inputFritzBoxAddress"
    private TextField inputFritzBoxAddress; // Value injected by FXMLLoader

    @FXML // fx:id="inputFritzBoxUser"
    private TextField inputFritzBoxUser; // Value injected by FXMLLoader

    @FXML // fx:id="inputFritzBoxActive"
    private CheckBox inputFritzBoxActive; // Value injected by FXMLLoader

    @FXML // fx:id="inputFritzBoxPassword"
    private PasswordField inputFritzBoxPassword; // Value injected by FXMLLoader

    @FXML // fx:id="inputElectricPrice"
    private Spinner<Double> inputElectricPrice; // Value injected by FXMLLoader

    @FXML // fx:id="inputWaterPrice"
    private Spinner<Double> inputWaterPrice; // Value injected by FXMLLoader

    @FXML // fx:id="inputGasPrice"
    private Spinner<Double> inputGasPrice; // Value injected by FXMLLoader

    @FXML // fx:id="tabAppSettings"
    private Tab tabAppSettings; // Value injected by FXMLLoader

    @FXML // fx:id="inputServerAddress"
    private TextField inputServerAddress; // Value injected by FXMLLoader

    @FXML // fx:id="inputPort"
    private Spinner<Integer> inputPort; // Value injected by FXMLLoader

    @FXML // fx:id="inputClientHash"
    private TextField inputClientHash; // Value injected by FXMLLoader

    @FXML // fx:id="inputUserAgent"
    private TextField inputUserAgent; // Value injected by FXMLLoader

    @FXML // fx:id="inputUserName"
    private TextField inputUserName; // Value injected by FXMLLoader

    @FXML // fx:id="inputUserHash"
    private TextField inputUserHash; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSave"
    private Button buttonSave; // Value injected by FXMLLoader

    @FXML // fx:id="buttonResetToDefault"
    private Button buttonResetToDefault; // Value injected by FXMLLoader

    @FXML // fx:id="buttobBack"
    private Button buttobBack; // Value injected by FXMLLoader

    /**
     * Ladeanzeige
     */
    private MaskerPane maskerPane = new MaskerPane();

    /**
     * Einstellungen vom Server
     */
    protected SettingsResponse settingsResponse;

    @FXML
    void back(ActionEvent event) {

        MainViewLoader.loadAdminMenueView();
    }

    @FXML
    void resetToDefault(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

        if(tabAppSettings.isSelected()) {

            saveAppSettings();
        } else if(tabServerSettings.isSelected()) {

            saveServerSettings();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootBorderPane != null : "fx:id=\"rootBorderPane\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert tabServerSettings != null : "fx:id=\"tabServerSettings\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert serverSettingsStackPane != null : "fx:id=\"serverSettingsStackPane\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputSunriseOffset != null : "fx:id=\"inputSunriseOffset\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputSunsetOffset != null : "fx:id=\"inputSunsetOffset\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputLatitude != null : "fx:id=\"inputLatitude\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputLogitude != null : "fx:id=\"inputLogitude\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputFritzBoxAddress != null : "fx:id=\"inputFritzBoxAddress\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputFritzBoxUser != null : "fx:id=\"inputFritzBoxUser\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputFritzBoxActive != null : "fx:id=\"inputFritzBoxActive\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputFritzBoxPassword != null : "fx:id=\"inputFritzBoxPassword\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputElectricPrice != null : "fx:id=\"inputElectricPrice\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputWaterPrice != null : "fx:id=\"inputWaterPrice\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputGasPrice != null : "fx:id=\"inputGasPrice\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert tabAppSettings != null : "fx:id=\"tabAppSettings\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputServerAddress != null : "fx:id=\"inputServerAddress\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputPort != null : "fx:id=\"inputPort\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputClientHash != null : "fx:id=\"inputClientHash\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputUserAgent != null : "fx:id=\"inputUserAgent\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputUserName != null : "fx:id=\"inputUserName\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert inputUserHash != null : "fx:id=\"inputUserHash\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert buttonResetToDefault != null : "fx:id=\"buttonResetToDefault\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";
        assert buttobBack != null : "fx:id=\"buttobBack\" was not injected: check your FXML file 'SettingsAdministration.fxml'.";

        //Berechtigung überprüfen
        if(!ShcDesktopClient.getInstance().getConnectionManager().checkPermission(Permissions.SETTINGS_ADMINISTRATION)) {

            tabServerSettings.setDisable(true);
        }

        //Formularfelder initalisieren

        //Port
        SpinnerValueFactory.IntegerSpinnerValueFactory portValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 0);
        inputPort.setValueFactory(portValueFactory);
        inputPort.setEditable(true);
        inputPort.getEditor().textProperty().addListener(e -> {

            if(!inputPort.isEditable()) {

                return;
            }

            String text = inputPort.getEditor().getText();
            SpinnerValueFactory<Integer> vf = inputPort.getValueFactory();
            if(vf != null) {

                StringConverter<Integer> converter = vf.getConverter();
                if(converter != null && text != null) {

                    vf.setValue(converter.fromString(text));
                }
            }
        });

        //Sonnenaufgang Offset
        SpinnerValueFactory.IntegerSpinnerValueFactory sunriseOffsetValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-90, 90, 0);
        inputSunriseOffset.setValueFactory(sunriseOffsetValueFactory);
        inputSunriseOffset.setEditable(true);
        inputSunriseOffset.getEditor().textProperty().addListener(e -> {

            if(!inputSunriseOffset.isEditable()) {

                return;
            }

            String text = inputSunriseOffset.getEditor().getText();
            SpinnerValueFactory<Integer> vf = inputSunriseOffset.getValueFactory();
            if(vf != null) {

                StringConverter<Integer> converter = vf.getConverter();
                if(converter != null && text != null) {

                    vf.setValue(converter.fromString(text));
                }
            }
        });

        //Sonnenuntergang Offset
        SpinnerValueFactory.IntegerSpinnerValueFactory sunsetOffsetValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-90, 90, 0);
        inputSunsetOffset.setValueFactory(sunsetOffsetValueFactory);
        inputSunsetOffset.setEditable(true);
        inputSunsetOffset.getEditor().textProperty().addListener(e -> {

            if(!inputSunsetOffset.isEditable()) {

                return;
            }

            String text = inputSunsetOffset.getEditor().getText();
            SpinnerValueFactory<Integer> vf = inputSunsetOffset.getValueFactory();
            if(vf != null) {

                StringConverter<Integer> converter = vf.getConverter();
                if(converter != null && text != null) {

                    vf.setValue(converter.fromString(text));
                }
            }
        });

        //Latitude
        SpinnerValueFactory.DoubleSpinnerValueFactory latitudeValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(-1000, 1000, 0);
        latitudeValueFactory.setAmountToStepBy(0.1);
        inputLatitude.setValueFactory(latitudeValueFactory);
        inputLatitude.setEditable(true);
        inputLatitude.getEditor().textProperty().addListener(e -> {

            if(!inputLatitude.isEditable()) {

                return;
            }

            String text = inputLatitude.getEditor().getText();
            SpinnerValueFactory<Double> vf = inputLatitude.getValueFactory();
            if(vf != null) {

                StringConverter<Double> converter = vf.getConverter();
                if(converter != null && text != null) {

                    vf.setValue(converter.fromString(text));
                }
            }
        });

        //Longitute
        SpinnerValueFactory.DoubleSpinnerValueFactory longitudeValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(-1000, 1000, 0);
        longitudeValueFactory.setAmountToStepBy(0.1);
        inputLogitude.setValueFactory(longitudeValueFactory);
        inputLogitude.setEditable(true);
        inputLogitude.getEditor().textProperty().addListener(e -> {

            if(!inputLogitude.isEditable()) {

                return;
            }

            String text = inputLogitude.getEditor().getText();
            SpinnerValueFactory<Double> vf = inputLogitude.getValueFactory();
            if(vf != null) {

                StringConverter<Double> converter = vf.getConverter();
                if(converter != null && text != null) {

                    vf.setValue(converter.fromString(text));
                }
            }
        });

        //Fritz!Box
        inputFritzBoxAddress.disableProperty().bind(inputFritzBoxActive.selectedProperty().not());
        inputFritzBoxUser.disableProperty().bind(inputFritzBoxActive.selectedProperty().not());
        inputFritzBoxPassword.disableProperty().bind(inputFritzBoxActive.selectedProperty().not());

        //Strompreis
        SpinnerValueFactory.DoubleSpinnerValueFactory electricPriceValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 60, 0);
        electricPriceValueFactory.setAmountToStepBy(0.01);
        inputElectricPrice.setValueFactory(electricPriceValueFactory);
        inputElectricPrice.setEditable(true);
        inputElectricPrice.getEditor().textProperty().addListener(e -> {

            if(!inputElectricPrice.isEditable()) {

                return;
            }

            String text = inputElectricPrice.getEditor().getText();
            SpinnerValueFactory<Double> vf = inputElectricPrice.getValueFactory();
            if(vf != null) {

                StringConverter<Double> converter = vf.getConverter();
                if(converter != null && text != null) {

                    vf.setValue(converter.fromString(text));
                }
            }
        });

        //Wasserpreis
        SpinnerValueFactory.DoubleSpinnerValueFactory waterPriceValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 60, 0);
        waterPriceValueFactory.setAmountToStepBy(0.01);
        inputWaterPrice.setValueFactory(waterPriceValueFactory);
        inputWaterPrice.setEditable(true);
        inputWaterPrice.getEditor().textProperty().addListener(e -> {

            if(!inputWaterPrice.isEditable()) {

                return;
            }

            String text = inputWaterPrice.getEditor().getText();
            SpinnerValueFactory<Double> vf = inputWaterPrice.getValueFactory();
            if(vf != null) {

                StringConverter<Double> converter = vf.getConverter();
                if(converter != null && text != null) {

                    vf.setValue(converter.fromString(text));
                }
            }
        });

        //Gasrpreis
        SpinnerValueFactory.DoubleSpinnerValueFactory gasPriceValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 60, 0);
        gasPriceValueFactory.setAmountToStepBy(0.01);
        inputGasPrice.setValueFactory(gasPriceValueFactory);
        inputGasPrice.setEditable(true);
        inputGasPrice.getEditor().textProperty().addListener(e -> {

            if(!inputGasPrice.isEditable()) {

                return;
            }

            String text = inputGasPrice.getEditor().getText();
            SpinnerValueFactory<Double> vf = inputGasPrice.getValueFactory();
            if(vf != null) {

                StringConverter<Double> converter = vf.getConverter();
                if(converter != null && text != null) {

                    vf.setValue(converter.fromString(text));
                }
            }
        });

        //Auf Tab änderungen reagieren
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue == tabAppSettings) {

                updateAppSettings();
                buttonSave.setDisable(false);
            } else if(newValue == tabServerSettings) {

                updateServerSettings();
            }
        });
        updateAppSettings();

        //Ladeanzeige für die Servereinstellungen initalisieren
        serverSettingsStackPane.getChildren().add(maskerPane);
        maskerPane.setText("Bitte warten ...");
        maskerPane.setVisible(false);
    }

    /**
     * aktualisiert die App Einstellungen
     */
    protected void updateAppSettings() {

        Settings settings = ShcDesktopClient.getInstance().getSettings();

        //Server
        inputServerAddress.setText(settings.getStringSetting(Settings.SETTING_SERVER_ADDRESS).getValue());
        inputPort.getValueFactory().setValue(settings.getIntegerSetting(Settings.SETTING_SERVER_PORT).getValue());

        //Anmeldedaten
        inputClientHash.setText(settings.getStringSetting(Settings.SETTING_SERVER_CLIENT_HASH).getValue());
        inputUserAgent.setText(ShcDesktopClient.getInstance().getUserAgent());
        inputUserName.setText(settings.getStringSetting(Settings.SETTING_SERVER_USER).getValue());
        inputUserHash.setText(settings.getStringSetting(Settings.SETTING_SERVER_IDENTIFIER).getValue());
        logger.info("Anwendungseinstellungen geladen");
    }

    /**
     * speichert die APP Einstellungen
     */
    protected void saveAppSettings() {

        Settings settings = ShcDesktopClient.getInstance().getSettings();

        //Server
        settings.getStringSetting(Settings.SETTING_SERVER_ADDRESS).setValue(inputServerAddress.getText());
        settings.getIntegerSetting(Settings.SETTING_SERVER_PORT).setValue(inputPort.getValueFactory().getValue());

        //Anmeldedaten
        settings.getStringSetting(Settings.SETTING_SERVER_USER).setValue(inputUserName.getText());
        settings.getStringSetting(Settings.SETTING_SERVER_IDENTIFIER).setValue(inputUserHash.getText());

        try {

            settings.dump();
            logger.info("Anwendungseinstellungen gespeichert");
            UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "App Einstellungen", "Die App Einstellungen wurden erfolgreich gespeichert");
        } catch (IOException e) {

            logger.log(Level.SEVERE, "Anwendungseinstellungen nicht gespeichert", e);
            UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "App Einstellungen", "Die App Einstellungen konnten nicht gespeichert werden");
        }
    }

    /**
     * Läadt die Server EInstellungen
     */
    protected void updateServerSettings() {

        maskerPane.setVisible(true);
        buttonSave.setDisable(true);

        //Einstellungen Laden
        Task<SettingsResponse> task = new Task<SettingsResponse>() {
            @Override
            protected SettingsResponse call() throws Exception {

                try {

                    return ShcDesktopClient.getInstance().getConnectionManager().getSettings();
                } catch (IOException e) {

                    SettingsResponse sr = new SettingsResponse();
                    sr.setSuccess(false);
                    sr.setMessage(e.getLocalizedMessage());
                    return sr;
                }
            }
        };
        task.setOnSucceeded((WorkerStateEvent event) -> {

            settingsResponse = (SettingsResponse) event.getSource().getValue();
            if(settingsResponse != null) {

                if(settingsResponse.isSuccess()) {

                    //Eingabefelder initalisieren
                    inputSunriseOffset.getValueFactory().setValue(settingsResponse.getIntegerSettings().get("setting.sunrise.offset").getValue());
                    inputSunsetOffset.getValueFactory().setValue(settingsResponse.getIntegerSettings().get("setting.sunset.offset").getValue());
                    inputLatitude.getValueFactory().setValue(settingsResponse.getDoubleSettings().get("setting.location.latitude").getValue());
                    inputLogitude.getValueFactory().setValue(settingsResponse.getDoubleSettings().get("setting.location.longitude").getValue());

                    inputFritzBoxActive.setSelected(settingsResponse.getBooleanSettings().get("setting.fritzbox.active").getValue());
                    inputFritzBoxAddress.setText(settingsResponse.getStringSettings().get("setting.fritzbox.address").getValue());
                    inputFritzBoxUser.setText(settingsResponse.getStringSettings().get("setting.fritzbox.user").getValue());
                    inputFritzBoxPassword.setText(settingsResponse.getStringSettings().get("setting.fritzbox.password").getValue());

                    inputElectricPrice.getValueFactory().setValue(settingsResponse.getDoubleSettings().get("setting.energy.electric.price").getValue());
                    inputWaterPrice.getValueFactory().setValue(settingsResponse.getDoubleSettings().get("setting.energy.water.price").getValue());
                    inputGasPrice.getValueFactory().setValue(settingsResponse.getDoubleSettings().get("setting.energy.gas.price").getValue());

                    maskerPane.setVisible(false);
                    buttonSave.setDisable(false);
                    logger.info("Server Einstellungen geladen");

                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "Fehler", settingsResponse.getMessage());
                    logger.warning("Server Einstellungen nicht geladen -> " + settingsResponse.getMessage());
                    if(settingsResponse.getErrorCode() == 100) {

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
     * speichert die Server Einstellungen
     */
    protected void saveServerSettings() {

        maskerPane.setVisible(true);
        buttonSave.setDisable(true);

        //Daten der EIngebefelder laden
        IntegerSetting sunriseOffset = settingsResponse.getIntegerSettings().get("setting.sunrise.offset");
        sunriseOffset.setValue(inputSunriseOffset.getValueFactory().getValue());
        IntegerSetting sunsetOffset = settingsResponse.getIntegerSettings().get("setting.sunset.offset");
        sunsetOffset.setValue(inputSunsetOffset.getValueFactory().getValue());
        DoubleSetting latitude = settingsResponse.getDoubleSettings().get("setting.location.latitude");
        latitude.setValue(inputLatitude.getValueFactory().getValue());
        DoubleSetting longitude = settingsResponse.getDoubleSettings().get("setting.location.longitude");
        longitude.setValue(inputLogitude.getValueFactory().getValue());

        BooleanSetting fritzBoxActive = settingsResponse.getBooleanSettings().get("setting.fritzbox.active");
        fritzBoxActive.setValue(inputFritzBoxActive.isSelected());
        StringSetting fritzBoxAddress = settingsResponse.getStringSettings().get("setting.fritzbox.address");
        fritzBoxAddress.setValue(inputFritzBoxAddress.getText());
        StringSetting fritzBoxUser = settingsResponse.getStringSettings().get("setting.fritzbox.user");
        fritzBoxUser.setValue(inputFritzBoxUser.getText());
        StringSetting fritzBoxPassword = settingsResponse.getStringSettings().get("setting.fritzbox.password");
        fritzBoxPassword.setValue(inputFritzBoxPassword.getText());

        DoubleSetting electricPrice = settingsResponse.getDoubleSettings().get("setting.energy.electric.price");
        electricPrice.setValue(inputElectricPrice.getValueFactory().getValue());
        DoubleSetting waterPrice = settingsResponse.getDoubleSettings().get("setting.energy.water.price");
        waterPrice.setValue(inputWaterPrice.getValueFactory().getValue());
        DoubleSetting gasPrice = settingsResponse.getDoubleSettings().get("setting.energy.gas.price");
        gasPrice.setValue(inputGasPrice.getValueFactory().getValue());

        //Daten Vorbereiten
        SettingsResponse settingsRequest = new SettingsResponse();
        settingsRequest.getIntegerSettings().put("setting.sunrise.offset", sunriseOffset);
        settingsRequest.getIntegerSettings().put("setting.sunset.offset", sunsetOffset);
        settingsRequest.getDoubleSettings().put("setting.location.latitude", latitude);
        settingsRequest.getDoubleSettings().put("setting.location.longitude", longitude);

        settingsRequest.getBooleanSettings().put("setting.fritzbox.active", fritzBoxActive);
        settingsRequest.getStringSettings().put("setting.fritzbox.address", fritzBoxAddress);
        settingsRequest.getStringSettings().put("setting.fritzbox.user", fritzBoxUser);
        settingsRequest.getStringSettings().put("setting.fritzbox.password", fritzBoxPassword);

        settingsRequest.getDoubleSettings().put("setting.energy.electric.price", electricPrice);
        settingsRequest.getDoubleSettings().put("setting.energy.water.price", waterPrice);
        settingsRequest.getDoubleSettings().put("setting.energy.gas.price", gasPrice);

        //Einstellungen speichern
        Task<SuccessResponse> task = new Task<SuccessResponse>() {
            @Override
            protected SuccessResponse call() throws Exception {

                try {

                    return ShcDesktopClient.getInstance().getConnectionManager().setSettings(settingsRequest);
                } catch (IOException e) {

                    SuccessResponse sur = new SuccessResponse();
                    sur.setSuccess(false);
                    sur.setMessage(e.getLocalizedMessage());
                    return sur;
                }
            }
        };
        task.setOnSucceeded((WorkerStateEvent event) -> {

            SuccessResponse successResponse = (SuccessResponse) event.getSource().getValue();
            if(successResponse != null) {

                if(successResponse.isSuccess()) {

                    //Daten
                    maskerPane.setVisible(false);
                    buttonSave.setDisable(false);

                    //Erfolgsmeldung
                    logger.info("Server EInstellungen gespeichert");
                    UiNotificationHelper.showInfoNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "App Einstellungen", "Die Server Einstellungen wurden erfolgreich gespeichert");
                } else {

                    UiNotificationHelper.showErrorNotification(ShcDesktopClient.getInstance().getPrimaryStage(), "App Einstellungen", "Die Server Einstellungen konnten nicht gespeichert werden");
                    logger.warning("Server Einstellungen nicht gespeichert -> " + successResponse.getMessage());
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
}
