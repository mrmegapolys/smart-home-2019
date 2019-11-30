package ru.sbt.mipt.oop.eventprocessors.decorators;

import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.alarm.*;

public class ActivatedAlarmDecorator2 implements EventProcessor {
    private final EventProcessor delegate;
    private final Alarm alarm;

    public ActivatedAlarmDecorator2(EventProcessor delegate, Alarm alarm) {
        this.delegate = delegate;
        this.alarm = alarm;
    }

    @Override
    public void process(SensorEvent event) {
        if (event instanceof AlarmEvent) {
            delegate.process(event);
            return;
        }

        AlarmState state = alarm.getState();
        if (state instanceof Alert) return;
        if (state instanceof Activated) {
            alarm.triggerAlert();
            return;
        }

        delegate.process(event);
    }

}
