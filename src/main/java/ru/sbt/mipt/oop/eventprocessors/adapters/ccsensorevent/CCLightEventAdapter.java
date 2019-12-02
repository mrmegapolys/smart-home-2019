package ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.light.LightActionType;
import ru.sbt.mipt.oop.smarthome.devices.light.LightEvent;

public class CCLightEventAdapter implements CCEventAdapter {
    @Override
    public SensorEvent adaptEvent(CCSensorEvent event) {
        if (event.getEventType().equals("LightIsOn")) {
            return new LightEvent(LightActionType.ON, event.getObjectId());
        }
        if (event.getEventType().equals("LightIsOff")) {
            return new LightEvent(LightActionType.OFF, event.getObjectId());
        }
        return null;
    }
}
