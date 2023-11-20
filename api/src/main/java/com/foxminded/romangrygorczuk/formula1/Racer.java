package com.foxminded.romangrygorczuk.formula1;

import java.time.Duration;
import java.util.Objects;

public class Racer {

    private final String abbreviation;
    private final String name;
    private final String team;
    private Duration bestLapTime;

    public Racer(String abbreviation, String name, String team) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.team = team;
    }

    public void setBestLapTime(Duration bestLapTime) {
        this.bestLapTime = bestLapTime;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Duration getBestLapTime() {
        return bestLapTime;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racer racer = (Racer) o;
        return Objects.equals(abbreviation, racer.abbreviation) && Objects.equals(name, racer.name) && Objects.equals(team, racer.team) && Objects.equals(bestLapTime, racer.bestLapTime);
    }
}

