package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventfactories.EventFactory;
import ru.sbt.mipt.oop.eventfactories.RandomEventFactory;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.decorators.ActivatedAlarmDecorator;
import ru.sbt.mipt.oop.eventprocessors.decorators.NotifierDecorator;
import ru.sbt.mipt.oop.eventprocessors.processors.SetAlarmState;
import ru.sbt.mipt.oop.eventprocessors.processors.SetDoorState;
import ru.sbt.mipt.oop.eventprocessors.processors.SetLightState;
import ru.sbt.mipt.oop.eventprocessors.processors.TurnLightsOffAfterClosingHallDoor;
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

        EventProcessor setDoorState = new SetDoorState(smartHome);
        setDoorState = new ActivatedAlarmDecorator(setDoorState, alarm);
        setDoorState = new NotifierDecorator(setDoorState, alarm, notifier);
        processors.add(setDoorState);

        EventProcessor setLightState = new SetLightState(smartHome);
        setLightState = new ActivatedAlarmDecorator(setLightState, alarm);
        setLightState = new NotifierDecorator(setLightState, alarm, notifier);
        processors.add(setLightState);

        EventProcessor turnLightsOff = new TurnLightsOffAfterClosingHallDoor(smartHome);
        turnLightsOff = new ActivatedAlarmDecorator(turnLightsOff, alarm);
        processors.add(turnLightsOff);

        processors.add(new SetAlarmState(smartHome.getAlarm()));

        return processors;
    }

}
