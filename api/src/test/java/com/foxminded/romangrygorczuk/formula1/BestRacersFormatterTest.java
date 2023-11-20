package com.foxminded.romangrygorczuk.formula1;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BestRacersFormatterTest {

    BestRacersFormatter bestRacersFormatter = new BestRacersFormatter();

    @Test
    void sortRacers_shouldSortAndShouldNotContainSeparator_whenTopPlacesIs15() {
        String actual = String.valueOf(bestRacersFormatter.format(buildRacers(), 15));
        String expected =
            " 1. Sebastian Vettel | FERRARI  | 1:04.415" + System.lineSeparator() +
                " 2. Valtteri Bottas  | MERCEDES | 1:12.434" + System.lineSeparator() +
                " 3. Carlos Sainz     | RENAULT  | 1:12.950" + System.lineSeparator();
        assertEquals(expected, actual);
    }

    @Test
    void sortRacers_shouldSortAndPlaceSeparatorAfterFirstPlace_whenOnlyOneTopPlace() {
        String actual = String.valueOf(bestRacersFormatter.format(buildRacers(), 1));
        String expected =
            " 1. Sebastian Vettel | FERRARI  | 1:04.415" + System.lineSeparator() +
                "------------------------------------------" + System.lineSeparator() +
                " 2. Valtteri Bottas  | MERCEDES | 1:12.434" + System.lineSeparator() +
                " 3. Carlos Sainz     | RENAULT  | 1:12.950" + System.lineSeparator();
        assertEquals(expected, actual);
    }

    @Test
    void sortRacers_shouldSortAndPlaceSeparatorAfterThirdPlace_whenThreeTopPlaces() {
        String actual = String.valueOf(bestRacersFormatter.format(buildRacers(), 3));
        String expected =
            " 1. Sebastian Vettel | FERRARI  | 1:04.415" + System.lineSeparator() +
                " 2. Valtteri Bottas  | MERCEDES | 1:12.434" + System.lineSeparator() +
                " 3. Carlos Sainz     | RENAULT  | 1:12.950" + System.lineSeparator() +
                "------------------------------------------" + System.lineSeparator();
        assertEquals(expected, actual);
    }

    private List<Racer> buildRacers() {
        List<Racer> racers = new ArrayList<>();
        Racer racerOne = new Racer("CSR", "Carlos Sainz", "RENAULT");
        racerOne.setBestLapTime(Duration.parse("PT1M12.95S"));
        racers.add(racerOne);
        Racer racerTwo = new Racer("SVF", "Sebastian Vettel", "FERRARI");
        racerTwo.setBestLapTime(Duration.parse("PT1M4.415S"));
        racers.add(racerTwo);
        Racer racerThree = new Racer("VBM", "Valtteri Bottas", "MERCEDES");
        racerThree.setBestLapTime(Duration.parse("PT1M12.434S"));
        racers.add(racerThree);
        return racers;
    }
}