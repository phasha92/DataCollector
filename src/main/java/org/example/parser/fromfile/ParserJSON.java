package org.example.parser.fromfile;

import org.example.parser.fromfile.entity.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParserJSON implements Parser<Station> {

    public List<Station> getObjectsFromFile(Path path) throws IOException, ParseException {

        List<Station> stations = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(getStringFromFile(path));

        array.forEach(element -> {
            JSONObject jsonObject = (JSONObject) element;
            String name = (String) jsonObject.get("station_name");
            String depth = (String) jsonObject.get("depth");
            stations.add(new Station(name, depth));
        });
        return stations;
    }

}
