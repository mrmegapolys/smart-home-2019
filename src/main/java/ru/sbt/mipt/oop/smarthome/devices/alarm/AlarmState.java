package ru.sbt.mipt.oop.smarthome.devices.alarm;

public interface AlarmState {
    void activate(int code);
    void deactivate(int code);
    void triggerAlert();
}
