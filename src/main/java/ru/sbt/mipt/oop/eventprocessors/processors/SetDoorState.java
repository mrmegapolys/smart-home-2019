package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.Logger;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.door.Door;
import ru.sbt.mipt.oop.devices.door.DoorActionType;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class SetDoorState implements EventProcessor {
    private final SmartHome smartHome;

    public SetDoorState(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void process(SensorEvent event) {
        if (event.getEventType() != SensorEventType.DOOR_EVENT) return;

        for (Room room : smartHome.getRooms()) {
            for (Door door: room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {

                    if (event.getActionType() == DoorActionType.OPEN) {
                        door.setOpen(true);
                        Logger.info("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                        return;
                    }

                    if (event.getActionType() == DoorActionType.CLOSE) {
                        door.setOpen(false);
                        Logger.info("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    }

                }
            }
        }
    }
}


