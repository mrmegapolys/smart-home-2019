package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.Actionable;
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

        smartHome.execute( (Actionable actionable) -> {
            if (!(actionable instanceof Room)) return;
            Room room = (Room) actionable;
            if (!(room.getName().equals("hall"))) return;

            room.execute( (Actionable hallActionable) -> {
                if (!(hallActionable instanceof Door)) return;
                if (!((Door) hallActionable).getId().equals(event.getObjectId())) return;
                LightScenarios.turnAllLightsOff(smartHome);
            });

        });
    }

}
