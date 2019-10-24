package ru.sbt.mipt.oop.smarthome.devices.alarm;

import ru.sbt.mipt.oop.smarthome.devices.Device;
import ru.sbt.mipt.oop.smarthome.devices.alarm.states.AlarmState;

public class Alarm extends Device {
    private AlarmState state;
    private final int code;

    public Alarm(String id, AlarmState state, int code) {
        super(id);
        this.state = state;
        this.code = code;
    }

    public void setState(AlarmState state) {
        this.state = state;
    }

    public int getCode() {
        return code;
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
