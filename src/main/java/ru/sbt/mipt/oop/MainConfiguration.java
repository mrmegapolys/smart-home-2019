package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.eventprocessors.CCEventProcessorAdapter;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

import java.io.IOException;
import java.util.Collection;

@Configuration
@Import(ProcessorsConfiguration.class)
public class MainConfiguration {
    @Autowired
    private Collection<EventProcessor> processors;

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager manager = new SensorEventsManager();
        for (EventProcessor eventProcessor : processors) {
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

}
