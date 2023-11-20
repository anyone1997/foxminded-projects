package com.foxminded.romangrygorczuk.formula1;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FileReaderTest {

    FileReader fileReader = new FileReader();

    @Test
    void read_shouldReturnStream_whenFileExistsAndNotEmpty() throws IOException, URISyntaxException {
        String racersStartLap = "shortStart.log";
        assertThat(fileReader.read(racersStartLap)).contains("SVF2018-05-24_12:02:58.917", "NHR2018-05-24_12:02:49.914");
    }

    @Test
    void read_shouldReturnEmptyResult_whenFileIsEmpty() throws IOException, URISyntaxException {
        String racersStartLap = "empty.log";
        assertThat(fileReader.read(racersStartLap)).isEmpty();
    }

    @Test
    void read_shouldNotReturnResult_whenFileIsMissing() throws IOException, URISyntaxException {
        String racersStartLap = "fileDoesNotExist.log";
        assertThrows(FileNotFoundException.class, () -> {
            fileReader.read(racersStartLap);
        });
    }
}