package ru.sbt.mipt.oop.eventprocessors.processors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;

import java.io.IOException;

class SetLightStateTest {
    private static SmartHome smartHome;
    private EventProcessor processor;

    @BeforeAll
    static void init() throws IOException {
        String FILEPATH = "smart-home-1.js";
        smartHome = SmartHomeProvider.readFile(FILEPATH, "json");
    }

    @BeforeEach
    void setUp() {
        processor = new SetLightState(smartHome);
    }

    @Test
    void process1() {
    }

    @Test
    void process2() {
    }
}