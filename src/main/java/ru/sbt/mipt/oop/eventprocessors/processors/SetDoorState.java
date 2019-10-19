package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.Logger;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.door.Door;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorActionType;

public class SetDoorState implements EventProcessor {
    private final SmartHome smartHome;

    public SetDoorState(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void process(SensorEvent event) {
        if (event.getEventType() != SensorEventType.DOOR_EVENT) return;

        smartHome.execute( (Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (!(door.getId().equals(event.getObjectId()))) return;

            if (event.getActionType() == DoorActionType.OPEN) {
                setDoorOpened(door);
            }
            if (event.getActionType() == DoorActionType.CLOSE) {
                setDoorClosed(door);
            }

        });
    }

    private void setDoorClosed(Door door) {
        door.setOpen(false);
        Logger.info("Door " + door.getId() + " was closed.");
    }

    private void setDoorOpened(Door door) {
        door.setOpen(true);
        Logger.info("Door " + door.getId() + " was opened.");
    }
}


