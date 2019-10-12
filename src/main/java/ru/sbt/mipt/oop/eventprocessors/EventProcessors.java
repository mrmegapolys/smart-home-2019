package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.eventprocessors.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.processors.LightEventProcessor;

public enum EventProcessors {
    DOOR_EVENT_PROCESSOR {
        @Override
        public EventProcessor createProcessor() {
            return new DoorEventProcessor();
        }
    },

    LIGHT_EVENT_PROCESSOR {
        @Override
        public EventProcessor createProcessor() {
            return new LightEventProcessor();
        }
    };

    public abstract EventProcessor createProcessor();
}
