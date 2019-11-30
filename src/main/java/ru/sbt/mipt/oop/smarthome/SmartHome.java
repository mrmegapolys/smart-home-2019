package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable{
    private final Collection<Room> rooms;
    private final Alarm alarm;

    public SmartHome(Alarm alarm, Collection<Room> rooms) {
        this.alarm = alarm;
        this.rooms = rooms;
    }

    public SmartHome(Alarm alarm) {
        this(alarm, new ArrayList<>());
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void execute(Action action) {
        action.run(this);
        alarm(action);
        processRooms(action);
    }

    private void alarm(Action action) {
        alarm.execute(action);
    }

    private void processRooms(Action action) {
        rooms.forEach(room -> room.execute(action));
    }
}
