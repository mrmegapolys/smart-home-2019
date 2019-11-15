package ru.sbt.mipt.oop.eventprocessors;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.smarthome.devices.ActionType;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorActionType;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorEvent;
import ru.sbt.mipt.oop.smarthome.devices.light.LightActionType;
import ru.sbt.mipt.oop.smarthome.devices.light.LightEvent;

public class CCEventProcessorAdapter implements EventHandler {
    private final EventProcessor adaptee;

    public CCEventProcessorAdapter(EventProcessor adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent adaptedEvent = adaptEvent(event);
        if (adaptedEvent != null) {
            adaptee.process(adaptedEvent);
        }
    }

    private static SensorEvent adaptEvent(CCSensorEvent event) {
        SensorEvent adaptedEvent;
        ActionType actionType;
        String objectId = event.getObjectId();

        switch (event.getEventType()) {
            case "LightIsOn":
                actionType = LightActionType.ON;
                adaptedEvent = new LightEvent(actionType, objectId);
                break;
            case "LightIsOff":
                actionType = LightActionType.OFF;
                adaptedEvent = new LightEvent(actionType, objectId);
                break;

            case "DoorIsOpen":
                actionType = DoorActionType.OPEN;
                adaptedEvent = new DoorEvent(actionType, objectId);
                break;
            case "DoorIsClosed":
                actionType = DoorActionType.CLOSE;
                adaptedEvent = new DoorEvent(actionType, objectId);
                break;

            default:
                adaptedEvent = null;
        }
        return adaptedEvent;
    }
}
