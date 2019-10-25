package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.devices.door.Door;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.run(this);
        for (Light light : lights) {
            light.execute(action);
        }
        for (Door door : doors) {
            door.execute(action);
        }
    }
}
