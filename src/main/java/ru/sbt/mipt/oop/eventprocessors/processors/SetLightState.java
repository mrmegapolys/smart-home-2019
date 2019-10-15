package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.SensorEvent;
import ru.sbt.mipt.oop.devices.light.Light;
import ru.sbt.mipt.oop.devices.light.LightActionType;
import ru.sbt.mipt.oop.devices.light.LightEvent;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.utils.Logger;

public class SetLightState implements EventProcessor {
    @Override
    public void process(SensorEvent event, SmartHome smartHome) {
        if (!(event instanceof LightEvent)) return;

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getActionType() == LightActionType.ON) {
                        light.setOn(true);
                        Logger.info("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    }

                    if (event.getActionType() == LightActionType.OFF) {
                        light.setOn(false);
                        Logger.info("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }
}
