package com.foxminded.romangrygorczuk.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnagramTest {

    Anagram anagram = new Anagram();

    @Test
    void reverseText_NullPointerExceptionShouldNotBeThrown_whenInputNullIsProvided() {
        assertThrows(NullPointerException.class,
                ()->
                    anagram.reverseText(null)
                );
    }

    @Test
    void reverseText_shouldReverseAllChars_whenStringContainsOnlyLetters() {
        assertEquals("cba", anagram.reverseText("abc"));
    }

    @Test
    void reverseText_shouldReturnAnEmptyString_whenInputStringIsEmpty() {
        assertEquals("", anagram.reverseText(""));
    }

    @Test
    void reverseText_shouldReturnSameAmountOfSpaces_whenInputContainsFewSpaces() {
        assertEquals("   ", anagram.reverseText("   "));
    }

    @Test
    void reverseText1_shouldReturnSameAmoundOfSpaceBeforeText_whenInputSpacesAndText() {
        assertEquals(" cba", anagram.reverseText(" abc"));
    }

    @Test
    void reverseText5_shouldReturnSameAmoundOfSpaceBeforeText_whenInputSpacesAndText() {
        assertEquals("  cba", anagram.reverseText("  abc"));
    }

    @Test
    void reverseText_shouldReturnSameAmoundOfSpaceBeforeText_whenInputSpacesAndText() {
        assertEquals("   cba", anagram.reverseText("   abc"));
    }

    @Test
    void reverseText_shouldReturnOneChar_whenInputStringContainsOneChar() {
        assertEquals("a", anagram.reverseText("a"));
    }

    @Test
    void reverseText_shouldReturnSameInput_whenInputStringContainsOThreeSpaces() {
        assertEquals("a   a", anagram.reverseText("a   a"));
    }

    @Test
    void reverseTest_shouldReturnReversedInputNotMovingNumbers_whenInputContainNumbers() {
        assertEquals("aaaAa1", anagram.reverseText("aAaaa1"));
    }

    @Test
    void reverseTest_shouldReturnSameNonReversedSequenceOfSymbols_whenOnlySymbolsProvided() {
        assertEquals("1@345", anagram.reverseText("1@345"));
    }

    @Test
    void reverseTest_shouldReturnSameReversedSeveralWords_whenAFewWordsProvided() {
        assertEquals("d1cba hgf!e", anagram.reverseText("a1bcd efg!h"));
    }

    @Test
    void reverseTest_shouldReturnReversedCharacters_whenInputInLowerAndUpperCases() {
        assertEquals("aAbbc1", anagram.reverseText("cbbAa1"));
    }
}