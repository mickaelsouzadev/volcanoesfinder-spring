package com.mickaelsouzadev.volcanoesfinder.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
    private static final String BASE_PATH = "src/main/resources/data";

    public String read(String fileName) {
        try {
            return Files.readString(Paths.get(BASE_PATH + fileName));
        } catch (IOException e) {
            return null;
        }
    }

    public void write(String filename, String content) throws IOException {
        Files.writeString(Paths.get(BASE_PATH + filename), content);
    }
}
