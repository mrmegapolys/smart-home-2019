package ru.sbt.mipt.oop.smarthome.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RemoteControlImpl implements RemoteControl {
    private final Set<String> buttons;
    private final Map<String, Command> commands = new HashMap<>();

    public RemoteControlImpl(Set<String> buttons) {
        this.buttons = buttons;
    }

    public boolean setCommand(String button, Command command) {
        if (!buttons.contains(button)) return false;
        commands.put(button, command);
        return true;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        Command command = commands.get(buttonCode);
        if (command != null) command.execute();
    }
}
