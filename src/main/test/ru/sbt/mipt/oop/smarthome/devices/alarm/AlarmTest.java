package ru.sbt.mipt.oop.smarthome.devices.alarm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AlarmTest {
    private final Random randomGenerator = new Random();
    private final String defaultCode = String.valueOf(randomGenerator.nextInt());
    private final String correctCode = getCorrectCode();
    private final String incorrectCode = getIncorrectCode();
    private final String id = String.valueOf(randomGenerator.nextInt());
    private Alarm alarm;

    private String getCorrectCode() {
        String correctCode;
        do {
            correctCode = String.valueOf(randomGenerator.nextInt());
        } while (Objects.equals(correctCode, defaultCode));
        return correctCode;
    }

    private String getIncorrectCode() {
        String incorrectCode;
        do {
            incorrectCode = String.valueOf(randomGenerator.nextInt());
        } while (Objects.equals(incorrectCode, correctCode) || Objects.equals(incorrectCode, defaultCode));
        return incorrectCode;
    }

    @BeforeEach
    void setUp() {
        alarm = new Alarm(id, defaultCode);
    }

    @Test
    void testDefaultAlarmStateIsDeactivated() {
        assertTrue(alarm.getState() instanceof Deactivated);
    }

    @Test
    void testActivation() {
        alarm.activate(correctCode);
        assertTrue(alarm.getState() instanceof Activated);
    }

    @Test
    void testTriggerAlertFromDeactivated() {
        alarm.triggerAlert();
        assertTrue(alarm.getState() instanceof Alert);
    }

    @Test
    void testTriggerAlertFromActivated() {
        alarm.activate(correctCode);
        alarm.triggerAlert();
        assertTrue(alarm.getState() instanceof Alert);
    }

    @Test
    void testDeactivationWithCorrectCode() {
        alarm.activate(correctCode);
        alarm.deactivate(correctCode);
        assertTrue(alarm.getState() instanceof Deactivated);
    }

    @Test
    void testDeactivationWithIncorrectCodeTriggersAlert() {
        alarm.activate(correctCode);
        alarm.deactivate(incorrectCode);
        assertTrue(alarm.getState() instanceof Alert);
    }

    @Test
    void testAlertDeactivationWithCorrectCodeWhenAlertTriggeredFromActivated() {
        alarm.activate(correctCode);
        alarm.triggerAlert();
        alarm.deactivate(correctCode);
        assertTrue(alarm.getState() instanceof Deactivated);
    }

    @Test
    void testAlertDeactivationWithDefaultCodeWhenAlertTriggeredFromDeactivated() {
        alarm.triggerAlert();
        alarm.deactivate(defaultCode);
        assertTrue(alarm.getState() instanceof Deactivated);
    }

    @Test
    void testAlertDeactivationWithIncorrectCode() {
        alarm.triggerAlert();
        alarm.deactivate(incorrectCode);
        assertTrue(alarm.getState() instanceof Alert);
    }
}