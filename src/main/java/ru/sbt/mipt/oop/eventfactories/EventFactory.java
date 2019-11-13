package ru.sbt.mipt.oop.eventfactories;

import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

public interface EventFactory {
    SensorEvent getNextSensorEvent();
}
