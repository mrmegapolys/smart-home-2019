package ru.sbt.mipt.oop.smarthome.devices.alarm;

import java.util.Objects;

public class Alert implements AlarmState {
    transient private final Alarm alarm;
    private final String code;

    Alert(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    Alert(Alarm alarm) {
        this(alarm, alarm.getDefaultCode());
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (Objects.equals(code, this.code)) {
            alarm.setState(new Deactivated(alarm));
        }
    }

    @Override
    public void triggerAlert() {
    }
}
