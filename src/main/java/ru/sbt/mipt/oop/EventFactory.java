package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.devices.SensorEvent;
import ru.sbt.mipt.oop.devices.door.DoorActionType;
import ru.sbt.mipt.oop.devices.door.DoorEvent;
import ru.sbt.mipt.oop.devices.light.LightActionType;
import ru.sbt.mipt.oop.devices.light.LightEvent;

public class EventFactory {
    public EventFactory() {
        /* Since this factory is a dummy one, it has no context
        * and therefore this constructor is that easy.
        * It still exists though, because the real one
        * would likely need to store context.
        */
    }

    public SensorEvent getNextSensorEvent(){
        SensorEvent event;
        if (Math.random() < 0.02) return null;
        String objectId = "" + ((int) (10 * Math.random()));
        int actionType = (int) (Math.random() * 2);
        if ((int) (Math.random() * 2) == 0) {
            event = new DoorEvent(objectId, DoorActionType.values()[actionType]);
        } else {
            event = new LightEvent(objectId, LightActionType.values()[actionType]);
        }
        return event;
    }
}
