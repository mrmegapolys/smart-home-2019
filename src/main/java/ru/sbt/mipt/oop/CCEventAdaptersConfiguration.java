package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent.CCDoorEventAdapter;
import ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent.CCEventAdapter;
import ru.sbt.mipt.oop.eventprocessors.adapters.ccsensorevent.CCLightEventAdapter;

@Configuration
public class CCEventAdaptersConfiguration {
    @Bean
    CCEventAdapter ccDoorEventAdapter() {
        return new CCDoorEventAdapter();
    }

    @Bean
    CCEventAdapter ccLightEventAdapter() {
        return new CCLightEventAdapter();
    }
}
