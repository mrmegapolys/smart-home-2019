package ru.sbt.mipt.oop.configuration;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.Notifier;
import ru.sbt.mipt.oop.SMSNotifier;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent.CCEventAdapter;
import ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent.CCEventProcessorAdapter;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.SmartHomeProvider;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.remotecontrol.RemoteControlImpl;

import java.io.IOException;
import java.util.*;

import static java.util.Arrays.asList;

@Configuration
@ComponentScan
public class MainConfiguration {
    @Autowired
    private Collection<EventProcessor> processors;
    @Autowired
    private Collection<CCEventAdapter> adapters;
    @Autowired
    private CommandsConfiguration commandsConfiguration;

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

    @Bean
    RemoteControlRegistry remoteControlRegistry(Collection<RemoteControl> controls) {
        RemoteControlRegistry registry = new RemoteControlRegistry();
        registry.registerRemoteControl(remoteControlImpl(), "rc_id");
        return registry;
    }

    @Bean
    RemoteControl remoteControlImpl() {
        RemoteControlImpl remoteControl = new RemoteControlImpl(getButtons());
        registerCommands(remoteControl);
        return remoteControl;
    }

    private Set<String> getButtons() {
        return new HashSet<>(asList(
                "A", "B", "C", "D", "1", "2", "3", "4"
        ));
    }

    private void registerCommands(RemoteControlImpl remoteControl) {
        remoteControl.setCommand("A", commandsConfiguration.activateAlarmCommand());
        remoteControl.setCommand("B", commandsConfiguration.triggerAlertCommand());
        remoteControl.setCommand("1", commandsConfiguration.turnAllLightsOnCommand());
        remoteControl.setCommand("2", commandsConfiguration.turnAllLightsOffCommand());
        remoteControl.setCommand("3", commandsConfiguration.turnHallLightOnCommand());
        remoteControl.setCommand("4", commandsConfiguration.closeHallDoorCommand());
    }

}
