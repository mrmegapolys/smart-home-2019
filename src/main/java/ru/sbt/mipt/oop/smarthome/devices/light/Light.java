package ru.sbt.mipt.oop.smarthome.devices.light;

import ru.sbt.mipt.oop.smarthome.devices.Device;

public class Light extends Device {
    private boolean isOn;

    public Light(String id, boolean isOn) {
        super(id);
        this.isOn = isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isOn() {
        return isOn;
    }
}
