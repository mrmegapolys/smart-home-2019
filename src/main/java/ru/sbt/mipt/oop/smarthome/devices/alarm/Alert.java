package ru.sbt.mipt.oop.smarthome.devices.alarm;

public class Alert implements AlarmState {
    transient private final Alarm alarm;

    Alert(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (alarm.isCorrectCode(code)) {
            alarm.setState(new Deactivated(alarm));
        }
    }

    @Override
    public void triggerAlert() {
    }
}
