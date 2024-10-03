package org.example.parser.fromfile;

import org.example.parser.fromfile.entity.Date;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParserCVS implements Parser<Date> {

    @Override
    public List<Date> getObjectsFromFile(Path path) throws IOException {
        List<Date> dates = new ArrayList<>();

        String[] lines = getStringFromFile(path).split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            dates.add(new Date(values[0], values[1]));
        }
        return dates;
    }

}
