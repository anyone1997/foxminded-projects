package com.foxminded.romangrygorczuk.formula1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    public List<Racer> splitLogData(Stream<String> startLog, Stream<String> endLog, Stream<String> abbreviations) {
        Map<String, LocalDateTime> splitStartLog = splitLogs(startLog);
        Map<String, LocalDateTime> splitEndLog = splitLogs(endLog);
        return abbreviations
            .map((abbr) -> {
                String[] separatedRacersData = abbr.split("_", 4);
                Racer racer = new Racer(separatedRacersData[0], separatedRacersData[1], separatedRacersData[2]);
                Duration duration = Duration.between(splitStartLog.get(racer.getAbbreviation()), splitEndLog.get(racer.getAbbreviation()));
                racer.setBestLapTime(duration);
                return racer;
            })
            .collect(Collectors.toList());
    }

    private Map<String, LocalDateTime> splitLogs(Stream<String> logs) {
        return logs
            .collect(Collectors.toMap(p -> p.substring(0, 3),
                p -> LocalDateTime.parse(p.substring(3),
                    FORMATTER)));
    }
}