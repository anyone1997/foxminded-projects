package com.foxminded.romangrygorczuk.charcount;

import java.util.HashMap;
import java.util.Map;

public class CharCounterCacheDecorator extends CharCounterDecorator {

    private Map<String, Map<Character, Integer>> cache = new HashMap<>();

    public CharCounterCacheDecorator(CharCounter decorated) {
        super(decorated);
    }

    public Map<Character, Integer> count(String input) {
        return cache.computeIfAbsent(input, key -> decorated.count(input));
    }
}
