package org.example.parser.fromfile;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public interface Parser<T> {
    List<T> getObjectsFromFile(Path path) throws IOException, ParseException;

    default String getStringFromFile(Path path) throws IOException {

        StringBuilder builder = new StringBuilder();
        Files.readAllLines(path).forEach(line -> builder.append(line).append("\n"));

        return builder.toString();
    }
}
