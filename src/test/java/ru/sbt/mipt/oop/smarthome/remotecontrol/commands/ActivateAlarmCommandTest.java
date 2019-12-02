package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.smarthome.devices.alarm.Alarm;

class ActivateAlarmCommandTest {
    @Test
    void testExecute() {
        String code = "12345";
        Alarm alarm = Mockito.mock(Alarm.class);
        new ActivateAlarmCommand(alarm, code).execute();
        Mockito.verify(alarm).activate(code);
    }
}