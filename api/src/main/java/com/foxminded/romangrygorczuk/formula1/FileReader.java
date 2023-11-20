package com.foxminded.romangrygorczuk.formula1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    public Stream<String> read(String fileName) throws IOException, URISyntaxException {
        URL resourceURL = getClass().getClassLoader().getResource(fileName);
        if (resourceURL == null) {
            throw new FileNotFoundException();
        }
        return Files.lines(Paths.get(resourceURL.toURI()));
    }
}

