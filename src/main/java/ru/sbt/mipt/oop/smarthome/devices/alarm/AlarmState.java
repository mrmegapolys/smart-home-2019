package ru.sbt.mipt.oop.smarthome.devices.alarm;

public interface AlarmState {
    void activate(String code);
    void deactivate(String code);
    void triggerAlert();
}
