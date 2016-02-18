package net.kleditzsch.shcApplicationServer.Core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.kleditzsch.shcApplicationServer.Database.Redis;
import net.kleditzsch.shcApplicationServer.DeviceManager.DeviceManager;
import net.kleditzsch.shcApplicationServer.HTTPInterface.ServerRunnable;
import net.kleditzsch.shcApplicationServer.Json.Serializer.Room.BoxSerializer;
import net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements.*;
import net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements.Groups.ActivitySerializer;
import net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements.Groups.ButtonSerializer;
import net.kleditzsch.shcApplicationServer.Json.Serializer.Room.Elements.Groups.CountdownSerializer;
import net.kleditzsch.shcApplicationServer.Json.Serializer.Room.RoomSerializer;
import net.kleditzsch.shcApplicationServer.Json.Serializer.User.UserSerializer;
import net.kleditzsch.shcApplicationServer.Room.Box;
import net.kleditzsch.shcApplicationServer.Room.Elements.*;
import net.kleditzsch.shcApplicationServer.Room.Elements.Groups.Activity;
import net.kleditzsch.shcApplicationServer.Room.Elements.Groups.Button;
import net.kleditzsch.shcApplicationServer.Room.Elements.Groups.Countdown;
import net.kleditzsch.shcApplicationServer.Room.Room;
import net.kleditzsch.shcApplicationServer.Room.RoomEditor;
import net.kleditzsch.shcApplicationServer.Session.SessionEditor;
import net.kleditzsch.shcApplicationServer.Settings.Settings;
import net.kleditzsch.shcApplicationServer.SwitchServer.SwitchServerEditor;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcApplicationServer.Util.CliConfigEditor;
import net.kleditzsch.shcCore.Json.LocalDateSerializer;
import net.kleditzsch.shcCore.Json.LocalDateTimeSerializer;
import net.kleditzsch.shcCore.Json.LocalTimeSerializer;
import net.kleditzsch.shcCore.User.User;
import redis.clients.jedis.Jedis;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Basisklasse
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2015, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class ShcApplicationServer {

    /**
     * Server Version
     */
    public static final String VERSION = "0.1.0";

    /**
     * Singleton Instanz
     */
    private static ShcApplicationServer instance;

    /**
     * Debug Modus Aktiv?
     */
    private static boolean debug = false;

    /**
     * Datenbankverbindung
     */
    private Jedis redis;

    /**
     * Gson Builder
     */
    private GsonBuilder builder;

    /**
     * EInstellungen
     */
    private Settings settings;

    /**
     * Benutzer verwaltung
     */
    private UserEditor userEditor;

    /**
     * Schaltserver verwaltung
     */
    private SwitchServerEditor switchServerEditor;

    /**
     * Sessionverwaltung
     */
    private SessionEditor sessionEditor;

    /**
     * Gerätemanager
     */
    private DeviceManager deviceManager;

    /**
     * Raum Verwaltung
     */
    private RoomEditor roomEditor;

    /**
     * Eintrittspunkt in die Anwendung
     *
     * @param args
     */
    public static void main(String[] args) {

        //Kommandozeilen Parameter in ein SET übernehmen
        Set<String> arguments = new HashSet<>();
        Collections.addAll(arguments, args);

        //Version anzeigen
        if(arguments.contains("-v") || arguments.contains("--version")) {

            System.out.println("Version: " + VERSION);
            return;
        }

        //Debug Modus
        if(arguments.contains("-d") || arguments.contains("--Debug")) {

            debug = true;
        }

        //Datenbank Konfiguration
        Path dbConfigFile = Paths.get("db.config.json");
        if(arguments.contains("-db") || arguments.contains("--Database") || !Files.exists(dbConfigFile)) {

            CliConfigEditor.startDatabaseConfig();
            return;
        }

        //Anwendung initalisieren
        instance = new ShcApplicationServer();
        instance.initAppliaction();

        //Datenbank Konfiguration
        if(arguments.contains("-c") || arguments.contains("--config")) {

            CliConfigEditor.startApplicationConfig();
            return;
        }

        //Shutdown Funktion
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                ShcApplicationServer.getInstance().saveApplicationData();
            }
        });

        //HTTP Server starten
        ServerRunnable httpServer = new ServerRunnable();
        Thread serverThread = new Thread(httpServer);
        serverThread.start();

        //Speicherdienst starten
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleAtFixedRate((Runnable) () -> {

            ShcApplicationServer.getInstance().saveApplicationData();
        },
                30, //Startverzögerung
                30, //Intervall
                TimeUnit.SECONDS
        );
    }

    /**
     * initalisiert die Anwendung
     */
    private void initAppliaction() {

        //Gson initalisieren
        initGson();

        //Datenbank Verbindung
        initDatabase();

        //Daten laden
        initData();

        //Info komplett durchlaufen
        if(isDebug()) {

            System.out.println("Initialisierung beendet");
        }
    }

    /**
     * initialisiert die Gson Json API
     */
    private void initGson() {

        builder = new GsonBuilder();

        //Serialisierer und Deserialisierer anmelden

        //User
        builder.registerTypeAdapter(User.class, new UserSerializer());

        //Raum
        builder.registerTypeAdapter(AvmSocket.class, new AvmSocketSerializer());
        builder.registerTypeAdapter(Battery.class, new BatterySerializer());
        builder.registerTypeAdapter(Bmp.class, new BmpSerializer());
        builder.registerTypeAdapter(DHT.class, new DhtSerializer());
        builder.registerTypeAdapter(DS18X20.class, new DS18X20Serializer());
        builder.registerTypeAdapter(EdimaxSocket.class, new EdimaxSocketSerializer());
        builder.registerTypeAdapter(ElectricMeter.class, new ElectricMeterSerializer());
        builder.registerTypeAdapter(FritzBox.class, new FritzBoxSerializer());
        builder.registerTypeAdapter(GasMeter.class, new GasMeterSerializer());
        builder.registerTypeAdapter(HcSr04.class, new HcSr04Serializer());
        builder.registerTypeAdapter(Hygrometer.class, new HygrometerSerializer());
        builder.registerTypeAdapter(Input.class, new InputSerializer());
        builder.registerTypeAdapter(LDR.class, new LdrSerializer());
        builder.registerTypeAdapter(Output.class, new OutputSerializer());
        builder.registerTypeAdapter(RadioSocket.class, new RadioSocketSerializer());
        builder.registerTypeAdapter(Rain.class, new RainSerializer());
        builder.registerTypeAdapter(Reboot.class, new RebootSerializer());
        builder.registerTypeAdapter(Script.class, new ScriptSerializer());
        builder.registerTypeAdapter(Sct013.class, new Sct013Serializer());
        builder.registerTypeAdapter(Shutdown.class, new ShutdownSerializer());
        builder.registerTypeAdapter(UserAtHome.class, new UserAtHomeSerializer());
        builder.registerTypeAdapter(VirtualActualPower.class, new VirtualActualPowerSerializer());
        builder.registerTypeAdapter(VirtualAmount.class, new VirtualAmountSerializer());
        builder.registerTypeAdapter(VirtualEnergy.class, new VirtualEnergySerializer());
        builder.registerTypeAdapter(VirtualHumidity.class, new VirtualHumiditySerializer());
        builder.registerTypeAdapter(VirtualHumidity.class, new VirtualLightIntensitySerializer());
        builder.registerTypeAdapter(VirtualMoisture.class, new VirtualMoistureSerializer());
        builder.registerTypeAdapter(VirtualSocket.class, new VirtualSocketSerializer());
        builder.registerTypeAdapter(VirtualTemperature.class, new VirtualTemperatureSerializer());
        builder.registerTypeAdapter(WakeOnLan.class, new WakeOnLanSerializer());
        builder.registerTypeAdapter(WaterMeter.class, new WaterMeterSerializer());

        builder.registerTypeAdapter(Activity.class, new ActivitySerializer());
        builder.registerTypeAdapter(Countdown.class, new CountdownSerializer());
        builder.registerTypeAdapter(Button.class, new ButtonSerializer());

        builder.registerTypeAdapter(Box.class, new BoxSerializer());
        builder.registerTypeAdapter(Room.class, new RoomSerializer());

        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        builder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
    }

    /**
     * erzeugt ein Gson Objekt aus dem Builder heraus
     *
     * @return
     */
    public Gson getGson() {

        return builder.create();
    }

    /**
     * baut die Datenbankverbindung auf
     */
    private void initDatabase() {

        Redis redis = new Redis();
        this.redis = redis.connect();
    }

    /**
     * gibt die Datenbankverbindung zurück
     *
     * @return
     */
    public Jedis getRedis() {
        return redis;
    }

    /**
     * läd alle SHC Daten aus der Datenbank
     */
    private void initData() {

        //Einstellungen laden
        settings = new Settings();
        settings.loadData();

        //Benutzer laden
        userEditor = new UserEditor();
        userEditor.loadData();

        //Sessions laden
        sessionEditor = new SessionEditor();

        //Schaltserver laden
        switchServerEditor = new SwitchServerEditor();
        switchServerEditor.loadData();

        //Räume laden
        roomEditor = new RoomEditor();
        roomEditor.loadData();

        //Gerätemanager
        deviceManager = new DeviceManager();
        deviceManager.loadData();
    }

    /**
     * gibt die Einstellungs Verwaltung zurück
     *
     * @return
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * gibt die Benutzer Verwaltung zurück
     *
     * @return
     */
    public UserEditor getUserEditor() {
        return userEditor;
    }

    /**
     * gibt den SessionEditor zurück
     *
     * @return
     */
    public SessionEditor getSessionEditor() {
        return sessionEditor;
    }

    /**
     * gibt den Gerätemanager zurück
     *
     * @return
     */
    public DeviceManager getDeviceManager() {
        return deviceManager;
    }

    /**
     * gibt den Schaltserver Editor zurück
     *
     * @return
     */
    public SwitchServerEditor getSwitchServerEditor() {
        return switchServerEditor;
    }

    /**
     * gibt den Raum Editor zurück
     *
     * @return
     */
    public RoomEditor getRoomEditor() {
        return roomEditor;
    }

    /**
     * bereitet den Server zum herunterfahren vor
     */
    public void saveApplicationData() {

        settings.saveData();
        userEditor.saveData();
        deviceManager.saveData();
        switchServerEditor.saveData();
        roomEditor.saveData();
    }

    /**
     * gibt die aktuelle Instanz der Anwendung zurück
     *
     * @return
     */
    public static ShcApplicationServer getInstance() {
        return instance;
    }

    /**
     * gibt an ob der Debug Modus aktiv ist
     *
     * @return true wenn aktiv
     */
    public static boolean isDebug() {
        return debug;
    }
}
