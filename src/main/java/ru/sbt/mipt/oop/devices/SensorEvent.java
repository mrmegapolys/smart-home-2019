package ru.sbt.mipt.oop.devices;

public abstract class SensorEvent {
    protected final String objectId;
    protected final ActionType actionType;

    public SensorEvent(String objectId, ActionType actionType) {
        this.objectId = objectId;
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getObjectId() {
        return objectId;
    }

    public abstract String toString();
}
