package ru.sbt.mipt.oop.smarthome.scenarios;

import ru.sbt.mipt.oop.Logger;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;

public class LightScenarios {
    public static void turnAllLightsOff(SmartHome smartHome) {
        Logger.info("Turning all lights off.");
        smartHome.execute( (Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            ((Light) actionable).setOn(false);
        });
    }
}
