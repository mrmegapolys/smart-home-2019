package ru.sbt.mipt.oop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeProvider {

    public static SmartHome readFile(String filepath, String encoding) throws IOException {
        String encoded = new String(Files.readAllBytes(Paths.get(filepath)));

        switch (encoding) {
            case "json":
                return SmartHomeSerializer.fromJson(encoded);
            default:
                throw new UnsupportedEncodingException();
        }

    }
}
