package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventfactories.EventFactory;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

import java.util.List;


public class Dispatcher {
    private final EventFactory eventFactory;
    private final List<EventProcessor> processors;

    public Dispatcher(EventFactory eventFactory, List<EventProcessor> processors) {
        this.eventFactory = eventFactory;
        this.processors = processors;
    }

    public void run() {
        SensorEvent event;
        while ((event = eventFactory.getNextSensorEvent()) != null) {
            Logger.newEvent(event);
            for (EventProcessor processor : processors) {
                processor.process(event);
            }
        }
    }
}
