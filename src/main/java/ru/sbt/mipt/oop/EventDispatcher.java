package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventfactories.SensorEventFactory;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

import java.util.List;
import java.util.Map;

import static ru.sbt.mipt.oop.Logger.logEvent;


public class EventDispatcher {
    private final SensorEventFactory sensorEventFactory;
    private final Map<Class<? extends SensorEvent>, List<EventProcessor>> processors;

    public EventDispatcher(SensorEventFactory sensorEventFactory,
                           Map<Class<? extends SensorEvent>, List<EventProcessor>> processors) {
        this.sensorEventFactory = sensorEventFactory;
        this.processors = null;
    }

    public void run() {
        while (true) {
            SensorEvent event = nextEvent();
            if (event == null) return;

            logEvent(event);
            process(event);
        }
    }

    private SensorEvent nextEvent() {
        return sensorEventFactory.nextEvent();
    }

    private void process(SensorEvent event) {
        List<EventProcessor> eventProcessors = processors.get(event.getClass());
        eventProcessors.forEach(p-> p.process(event));
    }
}
