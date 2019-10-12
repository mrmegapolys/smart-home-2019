package ru.sbt.mipt.oop.devices.door;

import ru.sbt.mipt.oop.devices.ActionType;
import ru.sbt.mipt.oop.devices.SensorEvent;

public class DoorEvent extends SensorEvent {
    public DoorEvent(String objectId, ActionType actionType) {
        super(objectId, actionType);
    }

    @Override
    public String toString() {
        return "DoorEvent{" +
                "objectId='" + objectId + '\'' +
                ", actionType=" + actionType +
                '}';
    }
}
