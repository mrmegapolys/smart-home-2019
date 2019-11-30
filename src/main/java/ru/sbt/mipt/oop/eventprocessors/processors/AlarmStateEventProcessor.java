package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmActionType;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmEvent;

import static ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmActionType.ACTIVATE;
import static ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmActionType.DEACTIVATE;

public class AlarmStateEventProcessor implements EventProcessor<AlarmEvent> {
    private final Alarm alarm;

    public AlarmStateEventProcessor(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void process(AlarmEvent event) {
        if (!event.getObjectId().equals(alarm.getId())) return;

        String code = ((AlarmEvent) event).getCode();
        if (event.getActionType() == ACTIVATE) {
            activateAlarm(code);
        }

        if (event.getActionType() == DEACTIVATE) {
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
