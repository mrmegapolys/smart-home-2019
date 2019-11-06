package ru.sbt.mipt.oop.smarthome.devices.alarm;

import ru.sbt.mipt.oop.smarthome.devices.Device;

public class Alarm extends Device {
    private AlarmState state;
    private final String defaultCode;

    public Alarm(String id, String defaultCode) {
        super(id);
        this.defaultCode = defaultCode;
        this.state = new Deactivated(this);
    }

    public AlarmState getState() {
        return state;
    }

    void setState(AlarmState state) {
        this.state = state;
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

    String getDefaultCode() {
        return defaultCode;
    }
}
