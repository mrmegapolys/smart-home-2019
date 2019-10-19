package ru.sbt.mipt.oop.eventprocessors.processors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;
import ru.sbt.mipt.oop.smarthome.devices.light.LightActionType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetLightStateTest {
    private SmartHome smartHome;
    private EventProcessor processor;

    @BeforeEach
    void setUp() throws IOException {
        smartHome = SmartHomeProvider.readFile("smart-home-1.js", "json");
        processor = new SetLightState(smartHome);
    }

    @Test
    void testTurnedOffLightTurnsOn() {
        String OBJECT_ID = "4";
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_EVENT, LightActionType.ON, OBJECT_ID);
        checkLightCondition(false, OBJECT_ID);
        processor.process(event);
        checkLightCondition(true, OBJECT_ID);
    }

    @Test
    void testTurnedOnLightTurnsOff() {
        String OBJECT_ID = "2";
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_EVENT, LightActionType.OFF, OBJECT_ID);
        checkLightCondition(true, OBJECT_ID);
        processor.process(event);
        checkLightCondition(false, OBJECT_ID);
    }

    private void checkLightCondition(boolean condition, String objectId) {
        smartHome.execute( (Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            Light light = (Light) actionable;
            if (!(light.getId().equals(objectId))) return;
            assertEquals(light.isOn(), condition);
        });
    }

}