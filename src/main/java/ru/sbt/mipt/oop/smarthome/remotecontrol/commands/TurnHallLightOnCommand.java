package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;

public class TurnHallLightOnCommand implements Command {
    private final SmartHome smartHome;

    public TurnHallLightOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Room)) return;
            Room room = (Room) actionable;
            if (!(room.getName().equals("hall"))) return;

            room.execute((Actionable hallActionable) -> {
                if (!(hallActionable instanceof Light)) return;
                ((Light) hallActionable).setOn(true);
            });
        });
    }

}
