package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.eventprocessors.CCEventProcessorAdapter;
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
        SensorEventsManager manager = new SensorEventsManager();

        for (EventProcessor processor : processors) {
            manager.registerEventHandler(new CCEventProcessorAdapter(processor));
        }
        manager.start();
    }

    private static List<EventProcessor> getProcessors(SmartHome smartHome) {
        Notifier notifier = new SMSNotifier();
        Alarm alarm = smartHome.getAlarm();
        List<EventProcessor> processors = new ArrayList<>();

        processors.add(new ActivatedAlarmDecorator(new DoorStateEventProcessor(smartHome), alarm));
        processors.add(new ActivatedAlarmDecorator(new LightStateEventProcessor(smartHome), alarm));
        processors.add(new ActivatedAlarmDecorator(new HallDoorEventProcessor(smartHome), alarm));
        processors.add(new AlarmStateEventProcessor(alarm));
        processors.add(new NotificationEventProcessor(alarm, notifier));

        return processors;
    }

}
