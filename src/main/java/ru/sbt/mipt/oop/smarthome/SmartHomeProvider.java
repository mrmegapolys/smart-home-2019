package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.utils.SmartHomeSerializer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public class SmartHomeProvider {
    public static SmartHome readFile(String filepath, String encoding) throws IOException {
        if ("json".equals(encoding)) {
            String encoded = read(filepath);
            return SmartHomeSerializer.fromJson(encoded);
        }

        throw new UnsupportedEncodingException("Unsupported encoding: " + encoding);
    }

    private static String read(String filepath) throws IOException {
        return new String(readAllBytes(Paths.get(filepath)));
    }
}
