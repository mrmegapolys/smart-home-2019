package ru.sbt.mipt.oop.eventfactories;

import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmActionType;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmEvent;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorActionType;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorEvent;
import ru.sbt.mipt.oop.smarthome.devices.light.LightActionType;
import ru.sbt.mipt.oop.smarthome.devices.light.LightEvent;

import java.util.Random;

import static java.lang.Math.random;

public class RandomSensorEventFactory implements SensorEventFactory {
    public SensorEvent nextEvent() {
        if (random() < 0.02) return null;

        String objectId = "" + nextRandom(10);
        int actionType = nextRandom(2);

        int i = nextRandom(3);
        if (i == 0) return new DoorEvent(DoorActionType.values()[actionType], objectId);
        if (i == 1) return new LightEvent(LightActionType.values()[actionType], objectId);
        if (i == 2) return new AlarmEvent(AlarmActionType.values()[actionType], "0", "12345");
        return null;
    }

    private int nextRandom(int bound) {
        return new Random().nextInt(bound);
    }
}
