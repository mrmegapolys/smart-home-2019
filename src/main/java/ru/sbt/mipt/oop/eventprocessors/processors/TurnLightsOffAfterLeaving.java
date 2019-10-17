package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.door.Door;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorActionType;
import ru.sbt.mipt.oop.smarthome.scenarios.LightScenarios;

public class TurnLightsOffAfterLeaving implements EventProcessor {
    private final SmartHome smartHome;

    public TurnLightsOffAfterLeaving(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void process(SensorEvent event) {
        if (event.getEventType() != SensorEventType.DOOR_EVENT) return;
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
