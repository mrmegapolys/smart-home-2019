package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.SensorEvent;

public class Logger {
    public static void newEvent(SensorEvent event) {
        write("Got event: " + event);
    }

    private static void write(String str) {
        System.out.println(str);
    }
}
