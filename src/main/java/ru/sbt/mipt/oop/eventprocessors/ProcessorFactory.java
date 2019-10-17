package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventprocessors.processors.SetDoorState;
import ru.sbt.mipt.oop.eventprocessors.processors.SetLightState;
import ru.sbt.mipt.oop.eventprocessors.processors.TurnLightsOffAfterLeaving;

import java.util.ArrayList;
import java.util.List;

public class ProcessorFactory {

    public static List<EventProcessor> getProcessors(SmartHome smartHome) {
        List<EventProcessor> processors = new ArrayList<>();

        processors.add(new SetDoorState(smartHome));
        processors.add(new TurnLightsOffAfterLeaving(smartHome));
        processors.add(new SetLightState(smartHome));

        return processors;
    }
}
