package ru.sbt.mipt.oop.eventprocessors.decorators;

import ru.sbt.mipt.oop.Notifier;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Activated;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmState;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alert;

public class ActivatedAlarmDecorator extends BaseProcessorDecorator {
    private final SmartHome smartHome;
    private final Notifier notifier;

    public ActivatedAlarmDecorator(EventProcessor delegate, SmartHome smartHome, Notifier notifier) {
        super(delegate);
        this.smartHome = smartHome;
        this.notifier = notifier;
    }

    @Override
    public void process(SensorEvent event) {
        if (!isSupportedEvent(event)) return;
        Alarm alarm = smartHome.getAlarm();
        AlarmState state = alarm.getState();

        if (state instanceof Activated || state instanceof Alert) {
            notifier.sendNotification();
            if (state instanceof Activated) alarm.triggerAlert();
            return;
        }
        super.process(event);
    }

}
