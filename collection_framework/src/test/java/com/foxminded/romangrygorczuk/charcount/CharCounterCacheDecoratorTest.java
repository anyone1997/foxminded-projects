package com.foxminded.romangrygorczuk.charcount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharCounterCacheDecoratorTest {

    @Mock
    CharCounter decorated;

    @InjectMocks
    CharCounterCacheDecorator charCounterCacheDecorator;

    @Test
    void charCount_shouldTriggeredOnce_whenTwoInputsAreTheSame() {
        Map<Character, Integer> charsFrequencies = new HashMap<>();
        charsFrequencies.put('t', 1);
        String text = "t";
        when(decorated.count(text)).thenReturn(charsFrequencies);
        assertEquals(charsFrequencies, charCounterCacheDecorator.count(text));
        assertEquals(charsFrequencies, charCounterCacheDecorator.count(text));
        verify(decorated, times(1)).count(text);
    }
}