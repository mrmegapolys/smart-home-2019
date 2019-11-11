package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

public class TriggerAlertCommand implements Command {
    private final Alarm alarm;

    public TriggerAlertCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.triggerAlert();
    }

}
