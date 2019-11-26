package ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

public interface CCEventAdapter {
    SensorEvent adaptEvent(CCSensorEvent event);
}
