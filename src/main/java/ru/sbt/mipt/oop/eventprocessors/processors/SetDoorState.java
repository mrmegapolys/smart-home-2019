package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.SensorEvent;
import ru.sbt.mipt.oop.devices.door.Door;
import ru.sbt.mipt.oop.devices.door.DoorActionType;
import ru.sbt.mipt.oop.devices.door.DoorEvent;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.utils.Logger;

public class SetDoorState implements EventProcessor {
    @Override
    public void process(SensorEvent event, SmartHome smartHome) {
        if (!(event instanceof DoorEvent)) return;

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


