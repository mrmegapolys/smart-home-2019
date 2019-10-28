package ru.sbt.mipt.oop.eventprocessors.decorators;

import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

public class BaseProcessorDecorator implements EventProcessor {
    private final EventProcessor delegate;

    BaseProcessorDecorator(EventProcessor delegate) {
        this.delegate = delegate;
    }

    @Override
    public void process(SensorEvent event) {
        delegate.process(event);
    }

    @Override
    public boolean isSupportedEvent(SensorEvent event) {
        return delegate.isSupportedEvent(event);
    }

}
