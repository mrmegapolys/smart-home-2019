package ru.sbt.mipt.oop.smarthome.devices.alarm;

import ru.sbt.mipt.oop.smarthome.devices.ActionType;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

public class AlarmEvent extends SensorEvent {
    private final String code;

    public AlarmEvent(ActionType actionType, String objectId, String code) {
        super(actionType, objectId);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
