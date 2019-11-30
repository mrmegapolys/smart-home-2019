package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.Notifier;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.alarm.*;

public class NotificationEventProcessor implements EventProcessor {
    private final Alarm alarm;
    private final Notifier notifier;

    public NotificationEventProcessor(Alarm alarm, Notifier notifier) {
        this.alarm = alarm;
        this.notifier = notifier;
    }

    @Override
    public void process(SensorEvent event) {
        if (event instanceof AlarmEvent) return;
        AlarmState state = alarm.getState();

        if (state instanceof Activated || state instanceof Alert) {
            notifier.sendNotification();
        }
    }

}
