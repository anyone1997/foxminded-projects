package com.foxminded.romangrygorczuk.charcount;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UniqueCharCounterTest {

    private UniqueCharCounter uniqueCharCounter = new UniqueCharCounter();

    @Test
    void count_shouldReturnSameNumberOfLetters_whenInputProvided() {
        Map<Character, Integer> numbers = new LinkedHashMap<>();
        numbers.put('h', 1);
        numbers.put('e', 1);
        numbers.put('l', 3);
        numbers.put('o', 2);
        numbers.put(' ', 1);
        numbers.put('w', 1);
        numbers.put('r', 1);
        numbers.put('d', 1);
        numbers.put('!', 1);
        assertEquals(numbers, uniqueCharCounter.count("hello world!"));
    }

    @Test
    void count_shouldReturnSameRegisterOfLetters_whenInputContainsUpperLowerCaseLetter() {
        Map<Character, Integer> numbers = new LinkedHashMap<>();
        numbers.put('R', 1);
        numbers.put('r', 1);
        numbers.put('O', 1);
        numbers.put('o', 1);
        numbers.put('M', 1);
        numbers.put('m', 1);
        numbers.put('A', 1);
        numbers.put('a', 1);
        numbers.put('N', 1);
        numbers.put('n', 1);
        assertEquals(numbers, uniqueCharCounter.count("RrOoMmAaNn"));
    }

    @Test
    void count_shouldReturnSpace_whenInputIsSpace() {
        Map<Character, Integer> numbers = new LinkedHashMap<>();
        numbers.put(' ', 1);
        assertEquals(numbers, uniqueCharCounter.count(" "));
    }

    @Test
    void count_shouldReturnAnEmptyMap_whenInputIsAnEmptyString() {
        Map<Character, Integer> numbers = new LinkedHashMap<>();
        assertEquals(numbers, uniqueCharCounter.count(""));
    }
}