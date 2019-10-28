package ru.sbt.mipt.oop.eventprocessors.decorators;

import ru.sbt.mipt.oop.Notifier;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Activated;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmState;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alert;

public class NotifierDecorator extends BaseProcessorDecorator {
    private final Alarm alarm;
    private final Notifier notifier;

    public NotifierDecorator(EventProcessor delegate, Alarm alarm, Notifier notifier) {
        super(delegate);
        this.alarm = alarm;
        this.notifier = notifier;
    }

    @Override
    public void process(SensorEvent event) {
        if (!isSupportedEvent(event)) return;
        AlarmState state = alarm.getState();

        if (state instanceof Activated || state instanceof Alert) notifier.sendNotification();
        super.process(event);
    }
}
