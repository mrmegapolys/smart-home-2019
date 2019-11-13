package ru.sbt.mipt.oop.eventprocessors.decorators;

import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.alarm.*;

public class ActivatedAlarmDecorator extends BaseProcessorDecorator {
    private final Alarm alarm;

    public ActivatedAlarmDecorator(EventProcessor delegate, Alarm alarm) {
        super(delegate);
        this.alarm = alarm;
    }

    @Override
    public void process(SensorEvent event) {
        if (event instanceof AlarmEvent) {
            super.process(event);
            return;
        }

        AlarmState state = alarm.getState();
        if (state instanceof Alert) return;
        if (state instanceof Activated) {
            alarm.triggerAlert();
            return;
        }

        super.process(event);
    }

}
