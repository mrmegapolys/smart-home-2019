package ru.sbt.mipt.oop.devices.door;

import ru.sbt.mipt.oop.devices.Device;

public class Door extends Device {
    private boolean isOpen;

    public Door(String id, boolean isOpen) {
        super(id);
        this.isOpen = isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

}
