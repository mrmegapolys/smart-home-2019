package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.devices.door.Door;
import ru.sbt.mipt.oop.devices.light.Light;
import ru.sbt.mipt.oop.utils.Writer;

import java.util.Arrays;

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
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
        String encoded = SmartHomeSerializer.toJson(smartHome);
        boolean writeSuccess = Writer.writeStringToFile(encoded, FILENAME);
        if (!writeSuccess) {
            System.out.println("Failed to write smart home config.");
        }

    }

}
