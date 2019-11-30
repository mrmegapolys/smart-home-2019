package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventfactories.RandomSensorEventFactory;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.decorators.ActivatedAlarmDecorator;
import ru.sbt.mipt.oop.eventprocessors.processors.*;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class Application {
    public static void main(String... args) {
        SmartHome smartHome = initializeSmartHome();

        EventDispatcher eventDispatcher = new EventDispatcher(
                new RandomSensorEventFactory(),
                getProcessors(smartHome)
        );
        eventDispatcher.run();
    }

    private static SmartHome initializeSmartHome() {
        try {
            return SmartHomeProvider.readFile("output.js", "json");
        } catch (IOException e) {
            System.out.println("Failed to read smart home config.");
            System.exit(-1);
            return null;
        }
    }

    private static List<EventProcessor> getProcessors(SmartHome smartHome) {
        Alarm alarm = smartHome.getAlarm();
        return asList(
                new ActivatedAlarmDecorator(new DoorStateEventProcessor(smartHome), alarm),
                new ActivatedAlarmDecorator(new LightStateEventProcessor(smartHome), alarm),
                new ActivatedAlarmDecorator(new HallDoorEventProcessor(smartHome), alarm),
                new AlarmStateEventProcessor(alarm),
                new NotificationEventProcessor(alarm, new SMSNotifier())
        );
    }
}
