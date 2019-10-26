package ru.sbt.mipt.oop.smarthome.devices.alarm.states;

import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

public class Alert implements AlarmState {
    private Alarm alarm;

    public Alert(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
    }

    @Override
    public void deactivate(int code) {
        if (code == alarm.getCode()) {
            alarm.setState(new Deactivated(alarm));
        }
    }

    @Override
    public void triggerAlert() {
    }
}
