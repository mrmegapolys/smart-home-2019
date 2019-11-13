package ru.sbt.mipt.oop.smarthome.utils;

import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.devices.door.Door;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HomeBuilder {

    public static void main(String[] args) {
        final String FILENAME = "output.js";

        Room kitchen = new Room(
                Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door("1", false)),
                "kitchen"
        );
        Room bathroom = new Room(
                Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door("2", false)),
                "bathroom"
        );
        Room bedroom = new Room(
                Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door("3", true)),
                "bedroom"
        );
        Room hall = new Room(
                Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door("4", false)),
                "hall"
        );
        List<Room> rooms = Arrays.asList(kitchen, bathroom, bedroom, hall);

        String defaultCode = "default";
        String alarmId = "0";
        Alarm alarm = new Alarm(alarmId, defaultCode);

        SmartHome smartHome = new SmartHome(alarm, rooms);
        String encoded = SmartHomeSerializer.toJson(smartHome);

        try {
            Writer.writeStringToFile(encoded, FILENAME);
        } catch (IOException e) {
            System.out.println("Failed to write smart home config.");
        }

    }

}
