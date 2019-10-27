package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable{
    private Collection<Room> rooms;
    private final Alarm alarm;

    public SmartHome(Alarm alarm) {
        this.alarm = alarm;
        rooms = new ArrayList<>();
    }

    public SmartHome(Alarm alarm, Collection<Room> rooms) {
        this.alarm = alarm;
        this.rooms = rooms;
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
        alarm.execute(action);
        for (Room room : rooms) {
            room.execute(action);
        }
    }
}
