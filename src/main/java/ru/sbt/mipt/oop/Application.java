package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventfactories.EventFactory;
import ru.sbt.mipt.oop.eventfactories.RandomEventFactory;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.decorators.ActivatedAlarmDecorator;
import ru.sbt.mipt.oop.eventprocessors.processors.*;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args) {
        String FILEPATH = "output.js";
        SmartHome smartHome;

        try {
            smartHome = SmartHomeProvider.readFile(FILEPATH, "json");
        } catch (IOException e) {
            System.out.println("Failed to read smart home config.");
            return;
        }

        List<EventProcessor> processors = getProcessors(smartHome);
        EventFactory eventFactory = new RandomEventFactory();

        Dispatcher dispatcher = new Dispatcher(eventFactory, processors);
        dispatcher.run();
    }

    private static List<EventProcessor> getProcessors(SmartHome smartHome) {
        Notifier notifier = new SMSNotifier();
        Alarm alarm = smartHome.getAlarm();
        List<EventProcessor> processors = new ArrayList<>();

        processors.add(new ActivatedAlarmDecorator(new SetDoorState(smartHome), alarm));
        processors.add(new ActivatedAlarmDecorator(new SetLightState(smartHome), alarm));
        processors.add(new ActivatedAlarmDecorator(new TurnLightsOffAfterClosingHallDoor(smartHome), alarm));
        processors.add(new SetAlarmState(alarm));
        processors.add(new SendNotification(alarm, notifier));

        return processors;
    }

}
