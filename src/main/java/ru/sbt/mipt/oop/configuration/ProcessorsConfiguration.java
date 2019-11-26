package ru.sbt.mipt.oop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.decorators.ActivatedAlarmDecorator;
import ru.sbt.mipt.oop.eventprocessors.processors.*;

@Configuration
public class ProcessorsConfiguration{
    @Autowired
    private MainConfiguration main;

    @Bean
    EventProcessor doorStateEventProcessor() {
        return new ActivatedAlarmDecorator(new DoorStateEventProcessor(main.smartHome()), main.alarm());
    }

    @Bean
    EventProcessor lightStateEventProcessor() {
        return new ActivatedAlarmDecorator(new LightStateEventProcessor(main.smartHome()), main.alarm());
    }

    @Bean
    EventProcessor hallDoorEventProcessor() {
        return new ActivatedAlarmDecorator(new HallDoorEventProcessor(main.smartHome()), main.alarm());
    }

    @Bean
    EventProcessor alarmStateEventProcessor() {
        return new AlarmStateEventProcessor(main.alarm());
    }

    @Bean
    EventProcessor notificationEventProcessor() {
        return new NotificationEventProcessor(main.alarm(), main.notifier());
    }
}
