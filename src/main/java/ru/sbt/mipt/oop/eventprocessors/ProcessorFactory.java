package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.eventprocessors.processors.SetDoorState;
import ru.sbt.mipt.oop.eventprocessors.processors.SetLightState;
import ru.sbt.mipt.oop.eventprocessors.processors.TurnLightsOffAfterLeaving;

import java.util.ArrayList;
import java.util.List;

public class ProcessorFactory {

    public static List<EventProcessor> getProcessors() {
        List<EventProcessor> processors = new ArrayList<>();

        processors.add(new SetDoorState());
        processors.add(new TurnLightsOffAfterLeaving());
        processors.add(new SetLightState());

        return processors;
    }
}
