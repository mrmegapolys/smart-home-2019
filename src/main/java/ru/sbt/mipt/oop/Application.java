package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventfactories.EventFactory;
import ru.sbt.mipt.oop.eventfactories.RandomEventFactory;

import java.io.IOException;

public class Application {

    public static void main(String... args) {
        String FILEPATH = "smart-home-1.js";
        SmartHome smartHome;

        try {
            smartHome = SmartHomeProvider.readFile(FILEPATH, "json");
        } catch (IOException e) {
            System.out.println("Failed to read smart home config.");
            return;
        }

        EventFactory eventFactory = new RandomEventFactory();
        Dispatcher dispatcher = new Dispatcher(eventFactory, smartHome);
        dispatcher.run();

    }

}
