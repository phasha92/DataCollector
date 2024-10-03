package org.example;

import org.example.parser.fromfile.ParserCVS;
import org.example.searcher.Searcher;
import org.example.parser.fromweb.MetroParser;
import org.example.parser.fromweb.WebPageSaver;
import org.example.parser.fromfile.ParserJSON;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.example.searcher.FormatFile.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        // 1
        String pathFile = WebPageSaver.saveDocument("https://skillbox-java.github.io/", "metro_doc.html");
        MetroParser.parseLines(pathFile).forEach(System.out::println);
        MetroParser.parseStations(pathFile).forEach(System.out::println);

        // 2
        String pathFile2 = "src\\data";
        List<Path> jsonPaths = Searcher.searchFiles(pathFile2, JSON);
        List<Path> csvPaths = Searcher.searchFiles(pathFile2, CSV);
        jsonPaths.forEach(System.out::println);
        csvPaths.forEach(System.out::println);

        // 3
        ParserJSON parserJSON = new ParserJSON();
        for (int i = 0; i < 2; i++) parserJSON.getObjectsFromFile(jsonPaths.get(i)).forEach(System.out::println);

        // 4
        ParserCVS parserCVS = new ParserCVS();
        for (int i = 0; i < 2; i++) parserCVS.getObjectsFromFile(csvPaths.get(i)).forEach(System.out::println);

    }

}