package ru.sbt.mipt.oop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.smarthome.remotecontrol.Command;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.*;

@Configuration
public class CommandsConfiguration {
    @Autowired
    private MainConfiguration main;

    private final String ALARM_CODE = "some_code";

    @Bean
    Command activateAlarmCommand() {
        return new ActivateAlarmCommand(main.alarm(), ALARM_CODE);
    }

    @Bean
    Command triggerAlertCommand() {
        return new TriggerAlertCommand(main.alarm());
    }

    @Bean
    Command turnAllLightsOnCommand() {
        return new TurnAllLightsOnCommand(main.smartHome());
    }

    @Bean
    Command turnAllLightsOffCommand() {
        return new TurnAllLightsOffCommand(main.smartHome());
    }

    @Bean
    Command closeHallDoorCommand() {
        return new CloseHallDoorCommand(main.smartHome());
    }

    @Bean
    Command turnHallLightOnCommand() {
        return new TurnHallLightOnCommand(main.smartHome());
    }
}
