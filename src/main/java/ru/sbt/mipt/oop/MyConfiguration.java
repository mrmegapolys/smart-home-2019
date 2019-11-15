package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
public class MyConfiguration {

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager manager = new SensorEventsManager();
        for (EventProcessor eventProcessor : eventProcessors()) {
            manager.registerEventHandler(new CCEventProcessorAdapter(eventProcessor));
        }
        return manager;
    }

    @Bean
    SmartHome smartHome() {
        try {
            return SmartHomeProvider.readFile("output.js", "json");
        } catch (IOException e) {
            System.out.println("Failed to read smart home config.");
            throw new RuntimeException(e);
        }
    }

    @Bean
    Alarm alarm() {
        return smartHome().getAlarm();
    }

    @Bean
    Notifier notifier() {
        return new SMSNotifier();
    }

    @Bean
    List<EventProcessor> eventProcessors() {
        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new ActivatedAlarmDecorator(new DoorStateEventProcessor(smartHome()), alarm()));
        processors.add(new ActivatedAlarmDecorator(new LightStateEventProcessor(smartHome()), alarm()));
        processors.add(new ActivatedAlarmDecorator(new HallDoorEventProcessor(smartHome()), alarm()));
        processors.add(new AlarmStateEventProcessor(alarm()));
        processors.add(new NotificationEventProcessor(alarm(), notifier()));
        return processors;
    }

}
