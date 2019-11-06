package ru.sbt.mipt.oop.smarthome.devices.alarm;

import ru.sbt.mipt.oop.smarthome.devices.Device;

import java.util.Objects;

public class Alarm extends Device {
    private AlarmState state;
    private final String code;

    public Alarm(String id, String code) {
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

    boolean isCorrectCode(String code) {
        return Objects.equals(this.code, code);
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void triggerAlert() {
        state.triggerAlert();
    }
}
