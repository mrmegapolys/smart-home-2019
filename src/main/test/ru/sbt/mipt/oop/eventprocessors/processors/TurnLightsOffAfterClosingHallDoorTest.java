package ru.sbt.mipt.oop.eventprocessors.processors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;
import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.door.Door;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorActionType;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorEvent;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TurnLightsOffAfterClosingHallDoorTest {
    private SmartHome smartHome;
    private EventProcessor processor;
    private List<String> turnedOnLights;
    private List<String> turnedOffLights;
    private String HALL_DOOR_ID;

    @BeforeEach
    void setUp() throws IOException {
        smartHome = SmartHomeProvider.readFile("output.js", "json");
        processor = new TurnLightsOffAfterClosingHallDoor(smartHome);
        turnedOnLights = getLights(true);
        turnedOffLights = getLights(false);
        HALL_DOOR_ID = "4";
    }

    @Test
    void testAllTurnedOnLightsTurnOffAfterClosingHallDoor() {
        SensorEvent event = new DoorEvent(DoorActionType.CLOSE, HALL_DOOR_ID);
        checkLightsCondition(turnedOnLights, true);
        processor.process(event);
        checkLightsCondition(turnedOnLights, false);
    }

    @Test
    void testAllTurnedOffLightsRemainTurnedOffAfterClosingHallDoor() {
        SensorEvent event = new DoorEvent(DoorActionType.CLOSE, HALL_DOOR_ID);
        checkLightsCondition(turnedOffLights, false);
        processor.process(event);
        checkLightsCondition(turnedOffLights, false);
    }

    @Test
    void testNothingChangesAfterClosingNotHallDoor() {
        List<String> notHallDoorIds = getNotHallDoorIds();
        for (String notHallDoor : notHallDoorIds) {
            SensorEvent event = new DoorEvent(DoorActionType.CLOSE, notHallDoor);
            checkLightsCondition(turnedOnLights, true);
            checkLightsCondition(turnedOffLights, false);
            processor.process(event);
            checkLightsCondition(turnedOnLights, true);
            checkLightsCondition(turnedOffLights, false);
        }

    }

    private List<String> getNotHallDoorIds() {
        List<String> notHallDoorIds = new ArrayList<>();
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (door.getId().equals(HALL_DOOR_ID)) return;
            notHallDoorIds.add(door.getId());
        });
        return notHallDoorIds;
    }

    private void checkLightsCondition(List<String> lightIds, boolean condition) {
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            Light light = (Light) actionable;
            if (!(lightIds.contains(light.getId()))) return;
            assertEquals(light.isOn(), condition);
        });
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

}