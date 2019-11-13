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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoorStateEventProcessorTest {
    private SmartHome smartHome;
    private EventProcessor processor;
    private List<String> openDoors;
    private List<String> closedDoors;
    private Random randomGenerator;

    @BeforeEach
    void setUp() throws IOException {
        smartHome = SmartHomeProvider.readFile("output.js", "json");
        processor = new DoorStateEventProcessor(smartHome);
        openDoors = getDoors(true);
        closedDoors = getDoors(false);
        randomGenerator = new Random();
    }

    @Test
    void testClosedDoorOpens() {
        int randInt = randomGenerator.nextInt(closedDoors.size());
        String doorId = closedDoors.get(randInt);
        SensorEvent event = new DoorEvent(DoorActionType.OPEN, doorId);

        processor.process(event);
        closedDoors.remove(doorId);
        openDoors.add(doorId);

        checkDoorsCondition(openDoors, true);
        checkDoorsCondition(closedDoors, false);
    }

    @Test
    void testOpenDoorCloses() {
        int randInt = randomGenerator.nextInt(openDoors.size());
        String doorId = openDoors.get(randInt);
        SensorEvent event = new DoorEvent(DoorActionType.CLOSE, doorId);

        processor.process(event);
        openDoors.remove(doorId);
        closedDoors.add(doorId);

        checkDoorsCondition(openDoors, true);
        checkDoorsCondition(closedDoors, false);
    }

    private List<String> getDoors(boolean condition) {
        List<String> doors = new ArrayList<>();
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (door.isOpen() == condition) {
                doors.add(door.getId());
            }
        });
        return doors;
    }

    private void checkDoorsCondition(List<String> doorIds, boolean condition) {
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (!(doorIds.contains(door.getId()))) return;
            assertEquals(door.isOpen(), condition);
        });
    }
}