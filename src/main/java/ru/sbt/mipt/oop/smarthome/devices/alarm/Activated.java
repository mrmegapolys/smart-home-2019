package ru.sbt.mipt.oop.smarthome.devices.alarm;

import java.util.Objects;

public class Activated implements AlarmState {
    transient private final Alarm alarm;
    private final String code;

    Activated(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (Objects.equals(code, this.code)) {
            alarm.setState(new Deactivated(alarm));
        } else {
            alarm.setState(new Alert(alarm, this.code));
        }
    }

    @Override
    public void triggerAlert() {
        alarm.setState(new Alert(alarm, code));
    }
}
