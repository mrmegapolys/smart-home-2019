package ru.sbt.mipt.oop.smarthome.devices.alarm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlarmTest {
    private Random randomGenerator = new Random();
    private int correctCode = randomGenerator.nextInt();
    private int incorrectCode = getIncorrectCode();
    private String id = String.valueOf(randomGenerator.nextInt());
    private Alarm alarm;

    private int getIncorrectCode() {
        int incorrectCode;
        do {
            incorrectCode = randomGenerator.nextInt();
        } while (incorrectCode == correctCode);
        return incorrectCode;
    }

    @BeforeEach
    void setUp() {
        alarm = new Alarm(id, correctCode);
    }

    @Test
    void testDefaultAlarmStateIsDeactivated() {
        assertTrue(alarm.getState() instanceof Deactivated);
    }

    @Test
    void testActivationOnCorrectCode() {
        alarm.activate(correctCode);
        assertTrue(alarm.getState() instanceof Activated);
    }

    @Test
    void testNoActivationOnIncorrectCode() {
        assertNotEquals(correctCode, incorrectCode);
        alarm.activate(incorrectCode);
        assertTrue(alarm.getState() instanceof Deactivated);
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
    void testAlertDeactivationWithCorrectCode() {
        alarm.triggerAlert();
        alarm.deactivate(correctCode);
        assertTrue(alarm.getState() instanceof Deactivated);
    }

    @Test
    void testAlertDeactivationWithIncorrectCode() {
        alarm.triggerAlert();
        alarm.deactivate(incorrectCode);
        assertTrue(alarm.getState() instanceof Alert);
    }
}