package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.devices.SensorEvent;
import ru.sbt.mipt.oop.eventfactories.EventFactory;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.ProcessorFactory;
import ru.sbt.mipt.oop.utils.Logger;

import java.util.List;


public class Dispatcher {
    private final EventFactory eventFactory;
    private final SmartHome smartHome;
    private final List<EventProcessor> processors;

    public Dispatcher(SmartHome smartHome, EventFactory eventFactory) {
        this.smartHome = smartHome;
        this.eventFactory = eventFactory;
        this.processors = ProcessorFactory.getProcessors();
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
