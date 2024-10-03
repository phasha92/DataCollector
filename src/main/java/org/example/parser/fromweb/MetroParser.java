package org.example.parser.fromweb;

import org.example.parser.fromweb.entity.Line;
import org.example.parser.fromweb.entity.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MetroParser {

    public static List<Line> parseLines(String path) throws IOException {

        Document document = Jsoup.parse(Files.readString(Paths.get(path)));
        Elements elements = document.select(".js-metro-line");
        List<Line> lines = new ArrayList<>();

        for (Element line : elements) {
            String name = line.text();
            String number = line.attr("data-line");
            lines.add(new Line(name, number));
        }

        return lines;
    }

    public static List<Station> parseStations(String path) throws IOException{

        Document document = Jsoup.parse(Files.readString(Paths.get(path)));
        List<Station> metroStations = new ArrayList<>();
        Elements stations = document.select(".js-metro-stations");

        for (Element stationList : stations) {
            String lineNumber = stationList.attr("data-line");
            Elements stationNames = stationList.select("span.name");

            for (Element station : stationNames) {
                String stationName = station.text();  // Получаем имя станции
                metroStations.add(new Station(stationName, lineNumber));
            }
        }

        return metroStations;
    }
}
