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
        AlarmState state = Objects.equals(code, this.code) ?
                new Deactivated(alarm) :
                new Alert(alarm, this.code);

        alarm.setState(state);
    }

    @Override
    public void triggerAlert() {
        alarm.setState(new Alert(alarm, code));
    }
}
