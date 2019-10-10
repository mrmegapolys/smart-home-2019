package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.initialization.Reader;
import ru.sbt.mipt.oop.initialization.SmartHomeCreator;

public class Application {

    public static void main(String... args) {
        String encoded = Reader.readFile("smart-home-1.js");
        if (encoded == null) {
            System.out.println("Smart home config not found.");
            return;
        }

        SmartHome smartHome = SmartHomeCreator.fromJson(encoded);
        EventFactory eventFactory = new EventFactory();
        Dispatcher dispatcher = new Dispatcher(smartHome, eventFactory);
        dispatcher.run();
    }

}
