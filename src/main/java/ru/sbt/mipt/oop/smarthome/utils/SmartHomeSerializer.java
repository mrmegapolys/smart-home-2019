package ru.sbt.mipt.oop.smarthome.utils;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.smarthome.SmartHome;

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
