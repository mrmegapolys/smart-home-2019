package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.scenarios.LightScenarios;

public class TurnAllLightsOnCommand implements Command {
    private final SmartHome smartHome;

    public TurnAllLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        LightScenarios.turnAllLightsOn(smartHome);
    }
}
