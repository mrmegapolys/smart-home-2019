package ru.sbt.mipt.oop.smarthome.devices.alarm;

public class Activated implements AlarmState {
    transient private final Alarm alarm;

    Activated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
    }

    @Override
    public void deactivate(int code) {
        if (alarm.isCorrectCode(code)) {
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
