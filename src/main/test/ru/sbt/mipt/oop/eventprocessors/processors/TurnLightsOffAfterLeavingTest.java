package ru.sbt.mipt.oop.eventprocessors.processors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;

import java.io.IOException;

class TurnLightsOffAfterLeavingTest {
    private SmartHome smartHome;
    private EventProcessor processor;

    @BeforeEach
    void setUp() throws IOException {
        smartHome = SmartHomeProvider.readFile("smart-home-1.js", "json");
        processor = new TurnLightsOffAfterLeaving(smartHome);
    }

    @Test
    void process1() {
    }

    @Test
    void process2() {
    }
}