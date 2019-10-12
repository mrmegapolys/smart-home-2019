package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.SensorEvent;

public interface EventProcessor {
    void process(SensorEvent event, SmartHome smartHome);
}
