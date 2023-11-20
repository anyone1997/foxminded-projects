package com.foxminded.romangrygorczuk.task1;

public class Anagram {

    public static final String SPACE = " ";

    public String reverseText(String text) {
        StringBuilder result = new StringBuilder();
        String[] words = text.split(SPACE, -1);
        for (String word : words) {
            String reversedWord = reverseWord(word);
            result.append(reversedWord);
            if(result.length() < text.length()) {
                result.append(SPACE);
            }
        }
        return result.toString();
    }

    private String reverseWord(String text) {
        char[] chars = text.toCharArray();
        int leftIndex = 0;
        int rightIndex = text.length() - 1;
        while (leftIndex < rightIndex) {
            if (!Character.isAlphabetic(chars[leftIndex])) {
                leftIndex++;
            } else if (!Character.isAlphabetic(chars[rightIndex])) {
                rightIndex--;
            } else {
                char leftChar = chars[leftIndex];
                chars[leftIndex] = chars[rightIndex];
                chars[rightIndex] = leftChar;
                leftIndex++;
                rightIndex--;
            }
        }
        return String.copyValueOf(chars);
    }
}