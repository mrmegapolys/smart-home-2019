package ru.sbt.mipt.oop.scenarios;

import ru.sbt.mipt.oop.Logger;
import ru.sbt.mipt.oop.devices.light.Light;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;

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
