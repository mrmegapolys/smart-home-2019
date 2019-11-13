package ru.sbt.mipt.oop.smarthome.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.devices.alarm.AlarmState;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class SmartHomeSerializer<T> {

    public static SmartHome fromJson(String encoded) {
        Gson gson = getGson();
        SmartHome smartHome = gson.fromJson(encoded, SmartHome.class);
        setAlarmState(smartHome);
        return smartHome;
    }

    public static String toJson(SmartHome smartHome) {
        Gson gson = getGson();
        return gson.toJson(smartHome);
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(AlarmState.class, new InterfaceAdapter<AlarmState>())
                .setPrettyPrinting()
                .create();
    }

    private static void setAlarmState(SmartHome smartHome) {
        Alarm alarm = smartHome.getAlarm();
        AlarmState currentState = alarm.getState();
        AlarmState newState = getNewStateInstance(currentState, alarm);

        try {
            Field stateField = alarm.getClass().getDeclaredField("state");
            stateField.setAccessible(true);
            stateField.set(alarm, newState);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new JsonParseException(e);
        }
    }

    private static AlarmState getNewStateInstance(AlarmState currentState, Alarm alarm) {
        Constructor<? extends AlarmState> constructor;
        try {
            constructor = currentState.getClass().getDeclaredConstructor(Alarm.class);
        } catch (NoSuchMethodException e) {
            throw new JsonParseException(e);
        }
        constructor.setAccessible(true);

        try {
            return constructor.newInstance(alarm);
        } catch (InstantiationException | InvocationTargetException e) {
            throw new JsonParseException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
