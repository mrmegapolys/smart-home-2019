package ru.sbt.mipt.oop.smarthome.devices.door;

import ru.sbt.mipt.oop.smarthome.devices.ActionType;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

public class DoorEvent extends SensorEvent {
    public DoorEvent(ActionType actionType, String objectId) {
        super(actionType, objectId);
    }
}
