package ru.sbt.mipt.oop.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Writer {

    public static boolean writeStringToFile(String body, String filename) {
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(body);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
