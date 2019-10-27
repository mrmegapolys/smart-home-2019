package ru.sbt.mipt.oop.smarthome.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmState;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class SmartHomeSerializer<T> {
    public static SmartHome fromJson(String encoded) {
        Gson gson = getGson();
        SmartHome smartHome = gson.fromJson(encoded, SmartHome.class);
        setAlarmState(smartHome);
        return smartHome;
    }

    private static void setAlarmState(SmartHome smartHome) { //TODO: refactor exception handling
        Alarm alarm = smartHome.getAlarm();
        AlarmState currentState = alarm.getState();
        try {
            AlarmState newState = currentState.getClass().getDeclaredConstructor(Alarm.class).newInstance(alarm);
            Field stateField = alarm.getClass().getDeclaredField("state");
            stateField.setAccessible(true);
            stateField.set(alarm, newState);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }


    public static String toJson(SmartHome smartHome) {
        Gson gson = getGson();
        return gson.toJson(smartHome);
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(AlarmState.class, new InterfaceAdapter<AlarmState>())
                .setPrettyPrinting().
                create();
    }
}
