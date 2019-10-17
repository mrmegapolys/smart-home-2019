package ru.sbt.mipt.oop.smarthome.scenarios;

import ru.sbt.mipt.oop.Logger;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;

public class LightScenarios {
    public static void turnAllLightsOff(SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(false);
            }
        }
        Logger.info("All lights have been turned off.");
    }
}
