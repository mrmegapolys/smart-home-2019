package ru.sbt.mipt.oop.scenarios;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.light.Light;
import ru.sbt.mipt.oop.utils.Logger;

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
