package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.devices.ActionType;

public class SensorEvent {
    private final SensorEventType eventType;
    private final ActionType actionType;
    private final String objectId;

    public SensorEvent(SensorEventType eventType, ActionType actionType, String objectId) {
        this.eventType = eventType;
        this.actionType = actionType;
        this.objectId = objectId;
    }

    public SensorEventType getEventType() {
        return eventType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "eventType=" + eventType +
                ", actionType=" + actionType +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
