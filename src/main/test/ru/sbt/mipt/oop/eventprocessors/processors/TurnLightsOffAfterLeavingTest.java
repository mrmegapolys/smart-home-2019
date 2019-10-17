package ru.sbt.mipt.oop.eventprocessors.processors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;

import java.io.IOException;

class TurnLightsOffAfterLeavingTest {
    private static SmartHome smartHome;
    private EventProcessor processor;

    @BeforeAll
    static void init() throws IOException {
        String FILEPATH = "smart-home-1.js";
        smartHome = SmartHomeProvider.readFile(FILEPATH, "json");
    }

    @BeforeEach
    void setUp() {
        processor = new TurnLightsOffAfterLeaving(smartHome);
    }

    @Test
    void process1() {
    }

    @Test
    void process2() {
    }
}