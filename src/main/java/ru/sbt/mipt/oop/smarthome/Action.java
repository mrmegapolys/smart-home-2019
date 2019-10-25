package ru.sbt.mipt.oop.smarthome;

@FunctionalInterface
public interface Action {
    void run(Actionable actionable);
}
