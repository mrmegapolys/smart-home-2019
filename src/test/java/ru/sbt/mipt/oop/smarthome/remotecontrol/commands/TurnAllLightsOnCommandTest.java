package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TurnAllLightsOnCommandTest {
    private SmartHome smartHome;

    @BeforeEach
    void setUp() throws IOException {
        smartHome = SmartHomeProvider.readFile("output.js", "json");
    }

    @Test
    void execute() {
        (new TurnAllLightsOnCommand(smartHome)).execute();
        smartHome.execute(actionable -> {
            if (!(actionable instanceof Light)) return;
            assertTrue(((Light) actionable).isOn());
        });
    }

}