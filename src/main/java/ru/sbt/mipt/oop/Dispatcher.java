package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.devices.SensorEvent;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.EventProcessors;
import ru.sbt.mipt.oop.utils.Logger;

import java.util.ArrayList;


public class Dispatcher {
    private final EventFactory eventFactory;
    private final SmartHome smartHome;
    private final ArrayList<EventProcessor> processors;

    public Dispatcher(SmartHome smartHome, EventFactory eventFactory) {
        this.smartHome = smartHome;
        this.eventFactory = eventFactory;
        this.processors = createProcessors();
    }

    private ArrayList<EventProcessor> createProcessors() {
        ArrayList<EventProcessor> processors = new ArrayList<>();
        for (EventProcessors eventProcessors : EventProcessors.values()) {
            processors.add(eventProcessors.createProcessor());
        }
        return processors;
    }

    public void run() {
        SensorEvent event;
        while ((event = eventFactory.getNextSensorEvent()) != null) {
            Logger.newEvent(event);
            for (EventProcessor processor : processors) {
                processor.process(event, smartHome);
            }
        }
    }
}
