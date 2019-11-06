package ru.sbt.mipt.oop.smarthome.devices.alarm;

public class Deactivated implements AlarmState {
    transient private final Alarm alarm;

    Deactivated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.setState(new Activated(alarm, code));
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void triggerAlert() {
        alarm.setState(new Alert(alarm));
    }
}
