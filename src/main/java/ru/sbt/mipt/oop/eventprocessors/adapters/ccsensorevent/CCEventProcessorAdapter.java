package ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

import java.util.Collection;

public class CCEventProcessorAdapter implements EventHandler {
    private final EventProcessor adaptee;
    private final Collection<CCEventAdapter> adapters;

    public CCEventProcessorAdapter(EventProcessor adaptee, Collection<CCEventAdapter> adapters) {
        this.adaptee = adaptee;
        this.adapters = adapters;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent adaptedEvent;
        for (CCEventAdapter adapter : adapters) {
            if ((adaptedEvent = adapter.adaptEvent(event)) != null) {
                adaptee.process(adaptedEvent);
                return;
            }
        }
    }

}
