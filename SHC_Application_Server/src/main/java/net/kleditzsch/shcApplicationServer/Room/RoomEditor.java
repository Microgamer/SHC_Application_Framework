package net.kleditzsch.shcApplicationServer.Room;

import com.google.gson.Gson;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Database.DatabaseEditor;
import net.kleditzsch.shcApplicationServer.Room.Elements.*;
import net.kleditzsch.shcApplicationServer.Room.Elements.Groups.Activity;
import net.kleditzsch.shcApplicationServer.Room.Elements.Groups.Button;
import net.kleditzsch.shcApplicationServer.Room.Elements.Groups.Countdown;
import net.kleditzsch.shcCore.Room.Elements.Interface.RoomElement;
import net.kleditzsch.shcCore.Room.Elements.Interface.ViewElements;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * Raum Verwaltung
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class RoomEditor implements DatabaseEditor {

    public static final String KEY_ROOM_ELEMENTS = "shc:roomElements";

    /**
     * Liste aller Raum Elemente
     */
    protected Map<String, RoomElement> roomElements = new HashMap<>();

    /**
     * Liste aller Räume
     */
    protected Map<String, Room> rooms = new HashMap<>();

    /**
     * lädt die Daten des Editors aus der Datenbank
     */
    @Override
    public void loadData() {

        //Datenbank Verbindung holen
        Jedis redis = ShcApplicationServer.getInstance().getRedis();

        //Gson Objekt holen
        Gson gson = ShcApplicationServer.getInstance().getGson();

        //Standard Elemente
        Map<String, String> elements;
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.AVM_SOCKET);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, AvmSocket.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.BATTERY);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Battery.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.BMP);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Bmp.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.DHT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, DHT.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.DS18X20);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, DS18X20.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.EDIMAX_SOCKET);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, EdimaxSocket.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.ELECTRIC_METER);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, ElectricMeter.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.FRITZ_BOX);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, FritzBox.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.GAS_METER);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, GasMeter.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.HC_SR04);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, HcSr04.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.HYGROMETER);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Hygrometer.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.INPUT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Input.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.LDR);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, LDR.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.OUTPUT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Output.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.RADIO_SOCKET);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, RadioSocket.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.RAIN);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Rain.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.REBOOT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Reboot.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.SCRIPT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Script.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.SCT_013);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Sct013.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.SHUTDOWN);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Shutdown.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.USER_AT_HOME);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, UserAtHome.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_ACTUEL_POWER);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, VirtualActualPower.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_AMOUNT);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, VirtualAmount.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_ENERGY);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, VirtualEnergy.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_HUMIDITY);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, VirtualHumidity.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_LIGHT_INTENSITY);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, VirtualLightIntensity.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_MOISTURE);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, VirtualMoisture.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_SOCKET);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, VirtualSocket.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_TEMPERATURE);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, VirtualTemperature.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.WAKE_ON_LAN);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, WakeOnLan.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.WATER_METER);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, WaterMeter.class));
        }

        //Aktivität/Countdown/Button
        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.ACTIVITY);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Activity.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.COUNTDOWN);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Countdown.class));
        }

        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.BUTTON);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Button.class));
        }

        //Box
        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.BOX);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            roomElements.put(hash, gson.fromJson(elementJson, Box.class));
        }

        //Raum
        elements.clear();
        elements = redis.hgetAll(KEY_ROOM_ELEMENTS + ":" + ViewElements.ROOM);
        for(String hash : elements.keySet()) {

            String elementJson = elements.get(hash);
            rooms.put(hash, gson.fromJson(elementJson, Room.class));
        }
    }

    /**
     * gibt ein Raum Element zurück
     *
     * @param hash Identifizierung
     * @return Element
     */
    public RoomElement getElement(String hash) {

        return roomElements.get(hash);
    }

    /**
     * fuegt ein Raum Element hinzu
     *
     * @param roomElement Element
     * @return true bei Erfolg
     */
    public boolean addRoomElement(RoomElement roomElement) {

        if(!roomElementExists(roomElement.getHash())) {

            roomElements.put(roomElement.getHash(), roomElement);
            return true;
        }
        return false;
    }

    /**
     * entfernt ein Raum Element hinzu
     *
     * @param roomElement Element
     * @return true bei Erfolg
     */
    public boolean removeRoomElement(RoomElement roomElement) {

        if(roomElementExists(roomElement.getHash())) {

            roomElements.remove(roomElement.getHash());
            return true;
        }
        return false;
    }

    /**
     * prüft ob ein Raum ELement Existiert
     *
     * @param hash Identifizierung
     * @return true bei Erfolg
     */
    public boolean roomElementExists(String hash) {

        return roomElements.containsKey(hash);
    }

    /**
     * gibt einen Raum zurück
     *
     * @param hash Identifizierung
     * @return Raum
     */
    public Room getRoom(String hash) {

        return rooms.get(hash);
    }

    /**
     * fügt einen Raum hinzu
     *
     * @param room Identifizierung
     * @return true bei Erfolg
     */
    public boolean addRoom(Room room) {

        if(!roomExsits(room.getHash())) {

            rooms.put(room.getHash(), room);
            return true;
        }
        return false;
    }

    /**
     * entfernt einen Raum hinzu
     *
     * @param room Identifizierung
     * @return true bei Erfolg
     */
    public boolean removeRoom(Room room) {

        if(!roomExsits(room.getHash())) {

            rooms.remove(room.getHash());
            return true;
        }
        return false;
    }

    /**
     * prüft ob ein Raum Existiert
     *
     * @param hash Identifizierung
     * @return true bei Erfolg
     */
    public boolean roomExsits(String hash) {

        return rooms.containsKey(hash);
    }

    /**
     * schreibt die Daten des Editors in die Datenbank
     */
    @Override
    public void saveData() {

        Pipeline pipeline = ShcApplicationServer.getInstance().getRedis().pipelined();
        Gson gson = ShcApplicationServer.getInstance().getGson();
        pipeline.del(
                KEY_ROOM_ELEMENTS + ":" + ViewElements.AVM_SOCKET,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.BATTERY,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.BMP,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.DHT,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.DS18X20,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.EDIMAX_SOCKET,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.ELECTRIC_METER,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.FRITZ_BOX,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.GAS_METER,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.HC_SR04,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.HYGROMETER,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.INPUT,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.LDR,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.OUTPUT,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.RADIO_SOCKET,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.RAIN,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.REBOOT,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.SCRIPT,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.SCT_013,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.SHUTDOWN,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.USER_AT_HOME,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_ACTUEL_POWER,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_AMOUNT,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_ENERGY,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_HUMIDITY,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_LIGHT_INTENSITY,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_MOISTURE,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_SOCKET,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_TEMPERATURE,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.WAKE_ON_LAN,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.WATER_METER,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.ACTIVITY,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.COUNTDOWN,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.BUTTON,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.BOX,
                KEY_ROOM_ELEMENTS + ":" + ViewElements.ROOM
        );

        synchronized (this) {

            for(String hash : roomElements.keySet()) {

                RoomElement roomElement = roomElements.get(hash);
                switch(roomElement.getType()) {

                    case ViewElements.AVM_SOCKET:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.AVM_SOCKET, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.BATTERY:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.BATTERY, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.BMP:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.BMP, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.DHT:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.DHT, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.DS18X20:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.DS18X20, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.EDIMAX_SOCKET:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.EDIMAX_SOCKET, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.ELECTRIC_METER:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.ELECTRIC_METER, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.FRITZ_BOX:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.FRITZ_BOX, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.GAS_METER:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.GAS_METER, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.HC_SR04:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.HC_SR04, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.HYGROMETER:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.HYGROMETER, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.INPUT:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.INPUT, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.LDR:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.LDR, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.OUTPUT:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.OUTPUT, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.RADIO_SOCKET:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.RADIO_SOCKET, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.RAIN:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.RAIN, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.REBOOT:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.REBOOT, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.SCRIPT:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.SCRIPT, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.SCT_013:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.SCT_013, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.SHUTDOWN:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.SHUTDOWN, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.USER_AT_HOME:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.USER_AT_HOME, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.VIRTUAL_ACTUEL_POWER:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_ACTUEL_POWER, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.VIRTUAL_AMOUNT:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_AMOUNT, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.VIRTUAL_ENERGY:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_ENERGY, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.VIRTUAL_HUMIDITY:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_HUMIDITY, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.VIRTUAL_LIGHT_INTENSITY:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_LIGHT_INTENSITY, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.VIRTUAL_MOISTURE:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_MOISTURE, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.VIRTUAL_SOCKET:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_SOCKET, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.VIRTUAL_TEMPERATURE:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.VIRTUAL_TEMPERATURE, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.WAKE_ON_LAN:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.WAKE_ON_LAN, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.WATER_METER:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.WATER_METER, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.ACTIVITY:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.ACTIVITY, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.COUNTDOWN:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.COUNTDOWN, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.BUTTON:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.BUTTON, hash, gson.toJson(roomElement));
                        break;
                    case ViewElements.BOX:

                        pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.BOX, hash, gson.toJson(roomElement));
                        break;
                }
            }

            for(String hash : rooms.keySet()) {

                Room room = rooms.get(hash);
                pipeline.hset(KEY_ROOM_ELEMENTS + ":" + ViewElements.ROOM, hash, gson.toJson(room));

            }
        }
        pipeline.sync();
    }
}
