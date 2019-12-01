package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.remotecontrol.Command;
import ru.sbt.mipt.oop.smarthome.scenarios.LightScenarios;

public class TurnAllLightsOffCommand implements Command {
    private final SmartHome smartHome;

    public TurnAllLightsOffCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        LightScenarios.turnAllLightsOff(smartHome);
    }
}
