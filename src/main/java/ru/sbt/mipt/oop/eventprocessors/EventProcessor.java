package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventProcessor {
    void process(SensorEvent event);
}
