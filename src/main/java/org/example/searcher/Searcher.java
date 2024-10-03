package org.example.searcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Searcher {
    private Searcher() {
    }

    public static List<Path> searchFiles(String pathFile, FormatFile format) {

        String finalFormat = format.getFormat();

        try (Stream<Path> walk = Files.walk(Path.of(pathFile))) {
            return walk.filter(Files::isRegularFile)
                    .filter(item -> item.toString().endsWith(finalFormat))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}