package ru.sbt.mipt.oop.smarthome.devices.alarm.states;

import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

public class Activated implements AlarmState {
    private Alarm alarm;

    public Activated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
    }

    @Override
    public void deactivate(int code) {
        if (code == alarm.getCode()) {
            alarm.setState(new Deactivated(alarm));
        } else {
            alarm.triggerAlert();
        }
    }

    @Override
    public void triggerAlert() {
        alarm.setState(new Alert(alarm));
    }
}
