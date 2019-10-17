package ru.sbt.mipt.oop.smarthome.devices;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;

public class Device implements Actionable {
    private final String id;

    public Device(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void execute(Action action) {
        action.run();
    }
}
