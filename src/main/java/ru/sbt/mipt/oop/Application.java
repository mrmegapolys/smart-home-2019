package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventfactories.EventFactory;
import ru.sbt.mipt.oop.eventfactories.RandomEventFactory;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.processors.SetDoorState;
import ru.sbt.mipt.oop.eventprocessors.processors.SetLightState;
import ru.sbt.mipt.oop.eventprocessors.processors.TurnLightsOffAfterLeaving;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        List<EventProcessor> processors = getProcessors(smartHome);
        EventFactory eventFactory = new RandomEventFactory();

        Dispatcher dispatcher = new Dispatcher(eventFactory, processors);
        dispatcher.run();

    }

    private static List<EventProcessor> getProcessors(SmartHome smartHome) {
        List<EventProcessor> processors = new ArrayList<>();

        processors.add(new SetDoorState(smartHome));
        processors.add(new TurnLightsOffAfterLeaving(smartHome));
        processors.add(new SetLightState(smartHome));

        return processors;
    }

}
