package ru.sbt.mipt.oop.eventprocessors.processors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeSerializer;
import ru.sbt.mipt.oop.utils.Reader;

class SetLightStateTest {

    @BeforeEach
    void setUp() {
        final String FILEPATH = "smart-home-1.js";
        String encoded = Reader.readFile(FILEPATH);
        SmartHome smartHome = SmartHomeSerializer.fromJson(encoded);
    }

    @Test
    void process() {
    }
}