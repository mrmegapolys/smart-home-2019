package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.SensorEvent;
import ru.sbt.mipt.oop.devices.door.Door;
import ru.sbt.mipt.oop.devices.door.DoorActionType;
import ru.sbt.mipt.oop.devices.door.DoorEvent;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.scenarios.LightScenarios;

public class TurnLightsOffAfterLeaving implements EventProcessor {

    @Override
    public void process(SensorEvent event, SmartHome smartHome) {
        if (!(event instanceof DoorEvent)) return;
        if (event.getActionType() != DoorActionType.CLOSE) return;

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId()) && room.getName().equals("hall")) {
                    LightScenarios.turnAllLightsOff(smartHome);
                }

            }
        }
    }

}
