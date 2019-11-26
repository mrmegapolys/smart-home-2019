package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent.CCEventAdapter;
import ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent.CCEventProcessorAdapter;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

import java.io.IOException;
import java.util.Collection;

@Configuration
@ComponentScan
public class MainConfiguration {
    @Autowired
    private Collection<EventProcessor> processors;
    @Autowired
    private Collection<CCEventAdapter> adapters;

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager manager = new SensorEventsManager();
        for (EventProcessor eventProcessor : processors) {
            manager.registerEventHandler(new CCEventProcessorAdapter(eventProcessor, adapters));
        }
        return manager;
    }

    @Bean
    SmartHome smartHome() {
        String filepath = "output.js";
        try {
            return SmartHomeProvider.readFile(filepath, "json");
        } catch (IOException e) {
            String msg = String.format("Failed to read smart home config from \"%s\"", filepath);
            throw new BeanCreationException(msg, e);
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
