package ru.sbt.mipt.oop.smarthome.devices.light;

import ru.sbt.mipt.oop.smarthome.devices.ActionType;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

public class LightEvent extends SensorEvent {
    public LightEvent(ActionType actionType, String objectId) {
        super(actionType, objectId);
    }
}
