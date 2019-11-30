package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.devices.door.Door;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;

import java.util.Collection;

public class Room implements Actionable {
    private final Collection<Light> lights;
    private final Collection<Door> doors;
    private final String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.run(this);
        processLights(action);
        processDoors(action);
    }

    private void processDoors(Action action) {
        doors.forEach(door -> door.execute(action));
    }

    private void processLights(Action action) {
        lights.forEach(light -> light.execute(action));
    }
}
