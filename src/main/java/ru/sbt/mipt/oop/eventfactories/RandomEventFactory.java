package ru.sbt.mipt.oop.eventfactories;

import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmActionType;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmEvent;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorActionType;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorEvent;
import ru.sbt.mipt.oop.smarthome.devices.light.LightActionType;
import ru.sbt.mipt.oop.smarthome.devices.light.LightEvent;

public class RandomEventFactory implements EventFactory {

    public SensorEvent getNextSensorEvent(){
        if (Math.random() < 0.02) return null;
        String objectId = "" + ((int) (10 * Math.random()));
        int actionType = (int) (Math.random() * 2);

        switch ((int) (Math.random() * 3)) {
            case (0):
                return new DoorEvent(DoorActionType.values()[actionType], objectId);
            case (1):
                return new LightEvent(LightActionType.values()[actionType], objectId);
            case (2):
                return new AlarmEvent(AlarmActionType.values()[actionType], "0", "12345");
            default:
                return null;
        }

    }
}
