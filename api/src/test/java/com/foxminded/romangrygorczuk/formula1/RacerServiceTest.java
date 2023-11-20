package com.foxminded.romangrygorczuk.formula1;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RacerServiceTest {

    RacerService racerService = new RacerService();

    @Test
    void countLaps_shouldReturnListOfRacers_whenStartEndLogsAreProvided() {
        Stream<String> startLog = Stream.of("BHS2018-05-24_12:14:51.985", "CLS2018-05-24_12:09:41.921");
        Stream<String> endLog = Stream.of("BHS2018-05-24_12:16:05.164", "CLS2018-05-24_12:10:54.750");
        Stream<String> abbreviations = Stream.of("BHS_Brendon Hartley_SCUDERIA TORO ROSSO HONDA", "CLS_Charles Leclerc_SAUBER FERRARI");

        List<Racer> actual = racerService.splitLogData(startLog, endLog, abbreviations);

        List<Racer> expected = new ArrayList<>();
        Racer racerOne = new Racer("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA");
        racerOne.setBestLapTime(Duration.parse("PT1M13.179S"));
        expected.add(racerOne);

        Racer racerTwo = new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI");
        racerTwo.setBestLapTime(Duration.parse("PT1M12.829S"));
        expected.add(racerTwo);

        assertEquals(expected, actual);
    }
}