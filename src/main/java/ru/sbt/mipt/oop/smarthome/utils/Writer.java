package ru.sbt.mipt.oop.smarthome.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Writer {

    public static void writeStringToFile(String body, String filename) throws IOException {
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(body);
        }
    }

}
