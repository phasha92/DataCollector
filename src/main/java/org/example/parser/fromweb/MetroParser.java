package org.example.parser.fromweb;

import org.example.pojo.Line;
import org.example.pojo.StationFromWeb;
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

    public static List<StationFromWeb> parseStations(String filePath) throws IOException{

        List<StationFromWeb> stations = new ArrayList<>();

        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        Document doc = Jsoup.parse(content);
        Elements stationElements = doc.select("p.single-station");

        for (Element stationElement : stationElements) {

            String name = stationElement.select("span.name").text();
            String lineNumber = stationElement.parent().attr("data-line");
            boolean hasConnection = !stationElement.select("span.t-icon-metroln").isEmpty();

            stations.add(new StationFromWeb(name, lineNumber, hasConnection));
        }

        return stations;
    }

}
