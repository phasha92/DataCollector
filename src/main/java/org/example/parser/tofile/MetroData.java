package org.example.parser.tofile;


import org.example.parser.fromweb.MetroParser;
import org.example.pojo.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class MetroData {

    public static void saveStationsToJson(List<StationAllData> stations, String filePath) throws IOException {

        JSONObject resultJson = new JSONObject();
        JSONArray stationsArray = new JSONArray();

        for (StationAllData station : stations) {

            JSONObject stationJson = new JSONObject();

            if (station.hasConnection()) stationJson.put("hasConnection", station.hasConnection());
            if (!station.depth().isEmpty()) stationJson.put("depth", station.depth());
            if (!station.date().isEmpty()) stationJson.put("date", station.date());
            stationJson.put("line", station.line());
            stationJson.put("name", station.name());

            stationsArray.add(stationJson);
        }

        resultJson.put("stations", stationsArray);

        Path path = Path.of(filePath);

        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        Files.writeString(path, resultJson.toJSONString(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<StationAllData> getAllData(
            List<StationFromWeb> allStations,
            List<Line> allLines,
            List<StationWithDate> allDatesFromFiles,
            List<StationWithDepth> allDepthsFromFiles) throws IOException {
        List<StationAllData> allData = new ArrayList<>();

        for (var station : allStations){
            String name = station.name();
            String line = getLineName(station.lineNumber(), allLines);
            String date = getDate(station.name(), allDatesFromFiles);
            String depth = getDepth(station.name(), allDepthsFromFiles);
            boolean hasConnection = station.hasConnection();

            allData.add(new StationAllData(name,line, date, depth, hasConnection));
        }

        return allData;
    }

    private static String getDate(String nameStation, List<StationWithDate> allStationWithDates){
        for (var date : allStationWithDates) {
            if (date.name().equals(nameStation)) return date.date();
        }
        return "";
    }
    private static String getLineName(String numberLine, List<Line>  allLines){
        for (var line : allLines) {
            if (line.number().equals(numberLine)) return line.name();
        }
        return "";
    }
    private static String getDepth(String nameStation, List<StationWithDepth> allStationWithDepths){
        for (var station : allStationWithDepths) {
            if (station.name().equals(nameStation)) return station.depth();
        }
        return "";
    }

}
