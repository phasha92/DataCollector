package org.example;

import org.example.parser.fromfile.Parser;
import org.example.parser.fromfile.ParserCVS;
import org.example.pojo.*;
import org.example.parser.tofile.MetroData;
import org.example.searcher.Searcher;
import org.example.parser.fromweb.MetroParser;
import org.example.parser.tofile.WebPageSaver;
import org.example.parser.fromfile.ParserJSON;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.example.searcher.FormatFile.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        String pathFile = WebPageSaver.saveDocument("https://skillbox-java.github.io/", "metro_doc.html");

        List<StationFromWeb> allStations = MetroParser.parseStations(pathFile);
        List<Line> allLines = MetroParser.parseLines(pathFile);

        List<StationWithDate> allDatesFromFiles = new ArrayList<>();
        List<StationWithDepth> allStationsFromFiles = new ArrayList<>();

        String path = "src\\data";
        List<Path> csvPaths = Searcher.searchFiles(path, CSV);
        List<Path> jsonPaths = Searcher.searchFiles(path, JSON);

        Parser<StationWithDepth> parserJSON = new ParserJSON();
        Parser<StationWithDate>  parserCVS = new ParserCVS();

        for (int i = 0; i < 3; i++) {
            allDatesFromFiles.addAll(parserCVS.getObjectsFromFile(csvPaths.get(i)));
            allStationsFromFiles.addAll(parserJSON.getObjectsFromFile(jsonPaths.get(i)));
        }

        String pathJSON = "src\\main\\resources\\all_data.json";
        List<StationAllData> allData =  MetroData.getAllData(allStations,allLines, allDatesFromFiles, allStationsFromFiles);
        MetroData.saveStationsToJson(allData, pathJSON);
        pathJSON = "src\\main\\resources\\data.json";
        MetroData.saveSCLToJson(allStations,allLines, pathJSON);

    }

}





















