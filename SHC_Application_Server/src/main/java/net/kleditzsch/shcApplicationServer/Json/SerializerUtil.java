package net.kleditzsch.shcApplicationServer.Json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.kleditzsch.Util.DatabaseDateTimeUtil;
import net.kleditzsch.shcApplicationServer.Core.ShcApplicationServer;
import net.kleditzsch.shcApplicationServer.Room.RoomEditor;
import net.kleditzsch.shcApplicationServer.User.UserEditor;
import net.kleditzsch.shcCore.Command.Interface.SwitchCommand;
import net.kleditzsch.shcCore.Core.BasicElement;
import net.kleditzsch.shcCore.Room.Elements.Abstract.*;
import net.kleditzsch.shcCore.Room.Elements.Interface.RoomElement;
import net.kleditzsch.shcCore.Room.Elements.Interface.RoomElementGroup;
import net.kleditzsch.shcCore.Room.Elements.Interface.Sensor;
import net.kleditzsch.shcCore.Room.Elements.Interface.Switchable;
import net.kleditzsch.shcCore.User.UserGroup;

import java.util.Map;

/**
 * Hilfsfunktionen für die Serialisierer/Deserialisierer
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public abstract class SerializerUtil {

    /**
     * Serialisiert ein AbstractReadable Objekt
     *
     * @param jsonObject Json Objekt
     * @param abstractReadable Readable Objekt
     */
    public static void serializeAbstractReadable(JsonObject jsonObject, AbstractReadable abstractReadable) {

        jsonObject.add("state", new JsonPrimitive(abstractReadable.getState()));

        //Abstract State Element
        serializeAbstractStateElement(jsonObject, abstractReadable);

        //Abstract Room Element
        serializeAbstractRoomElement(jsonObject, abstractReadable);

        //Abstract View Element
        serializeAbstractViewElement(jsonObject, abstractReadable);

        //Basic Element
        serializeAbstractBasicElement(jsonObject, abstractReadable);
    }

    /**
     * Deserialisiert ein AbstractReadable Objekt
     *
     * @param jsonObject Json Objekt
     * @param abstractReadable Readable Objekt
     */
    public static void deserializeAbstractReadable(JsonObject jsonObject, AbstractReadable abstractReadable) {

        abstractReadable.setState(jsonObject.get("state").getAsInt());

        //Abstract State Element
        deserializeAbstractStateElement(jsonObject, abstractReadable);

        //Abstract Room Element
        deserializeAbstractRoomElement(jsonObject, abstractReadable);

        //Abstract View Element
        deserializeAbstractViewElement(jsonObject, abstractReadable);

        //Basic Element
        deserializeAbstractBasicElement(jsonObject, abstractReadable);
    }

    /**
     * Serialisiert ein AbstractSwitchable Objekt
     *
     * @param jsonObject Json Objekt
     * @param abstractSwitchable Switchable Objekt
     */
    public static void serializeAbstractSwitchable(JsonObject jsonObject, AbstractSwitchable abstractSwitchable) {

        //Abstract Switchable
        jsonObject.add("showStateEnabled", new JsonPrimitive(abstractSwitchable.isShowStateEnabled()));
        jsonObject.add("lastToggleTime", new JsonPrimitive(DatabaseDateTimeUtil.getDatabaseDateTimeStr(abstractSwitchable.getLastToggleTime())));
        jsonObject.add("operatingSeconds", new JsonPrimitive(abstractSwitchable.getOperatingSeconds()));
        jsonObject.add("energy", new JsonPrimitive(abstractSwitchable.getEnergy()));
        jsonObject.add("actualPower", new JsonPrimitive(abstractSwitchable.getActualPower()));
        jsonObject.add("dataRecording", new JsonPrimitive(abstractSwitchable.isDataRecordingEnabled()));
        jsonObject.add("lastContactTime", new JsonPrimitive(DatabaseDateTimeUtil.getDatabaseDateTimeStr(abstractSwitchable.getLastContactTime())));
        jsonObject.add("buttonTextOn", new JsonPrimitive(abstractSwitchable.getOnButtonText()));
        jsonObject.add("buttonTextOff", new JsonPrimitive(abstractSwitchable.getOffButtonText()));

        //Abstract State Element
        serializeAbstractStateElement(jsonObject, abstractSwitchable);

        //Abstract Room Element
        serializeAbstractRoomElement(jsonObject, abstractSwitchable);

        //Abstract View Element
        serializeAbstractViewElement(jsonObject, abstractSwitchable);

        //Basic Element
        serializeAbstractBasicElement(jsonObject, abstractSwitchable);
    }

    /**
     * Deserialisiert ein AbstractSwitchable Objekt
     *
     * @param jsonObject Json Objekt
     * @param abstractSwitchable Readable Objekt
     */
    public static void deserializeAbstractSwitchable(JsonObject jsonObject, AbstractSwitchable abstractSwitchable) {

        //Abstract Switchable
        abstractSwitchable.setShowStateEnabled(jsonObject.get("showStateEnabled").getAsBoolean());
        abstractSwitchable.setLastToggleTime(DatabaseDateTimeUtil.parseDateTimeFromDatabase(jsonObject.get("lastToggleTime").getAsString()));
        abstractSwitchable.setOperatingSeconds(jsonObject.get("operatingSeconds").getAsLong());
        abstractSwitchable.setEnergy(jsonObject.get("energy").getAsDouble());
        abstractSwitchable.setActualPower(jsonObject.get("actualPower").getAsDouble());
        abstractSwitchable.setDataRecordingEnabled(jsonObject.get("dataRecording").getAsBoolean());
        abstractSwitchable.setLastContactTime(DatabaseDateTimeUtil.parseDateTimeFromDatabase(jsonObject.get("lastContactTime").getAsString()));
        abstractSwitchable.setButtonText(jsonObject.get("buttonTextOn").getAsString(), jsonObject.get("buttonTextOff").getAsString());

        //Abstract State Element
        deserializeAbstractStateElement(jsonObject, abstractSwitchable);

        //Abstract Room Element
        deserializeAbstractRoomElement(jsonObject, abstractSwitchable);

        //Abstract View Element
        deserializeAbstractViewElement(jsonObject, abstractSwitchable);

        //Basic Element
        deserializeAbstractBasicElement(jsonObject, abstractSwitchable);
    }

    /**
     * Serialisiert ein AbstractSwitchableGroup Objekt
     *
     * @param jsonObject Json Objekt
     * @param abstractSwitchableGroup Switchable Gruoup Objekt
     */
    public static void serializeAbstractSwitchableGroup(JsonObject jsonObject, AbstractSwitchableGroup abstractSwitchableGroup) {

        //Switchable Group
        JsonArray array = new JsonArray();
        for(SwitchCommand command : abstractSwitchableGroup.getCommands()) {

            JsonObject jo = new JsonObject();
            jo.add("hash", new JsonPrimitive(command.getSwitchable().getHash()));
            jo.add("command", new JsonPrimitive(command.getCommand()));
            array.add(jo);
        }
        jsonObject.add("commands", array);

        //Abstract Switchable
        serializeAbstractSwitchable(jsonObject, abstractSwitchableGroup);

        //Abstract State Element
        serializeAbstractStateElement(jsonObject, abstractSwitchableGroup);

        //Abstract Room Element
        serializeAbstractRoomElement(jsonObject, abstractSwitchableGroup);

        //Abstract View Element
        serializeAbstractViewElement(jsonObject, abstractSwitchableGroup);

        //Basic Element
        serializeAbstractBasicElement(jsonObject, abstractSwitchableGroup);
    }

    /**
     * Deserialisiert ein AbstractSwitchableGroup Objekt
     *
     * @param jsonObject Json Objekt
     * @param abstractSwitchableGroup witchable Group Objekt
     */
    public static void deserializeAbstractSwitchableGroup(JsonObject jsonObject, AbstractSwitchableGroup abstractSwitchableGroup) {

        //Switchable Group
        JsonArray array = jsonObject.get("commands").getAsJsonArray();
        RoomEditor roomEditor = ShcApplicationServer.getInstance().getRoomEditor();
        for(int i = 0; i < array.size(); i++) {

            JsonObject command = array.get(i).getAsJsonObject();
            abstractSwitchableGroup.addSwitchable((Switchable) roomEditor.getElement(command.get("hash").getAsString()), command.get("command").getAsInt());
        }

        //Abstract Switchable
        deserializeAbstractSwitchable(jsonObject, abstractSwitchableGroup);

        //Abstract State Element
        deserializeAbstractStateElement(jsonObject, abstractSwitchableGroup);

        //Abstract Room Element
        deserializeAbstractRoomElement(jsonObject, abstractSwitchableGroup);

        //Abstract View Element
        deserializeAbstractViewElement(jsonObject, abstractSwitchableGroup);

        //Basic Element
        deserializeAbstractBasicElement(jsonObject, abstractSwitchableGroup);
    }

    /**
     * Serialisiert ein AbstractSensor Objekt
     *
     * @param jsonObject Json Objekt
     * @param abstractSensor Sensor Objekt
     */
    public static void serializeAbstractSensor(JsonObject jsonObject, AbstractSensor abstractSensor) {

        //Abstract Sensor
        jsonObject.add("dataRecording", new JsonPrimitive(abstractSensor.isDataRecordingEnabled()));
        jsonObject.add("lastContactTime", new JsonPrimitive(DatabaseDateTimeUtil.getDatabaseDateTimeStr(abstractSensor.getLastContactTime())));
        jsonObject.add("identifier", new JsonPrimitive(abstractSensor.getIdentifier()));

        //Abstract Room Element
        serializeAbstractRoomElement(jsonObject, abstractSensor);

        //Abstract View Element
        serializeAbstractViewElement(jsonObject, abstractSensor);

        //Basic Element
        serializeAbstractBasicElement(jsonObject, abstractSensor);
    }

    /**
     * Deserialisiert ein AbstractSensor Objekt
     *
     * @param jsonObject Json Objekt
     * @param abstractSensor Sensor Objekt
     */
    public static void deserializeAbstractSensor(JsonObject jsonObject, AbstractSensor abstractSensor) {

        //Abstract Sensor
        abstractSensor.setDataRecordingEnabled(jsonObject.get("dataRecording").getAsBoolean());
        abstractSensor.setLastContactTime(DatabaseDateTimeUtil.parseDateTimeFromDatabase(jsonObject.get("lastContactTime").getAsString()));
        abstractSensor.setIdentifier(jsonObject.get("identifier").getAsString());

        //Abstract Room Element
        deserializeAbstractRoomElement(jsonObject, abstractSensor);

        //Abstract View Element
        deserializeAbstractViewElement(jsonObject, abstractSensor);

        //Basic Element
        deserializeAbstractBasicElement(jsonObject, abstractSensor);
    }

    /**
     * Serialisiert ein AbstractVirtualSensor Objekt
     *
     * @param jsonObject Json Objekt
     * @param abstractVirtualSensor Sensor Objekt
     */
    public static void serializeAbstractVirtualSensor(JsonObject jsonObject, AbstractVirtualSensor abstractVirtualSensor) {

        //Virtual Sensor
        JsonArray array = new JsonArray();
        for(Sensor sensor : abstractVirtualSensor.getSensors()) {

            array.add(new JsonPrimitive(sensor.getHash()));
        }
        jsonObject.add("sensors", array);

        //Abstract Sensor
        serializeAbstractSensor(jsonObject, abstractVirtualSensor);
    }

    /**
     * Deserialisiert ein AbstractVirtualSensor Objekt
     *
     * @param jsonObject Json Objekt
     * @param abstractVirtualSensor Sensor Objekt
     */
    public static void deserializeAbstractVirtualSensor(JsonObject jsonObject, AbstractVirtualSensor abstractVirtualSensor) {

        //Virtual Sensor
        JsonArray array = jsonObject.get("allowedUserGroups").getAsJsonArray();
        RoomEditor roomEditor = ShcApplicationServer.getInstance().getRoomEditor();
        for(int i = 0; i < array.size(); i++) {

            String sensorHash = array.get(i).getAsString();
            Sensor sensor = (Sensor) roomEditor.getElement(sensorHash);
            abstractVirtualSensor.addSensor(sensor);
        }

        //Abstract Sensor
        deserializeAbstractSensor(jsonObject, abstractVirtualSensor);
    }

    /**
     * Serialisiert ein AbstractSwitchableGroup Objekt
     *
     * @param jsonObject Json Objekt
     * @param roomElementGroup RoomElement Gruoup Objekt
     */
    public static void serializeAbstractRoomElementGroup(JsonObject jsonObject, RoomElementGroup roomElementGroup) {

        //Switchable Group
        JsonArray array = new JsonArray();
        Map<Integer, RoomElement> roomElements = roomElementGroup.getRoomElements();
        for(Integer orderId : roomElements.keySet()) {

            JsonObject jo = new JsonObject();
            jo.add("orderId", new JsonPrimitive(orderId));
            jo.add("roomElement", new JsonPrimitive(roomElements.get(orderId).getHash()));
            array.add(jo);
        }
        jsonObject.add("roomElements", array);
    }

    /**
     * Deserialisiert ein AbstractSwitchableGroup Objekt
     *
     * @param jsonObject Json Objekt
     * @param roomElementGroup RoomElement Gruoup Objekt
     */
    public static void deserializeAbstractRoomElementGroup(JsonObject jsonObject, RoomElementGroup roomElementGroup) {

        //Switchable Group
        JsonArray array = jsonObject.get("roomElements").getAsJsonArray();
        Map<Integer, RoomElement> roomElements = roomElementGroup.getRoomElements();
        RoomEditor roomEditor = ShcApplicationServer.getInstance().getRoomEditor();
        for(int i = 0; i < array.size(); i++) {

            JsonObject element = array.get(i).getAsJsonObject();
            roomElements.put(element.get("orderId").getAsInt(), roomEditor.getElement(element.get("roomElement").getAsString()));
        }
    }

    /**
     * Serialisiert die Daten einen StateElement
     *
     * @param jsonObject Json Objekt
     * @param abstractStateElement Element
     */
    public static void serializeAbstractStateElement(JsonObject jsonObject, AbstractStateElement abstractStateElement) {

        jsonObject.add("state", new JsonPrimitive(abstractStateElement.getState()));
    }

    /**
     * Deserialisiert die Daten einen StateElement
     *
     * @param jsonObject Json Objekt
     * @param abstractStateElement Element
     */
    public static void deserializeAbstractStateElement(JsonObject jsonObject, AbstractStateElement abstractStateElement) {

        abstractStateElement.setState(jsonObject.get("state").getAsInt());
    }

    /**
     * Serialisiert die Daten einen RaumElement
     *
     * @param jsonObject Json Objekt
     * @param abstractRoomElement Element
     */
    public static void serializeAbstractRoomElement(JsonObject jsonObject, AbstractRoomElement abstractRoomElement) {

        jsonObject.add("visible", new JsonPrimitive(abstractRoomElement.isVisible()));
        jsonObject.add("icon", new JsonPrimitive(abstractRoomElement.getIcon()));
    }

    /**
     * Deserialisiert die Daten einen RaumElement
     *
     * @param jsonObject Json Objekt
     * @param abstractRoomElement Element
     */
    public static void deserializeAbstractRoomElement(JsonObject jsonObject, AbstractRoomElement abstractRoomElement) {

        abstractRoomElement.setVisible(jsonObject.get("visible").getAsBoolean());
        abstractRoomElement.setIcon(jsonObject.get("icon").getAsString());
    }

    /**
     * Serialisiert die Daten einen ViewElement
     *
     * @param jsonObject Json Objekt
     * @param abstractViewElement Element
     */
    public static void serializeAbstractViewElement(JsonObject jsonObject, AbstractViewElement abstractViewElement) {

        jsonObject.add("enabled", new JsonPrimitive(abstractViewElement.isEnabled()));

        JsonArray array = new JsonArray();
        for(UserGroup userGroup : abstractViewElement.listAllowedUserGroups()) {

            array.add(new JsonPrimitive(userGroup.getHash()));
        }
        jsonObject.add("allowedUserGroups", array);
    }

    /**
     * Deserialisiert die Daten einen ViewElement
     *
     * @param jsonObject Json Objekt
     * @param abstractViewElement Element
     */
    public static void deserializeAbstractViewElement(JsonObject jsonObject, AbstractViewElement abstractViewElement) {

        abstractViewElement.setEnabled(jsonObject.get("enabled").getAsBoolean());

        JsonArray array = jsonObject.get("allowedUserGroups").getAsJsonArray();
        UserEditor userEditor = ShcApplicationServer.getInstance().getUserEditor();
        for(int i = 0; i < array.size(); i++) {

            String userGroupHash = array.get(i).getAsString();
            UserGroup userGroup = userEditor.getUserGroup(userGroupHash);
            abstractViewElement.addAllowedUserGroup(userGroup);
        }
    }

    /**
     * Serialisiert die Daten einen BasicElement
     *
     * @param jsonObject Json Objekt
     * @param basicElement Element
     */
    public static void serializeAbstractBasicElement(JsonObject jsonObject, BasicElement basicElement) {

        jsonObject.add("hash", new JsonPrimitive(basicElement.getHash()));
        jsonObject.add("name", new JsonPrimitive(basicElement.getName()));
    }

    /**
     * Deserialisiert die Daten einen BasicElement
     *
     * @param jsonObject Json Objekt
     * @param basicElement Element
     */
    public static void deserializeAbstractBasicElement(JsonObject jsonObject, BasicElement basicElement) {

        basicElement.setHash(jsonObject.get("hash").getAsString());
        basicElement.setName(jsonObject.get("name").getAsString());
    }
}
