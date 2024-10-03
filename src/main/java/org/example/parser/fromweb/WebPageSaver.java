package org.example.parser.fromweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WebPageSaver {

    public static String saveDocument(String url, String filename) throws IOException {

        Document document = Jsoup.connect(url).get();

        String res = "resources";
        Path resourceDirectory = Paths.get("src", "main", res, filename);

        if (Files.notExists(resourceDirectory)) Files.createFile(resourceDirectory);
        Files.write(resourceDirectory, document.outerHtml().getBytes());

        return resourceDirectory.toString();
    }
}
