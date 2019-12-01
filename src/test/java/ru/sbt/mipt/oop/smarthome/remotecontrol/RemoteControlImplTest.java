package ru.sbt.mipt.oop.smarthome.remotecontrol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;

import java.util.*;

class RemoteControlImplTest {
    private Map<String, Command> commands;
    private RemoteControlImpl remoteControl;

    @BeforeEach
    void setUp() {
        commands = getTestCommandMap();
        remoteControl = new RemoteControlImpl(commands.keySet());
        commands.forEach(remoteControl::setCommand);
    }

    private Map<String, Command> getTestCommandMap() {
        Map<String, Command> testCommandMap = new HashMap<>();

        testCommandMap.put("A", Mockito.mock(Command.class));
        testCommandMap.put("B", Mockito.mock(Command.class));
        testCommandMap.put("C", Mockito.mock(Command.class));
        testCommandMap.put("D", Mockito.mock(Command.class));

        return testCommandMap;
    }

    @Test
    void testCommandExecutedOnButtonPressed() {
        for (String button : commands.keySet()) {
            remoteControl.onButtonPressed(button);

            commands.forEach((key, value) -> {
                if (key.equals(button)) {
                    Mockito.verify(value).execute();
                } else {
                    Mockito.verifyZeroInteractions(value);
                }
            });

            Mockito.clearInvocations(commands.values().toArray());
        }
    }
}