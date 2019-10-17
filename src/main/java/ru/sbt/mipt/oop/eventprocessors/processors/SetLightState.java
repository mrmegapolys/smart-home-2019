package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.Logger;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;
import ru.sbt.mipt.oop.smarthome.devices.light.LightActionType;

public class SetLightState implements EventProcessor {
    private final SmartHome smartHome;

    public SetLightState(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void process(SensorEvent event) {
        if (event.getEventType() != SensorEventType.LIGHT_EVENT) return;

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
