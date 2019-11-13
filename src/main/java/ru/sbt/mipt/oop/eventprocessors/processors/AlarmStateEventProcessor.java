package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmActionType;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmEvent;

public class AlarmStateEventProcessor implements EventProcessor {
    private final Alarm alarm;

    public AlarmStateEventProcessor(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void process(SensorEvent event) {
        if (!(event instanceof AlarmEvent)) return;
        if (!event.getObjectId().equals(alarm.getId())) return;
        String code = ((AlarmEvent) event).getCode();

        if (event.getActionType() == AlarmActionType.ACTIVATE) {
            activateAlarm(code);
        }

        if (event.getActionType() == AlarmActionType.DEACTIVATE) {
            deactivateAlarm(code);
        }

    }

    private void activateAlarm(String code) {
        alarm.activate(code);
        System.out.println("Alarm activated.");
    }

    private void deactivateAlarm(String code) {
        alarm.deactivate(code);
        System.out.println("Alarm deactivated.");
    }
}
