package ru.sbt.mipt.oop.eventfactories;

import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorActionType;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorEvent;
import ru.sbt.mipt.oop.smarthome.devices.light.LightActionType;
import ru.sbt.mipt.oop.smarthome.devices.light.LightEvent;

public class RandomEventFactory implements EventFactory {

    public SensorEvent getNextSensorEvent(){
        SensorEvent event;
        if (Math.random() < 0.02) return null;
        String objectId = "" + ((int) (10 * Math.random()));
        int actionType = (int) (Math.random() * 2);
        if ((int) (Math.random() * 2) == 0) {
            event = new DoorEvent(DoorActionType.values()[actionType], objectId);
        } else {
            event = new LightEvent(LightActionType.values()[actionType], objectId);
        }
        return event;
    }
}
