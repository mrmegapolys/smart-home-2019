package ru.sbt.mipt.oop.devices;

public class Door {
    private final String id;
    private boolean isOpen;

    public Door(String id, boolean isOpen) {
        this.id = id;
        this.isOpen = isOpen;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

}
