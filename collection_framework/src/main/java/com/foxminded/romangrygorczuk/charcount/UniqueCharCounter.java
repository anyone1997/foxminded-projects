package com.foxminded.romangrygorczuk.charcount;

import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueCharCounter implements CharCounter {

    @Override
    public Map<Character, Integer> count(String text) {
        Map<Character, Integer> charsFrequencies = new LinkedHashMap<>();
        char[] chars = text.toCharArray();
        for (char c : chars) {
            if (charsFrequencies.containsKey(c)) {
                charsFrequencies.put(c, charsFrequencies.get(c) + 1);
            } else {
                charsFrequencies.put(c, 1);
            }
        }
        return charsFrequencies;
    }
}
