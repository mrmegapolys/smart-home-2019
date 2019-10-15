package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventfactories.EventFactory;
import ru.sbt.mipt.oop.eventfactories.RandomEventFactory;
import ru.sbt.mipt.oop.utils.Reader;

public class Application {

    public static void main(String... args) {
        final String FILEPATH = "smart-home-1.js";

        String encoded = Reader.readFile(FILEPATH);
        if (encoded == null) {
            System.out.println("Failed to read smart home config.");
            return;
        }

        SmartHome smartHome = HomeBuilder.fromJson(encoded);
        EventFactory eventFactory = new RandomEventFactory();
        Dispatcher dispatcher = new Dispatcher(smartHome, eventFactory);
        dispatcher.run();

    }

}
