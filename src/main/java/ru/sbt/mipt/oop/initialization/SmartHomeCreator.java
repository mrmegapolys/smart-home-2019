package ru.sbt.mipt.oop.initialization;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;

public class SmartHomeCreator {
    public static SmartHome fromJson(String encoded) {
        Gson gson = new Gson();
        return gson.fromJson(encoded, SmartHome.class);
    }
}
