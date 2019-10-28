package ru.sbt.mipt.oop.smarthome.devices.alarm;

import ru.sbt.mipt.oop.smarthome.devices.ActionType;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

public class AlarmEvent extends SensorEvent {
    private final int code;

    public AlarmEvent(ActionType actionType, String objectId, int code) {
        super(actionType, objectId);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
