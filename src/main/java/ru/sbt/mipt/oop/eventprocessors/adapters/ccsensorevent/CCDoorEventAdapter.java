package ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorActionType;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorEvent;

public class CCDoorEventAdapter implements CCEventAdapter {
    @Override
    public SensorEvent adaptEvent(CCSensorEvent event) {
        if (event.getEventType().equals("DoorIsOpen")) {
            return new DoorEvent(DoorActionType.OPEN, event.getObjectId());
        }
        if (event.getEventType().equals("DoorIsClosed")) {
            return new DoorEvent(DoorActionType.CLOSE, event.getObjectId());
        }
        return null;
    }
}
