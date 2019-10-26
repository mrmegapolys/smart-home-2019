package ru.sbt.mipt.oop.smarthome.devices.alarm;

import ru.sbt.mipt.oop.smarthome.devices.Device;

public class Alarm extends Device {
    private AlarmState state;
    private final int code;

    public Alarm(String id, int code) {
        super(id);
        this.code = code;
        this.state = new Deactivated(this);
    }

    public AlarmState getState() {
        return state;
    }

    void setState(AlarmState state) {
        this.state = state;
    }

    boolean isCorrectCode(int code) {
        return this.code == code;
    }

    public void activate(int code) {
        state.activate(code);
    }

    public void deactivate(int code) {
        state.deactivate(code);
    }

    public void triggerAlert() {
        state.triggerAlert();
    }
}
