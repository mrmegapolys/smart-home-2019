package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.remotecontrol.Command;

public class ActivateAlarmCommand implements Command {
    private final Alarm alarm;
    private final String code;

    public ActivateAlarmCommand(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void execute() {
        alarm.activate(code);
    }

}
