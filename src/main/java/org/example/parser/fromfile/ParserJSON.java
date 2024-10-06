package org.example.parser.fromfile;

import org.example.pojo.StationWithDepth;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParserJSON implements Parser<StationWithDepth> {

    public List<StationWithDepth> getObjectsFromFile(Path path) throws IOException, ParseException {

        List<StationWithDepth> stationWithDepths = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(getStringFromFile(path));

        array.forEach(element -> {
            JSONObject jsonObject = (JSONObject) element;
            String name = (String) jsonObject.get("station_name");
            String depth = (String) jsonObject.get("depth");
            stationWithDepths.add(new StationWithDepth(name, depth));
        });
        return stationWithDepths;
    }

}
