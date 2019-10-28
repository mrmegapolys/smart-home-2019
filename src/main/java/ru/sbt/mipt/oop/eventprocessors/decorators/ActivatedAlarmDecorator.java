package ru.sbt.mipt.oop.eventprocessors.decorators;

import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Activated;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmState;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alert;

public class ActivatedAlarmDecorator extends BaseProcessorDecorator {
    private final Alarm alarm;

    public ActivatedAlarmDecorator(EventProcessor delegate, Alarm alarm) {
        super(delegate);
        this.alarm = alarm;
    }

    @Override
    public void process(SensorEvent event) {
        if (!isSupportedEvent(event)) return;
        AlarmState state = alarm.getState();

        if (state instanceof Alert) return;
        if (state instanceof Activated) {
            alarm.triggerAlert();
            return;
        }
        super.process(event);
    }

}
