package ru.sbt.mipt.oop;

import com.google.gson.Gson;

public class SmartHomeSerializer {
    public static SmartHome fromJson(String encoded) {
        Gson gson = new Gson();
        return gson.fromJson(encoded, SmartHome.class);
    }

    public static String toJson(SmartHome smartHome) {
        Gson gson = new Gson();
        return gson.toJson(smartHome);
    }
}
