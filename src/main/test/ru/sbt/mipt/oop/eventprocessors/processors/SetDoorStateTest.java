package ru.sbt.mipt.oop.eventprocessors.processors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;
import ru.sbt.mipt.oop.smarthome.devices.door.Door;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorActionType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetDoorStateTest {
    private SmartHome smartHome;
    private EventProcessor processor;

    @BeforeEach
    void setUp() throws IOException {
        smartHome = SmartHomeProvider.readFile("smart-home-1.js", "json");
        processor = new SetDoorState(smartHome);
    }

    @Test
    void testClosedDoorOpens() {
        String OBJECT_ID = "1";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_EVENT, DoorActionType.OPEN, OBJECT_ID);
        checkDoorCondition(false, OBJECT_ID);
        processor.process(event);
        checkDoorCondition(true, OBJECT_ID);
    }

    @Test
    void testOpenDoorCloses() {
        String OBJECT_ID = "3";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_EVENT, DoorActionType.CLOSE, OBJECT_ID);
        checkDoorCondition(true, OBJECT_ID);
        processor.process(event);
        checkDoorCondition(false, OBJECT_ID);
    }

    private void checkDoorCondition(boolean condition, String objectId) {
        smartHome.execute( (Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (!(door.getId().equals(objectId))) return;
            assertEquals(door.isOpen(), condition);
        });
    }
}