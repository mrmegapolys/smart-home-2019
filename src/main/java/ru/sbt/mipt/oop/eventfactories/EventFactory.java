package ru.sbt.mipt.oop.eventfactories;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventFactory {
    SensorEvent getNextSensorEvent();
}
