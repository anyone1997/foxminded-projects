package com.foxminded.romangrygorczuk.charcount;

import java.util.Map;

public interface CharCounter {

    Map<Character, Integer> count(String text);
}
