package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.eventprocessors.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.processors.LightEventProcessor;

import java.util.ArrayList;
import java.util.List;

public class ProcessorFactory {

    public static List<EventProcessor> getProcessors() {
        List<EventProcessor> processors = new ArrayList<>();

        processors.add(new DoorEventProcessor());
        processors.add(new LightEventProcessor());

        return processors;
    }
}
