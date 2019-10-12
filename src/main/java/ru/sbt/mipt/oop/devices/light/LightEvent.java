package ru.sbt.mipt.oop.devices.light;

import ru.sbt.mipt.oop.devices.ActionType;
import ru.sbt.mipt.oop.devices.SensorEvent;

public class LightEvent extends SensorEvent {
    public LightEvent(String objectId, ActionType actionType) {
        super(objectId, actionType);
    }

    @Override
    public String toString() {
        return "LightEvent{" +
                "objectId='" + objectId + '\'' +
                ", actionType=" + actionType +
                '}';
    }
}
