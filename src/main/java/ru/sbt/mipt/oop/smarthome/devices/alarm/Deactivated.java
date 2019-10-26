package ru.sbt.mipt.oop.smarthome.devices.alarm;

public class Deactivated implements AlarmState {
    private Alarm alarm;

    public Deactivated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
        if (alarm.isCorrectCode(code)) {
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
