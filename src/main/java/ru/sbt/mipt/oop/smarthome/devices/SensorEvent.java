package ru.sbt.mipt.oop.smarthome.devices;

public class SensorEvent {
    private final ActionType actionType;
    private final String objectId;

    public SensorEvent(ActionType actionType, String objectId) {
        this.actionType = actionType;
        this.objectId = objectId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "actionType=" + actionType +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
