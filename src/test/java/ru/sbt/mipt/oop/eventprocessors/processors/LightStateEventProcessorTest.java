package ru.sbt.mipt.oop.eventprocessors.processors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;
import ru.sbt.mipt.oop.smarthome.devices.light.LightActionType;
import ru.sbt.mipt.oop.smarthome.devices.light.LightEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LightStateEventProcessorTest {
    private SmartHome smartHome;
    private EventProcessor processor;
    private List<String> turnedOnLights;
    private List<String> turnedOffLights;
    private Random randomGenerator;

    @BeforeEach
    void setUp() throws IOException {
        smartHome = SmartHomeProvider.readFile("output.js", "json");
        processor = new LightStateEventProcessor(smartHome);
        turnedOnLights = getLights(true);
        turnedOffLights = getLights(false);
        randomGenerator = new Random();
    }

    @Test
    void testTurnedOffLightTurnsOn() {
        int randInt = randomGenerator.nextInt(turnedOffLights.size());
        String lightId = turnedOffLights.get(randInt);
        SensorEvent event = new LightEvent(LightActionType.ON, lightId);

        processor.process(event);
        turnedOffLights.remove(lightId);
        turnedOnLights.add(lightId);

        checkLightsCondition(turnedOnLights, true);
        checkLightsCondition(turnedOffLights, false);
    }

    @Test
    void testTurnedOnLightTurnsOff() {
        int randInt = randomGenerator.nextInt(turnedOnLights.size());
        String lightId = turnedOnLights.get(randInt);
        SensorEvent event = new LightEvent(LightActionType.OFF, lightId);

        processor.process(event);
        turnedOnLights.remove(lightId);
        turnedOffLights.add(lightId);

        checkLightsCondition(turnedOnLights, true);
        checkLightsCondition(turnedOffLights, false);
    }

    private List<String> getLights(boolean condition) {
        List<String> lights = new ArrayList<>();
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            Light light = (Light) actionable;
            if (light.isOn() == condition) {
                lights.add(light.getId());
            }
        });
        return lights;
    }

    private void checkLightsCondition(List<String> lightIds, boolean condition) {
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            Light light = (Light) actionable;
            if (!(lightIds.contains(light.getId()))) return;
            assertEquals(light.isOn(), condition);
        });
    }

}