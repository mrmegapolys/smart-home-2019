package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.devices.SensorEvent;

public class Logger {
    public static void newEvent(SensorEvent event) {
        write("Got event: " + event);
    }

    public static void info(String info) {
        write(info);
    }

    private static void write(String str) {
        System.out.println(str);
    }
}
