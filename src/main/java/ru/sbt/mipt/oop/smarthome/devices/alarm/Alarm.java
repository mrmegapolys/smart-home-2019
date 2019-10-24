package ru.sbt.mipt.oop.smarthome.devices.alarm;

import ru.sbt.mipt.oop.smarthome.devices.Device;
import ru.sbt.mipt.oop.smarthome.devices.alarm.states.AlarmState;
import ru.sbt.mipt.oop.smarthome.devices.alarm.states.Deactivated;

public class Alarm extends Device {
    private AlarmState state;
    private final int code;

    public Alarm(String id, int code) {
        super(id);
        this.code = code;
        this.state = new Deactivated(this);
    }

    public void setState(AlarmState state) {
        this.state = state;
    }

    public AlarmState getState() {
        return state;
    }

    public boolean isCorrectCode(int code) {
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
