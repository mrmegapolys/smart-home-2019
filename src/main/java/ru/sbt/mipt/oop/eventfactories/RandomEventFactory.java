package ru.sbt.mipt.oop.eventfactories;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorActionType;
import ru.sbt.mipt.oop.smarthome.devices.light.LightActionType;

public class RandomEventFactory implements EventFactory {

    public SensorEvent getNextSensorEvent(){
        SensorEvent event;
        if (Math.random() < 0.02) return null;
        String objectId = "" + ((int) (10 * Math.random()));
        int actionType = (int) (Math.random() * 2);
        if ((int) (Math.random() * 2) == 0) {
            event = new SensorEvent(SensorEventType.DOOR_EVENT, DoorActionType.values()[actionType], objectId);
        } else {
            event = new SensorEvent(SensorEventType.LIGHT_EVENT, LightActionType.values()[actionType], objectId);
        }
        return event;
    }
}
