package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

class TriggerAlertCommandTest {
    @Test
    void testExecute() {
        Alarm alarm = Mockito.mock(Alarm.class);
        new TriggerAlertCommand(alarm).execute();
        Mockito.verify(alarm).triggerAlert();
    }
}