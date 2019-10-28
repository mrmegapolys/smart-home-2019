package ru.sbt.mipt.oop.eventprocessors.processors;

import ru.sbt.mipt.oop.Logger;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;
import ru.sbt.mipt.oop.smarthome.devices.light.LightActionType;
import ru.sbt.mipt.oop.smarthome.devices.light.LightEvent;

public class SetLightState implements EventProcessor {
    private final SmartHome smartHome;

    public SetLightState(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void process(SensorEvent event) {
        if (!isSupportedEvent(event)) return;

        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            Light light = (Light) actionable;
            if (!(light.getId().equals(event.getObjectId()))) return;

            if (event.getActionType() == LightActionType.ON) {
                setLightOn(light);
            }
            if (event.getActionType() == LightActionType.OFF) {
                setLightOff(light);
            }

        });
    }

    @Override
    public boolean isSupportedEvent(SensorEvent event) {
        return event instanceof LightEvent;
    }

    private void setLightOff(Light light) {
        light.setOn(false);
        Logger.info("Light " + light.getId() + " was turned off.");
    }

    private void setLightOn(Light light) {
        light.setOn(true);
        Logger.info("Light " + light.getId() + " was turned on.");
    }
}
