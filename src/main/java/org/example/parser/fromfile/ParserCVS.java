package org.example.parser.fromfile;

import org.example.pojo.StationWithDate;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParserCVS implements Parser<StationWithDate> {

    @Override
    public List<StationWithDate> getObjectsFromFile(Path path) throws IOException {
        List<StationWithDate> stationWithDates = new ArrayList<>();

        String[] lines = getStringFromFile(path).split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            stationWithDates.add(new StationWithDate(values[0], values[1]));
        }
        return stationWithDates;
    }

}
