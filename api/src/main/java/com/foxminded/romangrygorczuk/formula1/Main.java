package com.foxminded.romangrygorczuk.formula1;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String startFilename = "start.log";
        String endFilename = "end.log";
        String abbreviationFilename = "abbreviations.txt";
        FileReader fileReader = new FileReader();
        RacerService racerService = new RacerService();
        BestRacersFormatter formatRacers = new BestRacersFormatter();
        System.out.println(formatRacers.format(
            racerService.splitLogData(
                fileReader.read(startFilename),
                fileReader.read(endFilename),
                fileReader.read(abbreviationFilename)), 5));
    }
}