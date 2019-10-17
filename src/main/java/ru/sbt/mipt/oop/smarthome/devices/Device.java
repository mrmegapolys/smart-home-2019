package ru.sbt.mipt.oop.smarthome.devices;

public class Device {
    private final String id;

    public Device(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
