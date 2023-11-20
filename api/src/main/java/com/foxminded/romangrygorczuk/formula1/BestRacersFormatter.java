package com.foxminded.romangrygorczuk.formula1;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class BestRacersFormatter {

    public String format(List<Racer> racers, int topPlaces) {
        StringBuilder formattedResult = new StringBuilder();
        AtomicInteger place = new AtomicInteger();
        String template = "%2d. %-" + getLength(racers, Racer::getName) + "s | %-" + getLength(racers, Racer::getTeam) + "s | %s:%02d.%03d%n";

        racers.stream()
            .sorted(Comparator.comparing(Racer::getBestLapTime))
            .forEach(racer -> formattedResult.append(formatRacer(template, place, racer, topPlaces)));
        return formattedResult.toString();
    }

    private int getLength(List<Racer> racers, Function<Racer, String> getter) {
        return racers.stream()
            .map(getter)
            .mapToInt(String::length)
            .max()
            .getAsInt();
    }

    private String formatRacer(String template, AtomicInteger place, Racer racer, int topPlaces) {
        String formattedRacer = String.format(template,
            place.incrementAndGet(),
            racer.getName(),
            racer.getTeam(),
            racer.getBestLapTime().toMinutes(),
            racer.getBestLapTime().toSecondsPart(),
            racer.getBestLapTime().toMillisPart());
        String separator = "-";
        if (place.get() == topPlaces) {
            for (int i = 0; i < formattedRacer.length() - 2; i++) {
                separator = separator.concat("-");
            }
            return formattedRacer.concat(String.format("%s%n", separator));
        }
        return formattedRacer;
    }
}