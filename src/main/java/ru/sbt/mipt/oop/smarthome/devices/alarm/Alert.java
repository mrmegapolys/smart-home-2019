package ru.sbt.mipt.oop.smarthome.devices.alarm;

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
        if (alarm.isCorrectCode(code)) {
            alarm.setState(new Deactivated(alarm));
        }
    }

    @Override
    public void triggerAlert() {
    }
}
