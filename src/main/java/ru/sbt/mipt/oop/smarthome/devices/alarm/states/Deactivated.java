package ru.sbt.mipt.oop.smarthome.devices.alarm.states;

import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

public class Deactivated implements AlarmState {
    private Alarm alarm;

    public Deactivated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
        if (code == alarm.getCode()) {
            alarm.setState(new Activated(alarm));
        }
    }

    @Override
    public void deactivate(int code) {
    }

    @Override
    public void triggerAlert() {
        alarm.setState(new Alert(alarm));
    }
}
